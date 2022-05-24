package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.jamesmok.minishop.entiiy.Admin;
import xyz.jamesmok.minishop.entiiy.LogRecordEntity;
import xyz.jamesmok.minishop.service.LogRecordService;
import xyz.jamesmok.minishop.util.IpUtil;
import xyz.jamesmok.minishop.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AdminLoginController {

    private final String USERNAME = "admin";
    private final String PASSWORD = "123123";

    @Autowired
    private LogRecordService logRecordService;

    @PostMapping("/adminLogin")
    public Map<String, Object> login(HttpServletRequest httpServletRequest, @RequestBody Admin admin, HttpServletResponse httpServletResponse){
        if (USERNAME.equals(admin.getUsername()) && PASSWORD.equals(admin.getPassword())) {
            String jwt = JwtUtil.createToken();
            httpServletResponse.setHeader("Authorization", "Bearer " + jwt);
            Map<String, Object> map = new HashMap<>();
            map.put("token", jwt);

            LogRecordEntity logRecordEntity = new LogRecordEntity();
            logRecordEntity.setName(USERNAME);
            logRecordEntity.setRole("管理员");
            logRecordEntity.setState("登入");
            logRecordEntity.setIp(IpUtil.getIpAddr(httpServletRequest));
            Date date = new Date();
            logRecordEntity.setTime(new Timestamp(date.getTime()));
            logRecordService.insertLogRecord(logRecordEntity);

            return map;

        }
        return null;
    }


}
