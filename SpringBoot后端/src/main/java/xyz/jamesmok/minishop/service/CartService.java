package xyz.jamesmok.minishop.service;

import org.springframework.stereotype.Service;
import xyz.jamesmok.minishop.entiiy.CartEntity;

import java.util.List;
import java.util.Map;

public interface CartService {

    Map<String, Object> getCartAll(int pageNum, int pageSize);

    int deleteCartById(long id);

    int batchDeleteCartById(int[] ids);

    Map<String, Object> getCartByUserId(int pageNum, int pageSize, long id);

    int UserDealwithCart(CartEntity cart);

    int changeSelectedById(List<Long> ids, Long user_id);

}
