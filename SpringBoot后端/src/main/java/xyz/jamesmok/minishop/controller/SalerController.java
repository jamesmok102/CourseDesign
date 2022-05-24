package xyz.jamesmok.minishop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.entiiy.Admin;
import xyz.jamesmok.minishop.entiiy.SalerEntity;
import xyz.jamesmok.minishop.service.RecordService;
import xyz.jamesmok.minishop.service.SalerService;
import xyz.jamesmok.minishop.util.JwtUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SalerController {

//    private final String USERNAME = "admin";
//    private final String PASSWORD = "123123";

    @Autowired
    private SalerService salerService;

    @Autowired
    private RecordService recordService;

//    @PostMapping("/adminLogin")
//    public Map<String, Object> login(@RequestBody Admin admin, HttpServletResponse httpServletResponse){
//        if (USERNAME.equals(admin.getUsername()) && PASSWORD.equals(admin.getPassword())) {
//            String jwt = JwtUtil.createToken();
//            httpServletResponse.setHeader("Authorization", "Bearer " + jwt);
//            Map<String, Object> map = new HashMap<>();
//            map.put("token", jwt);
//            return map;
//
//        }
//        return null;
//    }

    @GetMapping("/admin/salers/{pageNum}")
    public Map<String, Object> getAll(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "管理员取得所有销售员第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = salerService.getSalerAll(pageNum, 5);
        return map;
    }

    @DeleteMapping("/admin/salers/{id}")
    public Map<String, Object> deleteSalerById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "管理员删除销售员id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", salerService.deleteSalerById(id));
        return map;
    }

    @DeleteMapping("/admin/salers/batchDelete")
    public Map<String, Object> batchDeleteSalerById(HttpServletRequest httpServletRequest, @RequestBody int[] ids) {
        String content = "管理员批量删除销售员";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", salerService.batchDeleteSalerById(ids));
        return map;
    }

    @PostMapping("/admin/salers/insertSaler")
    public Map<String, Object> insertSaler(HttpServletRequest httpServletRequest, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("phone") String phone, @RequestParam("avatar") MultipartFile avatar) throws IOException, SQLException {
        //@RequestBody SalerEntity saler
        String content = "管理员增加销售员name:"+name;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", salerService.insertSaler(name, email, password, phone, avatar));
        return map;
    }

    @GetMapping("/admin/saler_id/{id}")
    public Map<String, Object> getSalerById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "管理员取得销售员id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        SalerEntity saler = salerService.getSalerById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", saler.getId());
        map.put("name", saler.getName());
        map.put("email", saler.getEmail());
        map.put("password", saler.getPassword());
        map.put("phone", saler.getPhone());
        return map;
    }

    @GetMapping("/saler/saler_id/{id}")
    public Map<String, Object> getSalerById_forSaler(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员取得销售员id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        SalerEntity saler = salerService.getSalerById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", saler.getId());
        map.put("name", saler.getName());
        map.put("email", saler.getEmail());
        map.put("password", saler.getPassword());
        map.put("phone", saler.getPhone());
        return map;
    }

    @PostMapping("/admin/updateSaler")
    public Map<String, Object> updateSaler(HttpServletRequest httpServletRequest, @RequestBody SalerEntity saler) {
        String content = "管理员更新销售员id:"+saler.getId();
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", salerService.updateSaler(saler));
        return map;
    }

    @PostMapping("/admin/updateSalerImg")
    public Map<String, Object> updateSalerImg(@RequestParam("fileid") String fileid, @RequestParam("file") MultipartFile file) throws SQLException, IOException {
        // @RequestBody String fileid, @RequestBody MultipartFile file
        // @RequestParam("fileid") String fileid, @RequestParam("file") MultipartFile file
        Map<String, Object> map = new HashMap<>();
        map.put("status", salerService.updateSalerImg(fileid, file));
        return map;
    }

    @GetMapping("/querySalerImg/{id}")
    public void querySalerImg(HttpServletResponse response, @PathVariable long id) throws SQLException, IOException {
        ServletOutputStream out = null;
        Map<String, Object> map = salerService.querySalerImg(id);
        byte[] b = (byte[]) map.get("avatar");
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

}
