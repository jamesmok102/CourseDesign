package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.jamesmok.minishop.entiiy.LogRecordEntity;
import xyz.jamesmok.minishop.entiiy.UserEntity;
import xyz.jamesmok.minishop.service.LogRecordService;
import xyz.jamesmok.minishop.service.UserService;
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
public class UserLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogRecordService logRecordService;

    @PostMapping("/userlogin")
    public Map<String, Object> login(HttpServletRequest httpServletRequest, @RequestBody UserEntity user, HttpServletResponse httpServletResponse) {
        UserEntity sqlUser = userService.getUserByName(user.getName());
        if (sqlUser.getPassword().equals(user.getPassword())) {
            String jwt = JwtUtil.createToken3(sqlUser.getId(), sqlUser.getName());
            httpServletResponse.setHeader("Authorization3", "Bearer " + jwt);
            Map<String, Object> map = new HashMap<>();
            map.put("token", jwt);
            map.put("id", sqlUser.getId());

            LogRecordEntity logRecordEntity = new LogRecordEntity();
            logRecordEntity.setName(user.getName());
            logRecordEntity.setRole("用户");
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
