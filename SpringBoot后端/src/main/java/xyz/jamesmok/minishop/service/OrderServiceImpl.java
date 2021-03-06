package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jamesmok.minishop.Mapper.CartMapper;
import xyz.jamesmok.minishop.Mapper.GoodMapper;
import xyz.jamesmok.minishop.Mapper.OrderDetailMapper;
import xyz.jamesmok.minishop.Mapper.OrderMapper;
import xyz.jamesmok.minishop.entiiy.CartEntity;
import xyz.jamesmok.minishop.entiiy.GoodEntity;
import xyz.jamesmok.minishop.entiiy.OrderDetailEntity;
import xyz.jamesmok.minishop.entiiy.OrderEntity;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public Map<String, Object> getOrderByUserId(int pageNum, int pageSize, long id) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderEntity> pageInfo = new PageInfo<>(orderMapper.getOrderByUserId(id));
        Long total = pageInfo.getTotal();
        List<OrderEntity> cart = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", cart);
        return map;
    }

    @Override
    public int deleteOrderById(long id) {
        return orderMapper.deleteOrderById(id);
    }

    @Override
    public int batchDeleteOrderById(int[] ids) {
        return orderMapper.batchDeleteOrderById(ids);
    }

    @Override
    public int insertOrder(OrderEntity orderEntity) {
        AtomicReference<Boolean> flag = new AtomicReference<>(false); //??????cart?????????????????????????????????????????????????????????????????????????????????
        Date date = new Date();
        orderEntity.setTime(new Timestamp(date.getTime())); //????????????
        List<CartEntity> cart = cartMapper.getCartByUserIdNoPage(orderEntity.getUser_id());
        //?????????forEach??????????????????????????????????????????????????????????????????
        cart.forEach((e)->{
            GoodEntity good = goodMapper.getGoodById(e.getGood_id());
            if (e.getCount() <= good.getCount()) {
                flag.set(true);
            }
        });
        //???????????????????????????
        if (flag.get()) {
            orderMapper.insertOrder(orderEntity);//????????????
            Long lastOrderId = orderMapper.getLastId(); //????????????ID
            //??????????????????????????????
            cart.forEach((e)->{
                GoodEntity good = goodMapper.getGoodById(e.getGood_id()); // ??????????????????
                //?????????????????????????????????????????????????????????
                if (e.getCount() <= good.getCount()) {
                    OrderDetailEntity orderDetail = new OrderDetailEntity();
                    orderDetail.setOrder_id(lastOrderId);
                    orderDetail.setCount(e.getCount());
                    orderDetail.setPrice((long) (good.getPrice()*e.getCount()));
                    orderDetail.setCategory_id(good.getCategory_id());
                    orderDetail.setGood_id(e.getGood_id());
                    orderDetail.setUser_id(e.getUser_id());
                    orderDetail.setSaler_id(good.getSaler_id());
                    orderDetailMapper.insertOrderDetail(orderDetail); //???????????????????????????
                    GoodEntity tempGood = new GoodEntity();
                    tempGood.setId(good.getId());
                    tempGood.setCount(good.getCount()-e.getCount());
                    goodMapper.updateGoodCount(tempGood); //????????????????????????
                    cartMapper.deleteCartById(e.getId()); //???????????????????????????????????????
                }
            });
        }


        return 0;
    }
}
