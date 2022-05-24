package xyz.jamesmok.minishop.service;

import java.util.Map;

public interface OrderDetailService {

    Map<String, Object> getOrderDetailByUserId(int pageNum, int pageSize, long id);

    int deleteOrderDetailById(long id);

    int batchDeleteOrderDetailById(int[] ids);

    Map<String, Object> getOrderDetailBySalerId(int pageNum, int pageSize, long id);

    Map<String, Object> getOrderDetailByGoodId(int pageNum, int pageSize, long id);

}
