package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.jamesmok.minishop.entiiy.CartEntity;
import xyz.jamesmok.minishop.service.CartService;
import xyz.jamesmok.minishop.service.RecordService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/saler/cart/{pageNum}")
    public Map<String, Object> getCartAll(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "销售员取得购物车第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = cartService.getCartAll(pageNum, 5);
        return map;
    }

    @DeleteMapping("/saler/cart/{id}")
    public Map<String, Object> deleteGoodById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员删除购物车id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", cartService.deleteCartById(id));
        return map;
    }

    @DeleteMapping("/saler/cart/batchDelete")
    public Map<String, Object> batchDeleteCartById(HttpServletRequest httpServletRequest, @RequestBody int[] ids) {
        String content = "销售员批量删除购物车商品";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", cartService.batchDeleteCartById(ids));
        return map;
    }

    @DeleteMapping("/userislogin/cart/{id}")
    public Map<String, Object> deleteGoodByIdForUser(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "用户删除购物车id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", cartService.deleteCartById(id));
        return map;
    }

    @DeleteMapping("/userislogin/cart/batchDelete")
    public Map<String, Object> batchDeleteCartByIdForUser(HttpServletRequest httpServletRequest, @RequestBody int[] ids) {
        String content = "用户批量删除购物车";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", cartService.batchDeleteCartById(ids));
        return map;
    }

    @GetMapping("/saler/cart_byuser/{id}/{pageNum}")
    public Map<String, Object> getCartByUserIdAll(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        String content = "销售员取得用户id:"+id+"的购物车商品第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = cartService.getCartByUserId(pageNum, 5, id);
        return map;
    }

    @GetMapping("/userislogin/cart_byuser/{id}/{pageNum}")
    public Map<String, Object> getCartByUserIdAllForUser(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        String content = "用户id:"+id+"取得购物车的商品第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = cartService.getCartByUserId(pageNum, 5, id);
        return map;
    }

    @PostMapping("/userislogin/dealwithcart")
    public Map<String, Object> userDealwithCart(HttpServletRequest httpServletRequest, @RequestBody CartEntity cart) {
        String content = "用户增加商品id:"+cart.getGood_id()+"到购物车";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", cartService.UserDealwithCart(cart));
        return map;
    }

    @PostMapping("/userislogin/cart/selected/{user_id}")
    public Map<String, Object> changeSelectedById(HttpServletRequest httpServletRequest, @RequestBody List<Long> ids, @PathVariable Long user_id) {
        String content = "用户:"+user_id+"改变已选择的购物车";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", cartService.changeSelectedById(ids, user_id));
        return map;
    }

}
