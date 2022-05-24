package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jamesmok.minishop.Mapper.OrderDetailMapper;
import xyz.jamesmok.minishop.entiiy.CartEntity;
import xyz.jamesmok.minishop.entiiy.OrderDetailEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Map<String, Object> getOrderDetailByUserId(int pageNum, int pageSize, long id) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderDetailEntity> pageInfo = new PageInfo<>(orderDetailMapper.getOrderDetailByOrderId(id));
        Long total = pageInfo.getTotal();
        List<OrderDetailEntity> cart = pageInfo.getList();
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
    public int deleteOrderDetailById(long id) {
        return orderDetailMapper.deleteOrderDetailById(id);
    }

    @Override
    public int batchDeleteOrderDetailById(int[] ids) {
        return orderDetailMapper.batchDeleteOrderDetailById(ids);
    }

    @Override
    public Map<String, Object> getOrderDetailBySalerId(int pageNum, int pageSize, long id) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderDetailEntity> pageInfo = new PageInfo<>(orderDetailMapper.getOrderDetailBySalerId(id));
        Long total = pageInfo.getTotal();
        List<OrderDetailEntity> cart = pageInfo.getList();
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
    public Map<String, Object> getOrderDetailByGoodId(int pageNum, int pageSize, long id) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderDetailEntity> pageInfo = new PageInfo<>(orderDetailMapper.getOrderDetailByGoodId(id));
        Long total = pageInfo.getTotal();
        List<OrderDetailEntity> cart = pageInfo.getList();
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
}
