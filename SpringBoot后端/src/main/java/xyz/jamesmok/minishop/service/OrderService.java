package xyz.jamesmok.minishop.service;

import xyz.jamesmok.minishop.entiiy.OrderEntity;

import java.util.Map;

public interface OrderService {

    Map<String, Object> getOrderByUserId(int pageNum, int pageSize, long id);

    int deleteOrderById(long id);

    int batchDeleteOrderById(int[] ids);

    int insertOrder(OrderEntity orderEntity);

}
