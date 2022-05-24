package xyz.jamesmok.minishop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.jamesmok.minishop.entiiy.Admin;
import xyz.jamesmok.minishop.entiiy.LogRecordEntity;
import xyz.jamesmok.minishop.entiiy.SalerEntity;
import xyz.jamesmok.minishop.service.LogRecordService;
import xyz.jamesmok.minishop.service.SalerService;
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
public class SalerLoginController {

    @Autowired
    private SalerService salerService;

    @Autowired
    private LogRecordService logRecordService;

    @PostMapping("/salerLogin")
    public Map<String, Object> login(HttpServletRequest httpServletRequest, @RequestBody SalerEntity saler, HttpServletResponse httpServletResponse){
        SalerEntity sqlSaler = salerService.getSalerByNmae(saler.getName());
        if (sqlSaler.getPassword().equals(saler.getPassword())) {
            String jwt = JwtUtil.createToken2(sqlSaler.getId(), sqlSaler.getName());
            httpServletResponse.setHeader("Authorization2", "Bearer " + jwt);
            Map<String, Object> map = new HashMap<>();
            map.put("token", jwt);
            map.put("id", sqlSaler.getId());

            LogRecordEntity logRecordEntity = new LogRecordEntity();
            logRecordEntity.setName(saler.getName());
            logRecordEntity.setRole("销售员");
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
