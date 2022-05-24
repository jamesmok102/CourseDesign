package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jamesmok.minishop.Mapper.OrderDetailMapper;
import xyz.jamesmok.minishop.Mapper.UserMapper;
import xyz.jamesmok.minishop.entiiy.OrderDetailEntity;
import xyz.jamesmok.minishop.entiiy.UserEntity;
import xyz.jamesmok.minishop.entiiy.UserPortraitEntity;

import java.util.*;

@Service
public class UserPortraitServiceImpl implements UserPortraitService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Map<String, Object> check(int pageNum) {

        List<UserPortraitEntity> userPortraitEntities = new ArrayList<>();

        //取得用户所有资料
        PageHelper.startPage(pageNum, 5);
        PageInfo<UserEntity> userPageInfo = new PageInfo<>(userMapper.getUserAll());
        List<UserEntity> user = userPageInfo.getList();

        user.forEach((u)->{
            UserPortraitEntity userPortraitEntity = new UserPortraitEntity();
            userPortraitEntity.setName(u.getName());
            userPortraitEntity.setArea(u.getArea());

            //取得每个用户的购买记录
            PageHelper.startPage(pageNum, 5);
            PageInfo<OrderDetailEntity> orderDetailPageInfo = new PageInfo<>(orderDetailMapper.getOrderDetailByUserId(u.getId()));
            List<OrderDetailEntity> orderDetail = orderDetailPageInfo.getList();

//            long power = orderDetail.stream().mapToLong(OrderDetailEntity::getPrice).sum();
            long power = 0;
            for (OrderDetailEntity od : orderDetail) {
                power += od.getPrice();
            }

            userPortraitEntity.setPower(power);

            //List<String> Category_String = new ArrayList<>();

            for (OrderDetailEntity od: orderDetail) {
                //Category_String.add(od.getItem());
                userPortraitEntity.setItem(od.getItem());
                break;
            }

//            orderDetail.forEach((od)->{
//                Category_Ids.add(od.getCategory_id());
//            });

            //userPortraitEntity.setItem(Category_String.get(0));

            userPortraitEntities.add(userPortraitEntity);
        });

        Map<String, Object> map = new HashMap<>();
        map.put("data", userPortraitEntities);
        map.put("PageSize", userPageInfo.getSize());
        map.put("PageLength", userPageInfo.getPages());
        map.put("PageTotal", userPageInfo.getTotal());


        return map;
    }
}
