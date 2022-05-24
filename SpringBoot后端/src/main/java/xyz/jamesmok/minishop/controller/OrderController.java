package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.jamesmok.minishop.entiiy.OrderEntity;
import xyz.jamesmok.minishop.service.OrderService;
import xyz.jamesmok.minishop.service.RecordService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/saler/order_byuser/{id}/{pageNum}")
    public Map<String, Object> getOrderByUser(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        String content = "销售员取得用户id:"+id+"的订单第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = orderService.getOrderByUserId(pageNum, 5, id);
        return map;
    }

    @GetMapping("/userislogin/order_byuser/{id}/{pageNum}")
    public Map<String, Object> getOrderByUserForUser(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        String content = "用户id:"+id+"取得自已的订单第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = orderService.getOrderByUserId(pageNum, 5, id);
        return map;
    }

    @DeleteMapping("/saler/order/{id}")
    public Map<String, Object> deleteOrderById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员删除订单id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", orderService.deleteOrderById(id));
        return map;
    }

    @DeleteMapping("/saler/order/batchDelete")
    public Map<String, Object> batchDeleteOrderById(HttpServletRequest httpServletRequest, @RequestBody int[] ids) {
        String content = "销售员批量删除订单";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", orderService.batchDeleteOrderById(ids));
        return map;
    }

    @PostMapping("/userislogin/insertOrder")
    public Map<String, Object> insertOrder(HttpServletRequest httpServletRequest, @RequestBody OrderEntity orderEntity) {
        String content = "用户提交订单";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", orderService.insertOrder(orderEntity));
        return map;
    }

}
