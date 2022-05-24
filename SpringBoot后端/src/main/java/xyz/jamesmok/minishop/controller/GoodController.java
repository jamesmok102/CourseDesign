package xyz.jamesmok.minishop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.entiiy.GoodEntity;
import xyz.jamesmok.minishop.entiiy.SalerEntity;
import xyz.jamesmok.minishop.service.GoodService;
import xyz.jamesmok.minishop.service.RecordService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private RecordService recordService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/saler/insertGood")
    public Map<String, Object> insertGood(HttpServletRequest httpServletRequest, @RequestParam("name") String name, @RequestParam("category_id") long category_id, @RequestParam("intro") String intro, @RequestParam("pic")MultipartFile pic, @RequestParam("price") Double price, @RequestParam("count") long count, @RequestParam("saler_id") long saler_id) throws SQLException, IOException {
        //@RequestBody GoodEntity goodEntity
        String content = "销售员增加商品:"+name;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", goodService.insertGood(name, category_id, intro, pic, price, count, saler_id, 0));
        return map;
    }

    @GetMapping("/saler/good/{pageNum}")
    public Map<String, Object> getGoodAll(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "销售员取得商品第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = goodService.getGoodAll(pageNum, 5);
        return map;
    }

    @GetMapping("/admin/good0/{pageNum}")
    public Map<String, Object> getGoodAllCount0(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "管理员取得销售异常第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = goodService.getGoodAllCount0(pageNum, 5);
        return map;
    }

    @GetMapping("/admin/good/{pageNum}")
    public Map<String, Object> getGoodAllForAdmin(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "管理员取得商品第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = goodService.getGoodAll(pageNum, 5);
        return map;
    }

    @GetMapping("/user/good/{pageNum}")
    public Map<String, Object> getGoodAll_forUser(@PathVariable int pageNum) {
        Map<String, Object> map = goodService.getGoodAll(pageNum, 6);
        return map;
    }

    @GetMapping("/saler/good_bysaler/{id}/{pageNum}")
    public Map<String, Object> getGoodBySaler(HttpServletRequest httpServletRequest, @PathVariable int pageNum, @PathVariable long id) {
        //String username = (String) httpServletRequest.getAttribute("username");
        //logger.info(username);
        String content = "销售员取得销售员id:"+id+"所负责的商品第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = goodService.getGoodBySaler(pageNum, 5, id);
        return map;
    }

    @DeleteMapping("/saler/good/{id}")
    public Map<String, Object> deleteGoodById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员删除商品id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", goodService.deleteGoodById(id));
        return map;
    }

    @DeleteMapping("/saler/good/batchDelete")
    public Map<String, Object> batchDeleteGoodById(HttpServletRequest httpServletRequest, @RequestBody int[] ids) {
        String content = "销售员批量删除商品";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", goodService.batchDeleteGoodById(ids));
        return map;
    }

    @GetMapping("/saler/good_id/{id}")
    public Map<String, Object> getGoodById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员取得商品id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        GoodEntity good = goodService.getGoodById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", good.getId());
        map.put("name", good.getName());
        map.put("item", good.getItem());
        map.put("intro", good.getIntro());
        map.put("price", good.getPrice());
        map.put("count", good.getCount());
        map.put("saler_id", good.getSaler_id());
        return map;
    }

    @GetMapping("/user/good_id/{id}")
    public Map<String, Object> getGoodByIdFroUser(@PathVariable long id) {
        GoodEntity good = goodService.getGoodById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", good.getId());
        map.put("name", good.getName());
        map.put("item", good.getItem());
        map.put("intro", good.getIntro());
        map.put("price", good.getPrice());
        map.put("count", good.getCount());
        map.put("saler_id", good.getSaler_id());
        return map;
    }

    @GetMapping("/user/randomgood/{count}")
    public List<GoodEntity> getRandomGood(@PathVariable long count) {
        return goodService.getRandomGood(count);
    }

    @GetMapping("/queryGoodImg/{id}")
    public void queryGoodImg(HttpServletResponse response, @PathVariable long id) throws IOException {
        ServletOutputStream out = null;
        Map<String, Object> map = goodService.queryGoodImg(id);
        byte[] b = (byte[]) map.get("pic");
        InputStream is = new ByteArrayInputStream(b);
        response.setContentType("image/*");
        out = response.getOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = is.read(buf,0,1024)) != -1) {
            out.write(buf,0,len);
        }
        out.flush();
        out.close();
    }

    @PostMapping("/saler/updateGood")
    public Map<String, Object> updateGood(HttpServletRequest httpServletRequest, @RequestBody GoodEntity good) {
        String content = "销售员更新商品id:"+good.getId();
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", goodService.updateGood(good));
        return map;
    }

    @PostMapping("/saler/updateGoodImg")
    public Map<String, Object> updateGoodImg(@RequestParam("fileid") String fileid, @RequestParam("file") MultipartFile file) throws SQLException, IOException {
        // @RequestBody String fileid, @RequestBody MultipartFile file
        // @RequestParam("fileid") String fileid, @RequestParam("file") MultipartFile file
        Map<String, Object> map = new HashMap<>();
        map.put("status", goodService.updateGoodImg(fileid, file));
        return map;
    }

    @PostMapping("/user/updateBrowse/{id}")
    public Map<String, Object> updateCountOfBroweringGood(@PathVariable long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", goodService.updateCountOfBroweringGood(id));
        return map;
    }

    @GetMapping("admin/goodsell/{pageNum}")
    public Map<String, Object> getGoodSell(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "管理员取得商品出售情况第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = goodService.getGoodSell(pageNum, 5);
        return map;
    }

}
