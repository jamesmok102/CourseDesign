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
        AtomicReference<Boolean> flag = new AtomicReference<>(false); //只要cart的其中一个商品的所需数量小于商品实际数量，才可生成订单
        Date date = new Date();
        orderEntity.setTime(new Timestamp(date.getTime())); //取得时间
        List<CartEntity> cart = cartMapper.getCartByUserIdNoPage(orderEntity.getUser_id());
        //第一次forEach检查购物车全部商品的数量是否超出商品实际数量
        cart.forEach((e)->{
            GoodEntity good = goodMapper.getGoodById(e.getGood_id());
            if (e.getCount() <= good.getCount()) {
                flag.set(true);
            }
        });
        //符合生成订单的条件
        if (flag.get()) {
            orderMapper.insertOrder(orderEntity);//生成订单
            Long lastOrderId = orderMapper.getLastId(); //取得订单ID
            //检查购物车的每个商品
            cart.forEach((e)->{
                GoodEntity good = goodMapper.getGoodById(e.getGood_id()); // 取得实际商品
                //如果购物车里的商品数量没有超过实际数量
                if (e.getCount() <= good.getCount()) {
                    OrderDetailEntity orderDetail = new OrderDetailEntity();
                    orderDetail.setOrder_id(lastOrderId);
                    orderDetail.setCount(e.getCount());
                    orderDetail.setPrice((long) (good.getPrice()*e.getCount()));
                    orderDetail.setCategory_id(good.getCategory_id());
                    orderDetail.setGood_id(e.getGood_id());
                    orderDetail.setUser_id(e.getUser_id());
                    orderDetail.setSaler_id(good.getSaler_id());
                    orderDetailMapper.insertOrderDetail(orderDetail); //生成商品的订单详情
                    GoodEntity tempGood = new GoodEntity();
                    tempGood.setId(good.getId());
                    tempGood.setCount(good.getCount()-e.getCount());
                    goodMapper.updateGoodCount(tempGood); //更新实际商品数量
                    cartMapper.deleteCartById(e.getId()); //买了当然要删除购物车的商品
                }
            });
        }


        return 0;
    }
}
