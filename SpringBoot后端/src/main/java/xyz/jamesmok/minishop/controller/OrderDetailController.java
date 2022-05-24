package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.jamesmok.minishop.service.OrderDetailService;
import xyz.jamesmok.minishop.service.RecordService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/saler/orderdetail_byorder/{id}/{pageNum}")
    public Map<String, Object> getOrderDetailByOrderId(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        String content = "销售员取得订单id:"+id+"的所有订单详情第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = orderDetailService.getOrderDetailByUserId(pageNum, 5, id);
        return map;
    }

    @GetMapping("/admin/orderdetail_bysaler/{id}/{pageNum}")
    public Map<String, Object> getOrderDetailBySalerIdForAdmin(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        String content = "管理员取得销售员id:"+id+"的业绩第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = orderDetailService.getOrderDetailBySalerId(pageNum, 5, id);
        return map;
    }

    @GetMapping("/admin/orderdetail_bygood/{id}/{pageNum}")
    public Map<String, Object> getOrderDetailByGoodIdForAdmin(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        String content = "管理员取得商品id:"+id+"出售记录第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = orderDetailService.getOrderDetailByGoodId(pageNum, 5, id);
        return map;
    }

    @GetMapping("/userislogin/orderdetail_byorder/{id}/{pageNum}")
    public Map<String, Object> getOrderDetailByOrderIdForUser(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        String content = "用户取得订单id:"+id+"的订单详情商品第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = orderDetailService.getOrderDetailByUserId(pageNum, 5, id);
        return map;
    }



    @DeleteMapping("/saler/orderdetail/{id}")
    public Map<String, Object> deleteGoodById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员删除订单详情id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", orderDetailService.deleteOrderDetailById(id));
        return map;
    }

    @DeleteMapping("/saler/orderdetail/batchDelete")
    public Map<String, Object> batchDeleteCartById(HttpServletRequest httpServletRequest, @RequestBody int[] ids) {
        String content = "销售员批量删除订单详情";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", orderDetailService.batchDeleteOrderDetailById(ids));
        return map;
    }

}
