package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jamesmok.minishop.Mapper.CartMapper;
import xyz.jamesmok.minishop.Mapper.GoodMapper;
import xyz.jamesmok.minishop.entiiy.CartEntity;
import xyz.jamesmok.minishop.entiiy.GoodEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodMapper goodMapper;


    @Override
    public Map<String, Object> getCartAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<CartEntity> pageInfo = new PageInfo<>(cartMapper.getCartAll());
        Long total = pageInfo.getTotal();
        List<CartEntity> cart = pageInfo.getList();
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
    public int deleteCartById(long id) {
        return cartMapper.deleteCartById(id);
    }

    @Override
    public int batchDeleteCartById(int[] ids) {
        return cartMapper.batchDeleteCartById(ids);
    }

    @Override
    public Map<String, Object> getCartByUserId(int pageNum, int pageSize, long id) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<CartEntity> pageInfo = new PageInfo<>(cartMapper.getCartByUserId(id));
        Long total = pageInfo.getTotal();
        List<CartEntity> cart = pageInfo.getList();
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
    public int UserDealwithCart(CartEntity cart) {
        //如果是从商品详情里点击加入购物车，cart的id会为null，如果是从购物车面页改变数量，则cart的id不为null
        //logger.info(cart.toString());
        if (cart.getId() == null) {
            CartEntity sqlcart = cartMapper.getCartByUserIdGoodId(cart.getUser_id(), cart.getGood_id());
            //检查购物车是否有商品，有的话就更新，没有的话插入
            if (sqlcart != null) {
                GoodEntity sqlgood = goodMapper.getGoodById(cart.getGood_id());
                //logger.info(sqlgood.toString());
                //检查改变购物车的数量是否超过商品库存数量
                if (cart.getCount() >= sqlgood.getCount() || cart.getCount() < 1) {
                    return -1;
                }
                cart.setId(sqlcart.getId());
                cart.setCount(cart.getCount()+1);
                return cartMapper.updateCart(cart);
            }
            return cartMapper.insertCart(cart);
        } else {
            GoodEntity sqlgood = goodMapper.getGoodById(cart.getGood_id());
            //检查改变购物车的数量是否超过商品库存数量
            if (cart.getCount() > sqlgood.getCount()  || cart.getCount() <= 1) {
                return -1;
            }
            return cartMapper.updateCart(cart);
        }
    }

    @Override
    public int changeSelectedById(List<Long> ids, Long user_id) {
        int status1 = 0;
        int status2 = 0;
        List<CartEntity> list = cartMapper.getCartByUserId(user_id);
        List<Long> allIds = new ArrayList<>(); //全部的id
        List<Long> allIdsSubIds = new ArrayList<>(); //全部id和已选id的差集
        list.forEach((e)->{
            allIds.add(e.getId());
            allIdsSubIds.add(e.getId());
        });
        allIdsSubIds.removeAll(ids); //求差集
        if (ids.size() != 0) {
            status1 = cartMapper.change1SelectedById(ids); //已选中的selected=1
        }
        if (allIdsSubIds.size() != 0) {
            status2 = cartMapper.change0SelectedById(allIdsSubIds); //未选中的selected=0
        }
        return status1+status2;


    }
}
