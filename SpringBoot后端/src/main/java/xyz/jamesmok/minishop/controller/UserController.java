package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.entiiy.SalerEntity;
import xyz.jamesmok.minishop.entiiy.UserEntity;
import xyz.jamesmok.minishop.service.RecordService;
import xyz.jamesmok.minishop.service.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    @PostMapping("/saler/user/insertUser")
    public Map<String, Object> insertUser(HttpServletRequest httpServletRequest, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("phone") String phone, @RequestParam("area") String area, @RequestParam("age") long age, @RequestParam("avatar") MultipartFile avatar) throws IOException, SQLException {
        String content = "销售员增加用户name:"+name;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", userService.insertUser(name, email, password, phone, area, age, avatar));
        return map;
    }

    @PostMapping("/user/user/insertUser")
    public Map<String, Object> insertUserForUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("phone") String phone, @RequestParam("area") String area, @RequestParam("age") long age, @RequestParam("avatar") MultipartFile avatar) throws IOException, SQLException {
        Map<String, Object> map = new HashMap<>();
        map.put("status", userService.insertUser(name, email, password, phone, area, age, avatar));
        return map;
    }

    @GetMapping("/saler/user/{pageNum}")
    public Map<String, Object> getUserAll(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "销售员取得所有用户第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = userService.getUserAll(pageNum, 5);
        return map;
    }

    @DeleteMapping("/saler/user/{id}")
    public Map<String, Object> deleteUserById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员删除用户id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", userService.deleteUserById(id));
        return map;
    }

    @DeleteMapping("/saler/user/batchDelete")
    public Map<String, Object> batchDeleteSalerById(HttpServletRequest httpServletRequest, @RequestBody int[] ids) {
        String content = "销售员批量删除用户";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", userService.batchDeleteUserById(ids));
        return map;
    }

    @GetMapping("/saler/user_id/{id}")
    public Map<String, Object> getUserById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员取得用户id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        UserEntity user = userService.getUserById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("phone", user.getPhone());
        map.put("area", user.getArea());
        map.put("age", user.getAge());
        return map;
    }

    @GetMapping("/userislogin/user_id/{id}")
    public Map<String, Object> getUserByIdForUser(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "用户取得自己的个人资料";
        recordService.insertRecord(httpServletRequest, content);
        UserEntity user = userService.getUserById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("phone", user.getPhone());
        map.put("area", user.getArea());
        map.put("age", user.getAge());
        return map;
    }

    @PostMapping("/saler/updateUser")
    public Map<String, Object> updateUser(HttpServletRequest httpServletRequest, @RequestBody UserEntity user) {
        String content = "销售员更新用户id:"+user.getId();
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", userService.updateUser(user));
        return map;
    }

    @PostMapping("/saler/updateUserImg")
    public Map<String, Object> updateUserImg(@RequestParam("fileid") String fileid, @RequestParam("file") MultipartFile file) throws SQLException, IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("status", userService.updateUserImg(fileid, file));
        return map;
    }

    @PostMapping("/userislogin/updateUser")
    public Map<String, Object> updateUserForUser(HttpServletRequest httpServletRequest, @RequestBody UserEntity user) {
        String content = "用户更新自已的资料";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", userService.updateUser(user));
        return map;
    }

    @PostMapping("/userislogin/updateUserImg")
    public Map<String, Object> updateUserImgForUser(@RequestParam("fileid") String fileid, @RequestParam("file") MultipartFile file) throws SQLException, IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("status", userService.updateUserImg(fileid, file));
        return map;
    }

    @GetMapping("/queryUserImg/{id}")
    public void queryUserImg(HttpServletResponse response, @PathVariable long id) throws SQLException, IOException {
        ServletOutputStream out = null;
        Map<String, Object> map = userService.queryUserImg(id);
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
