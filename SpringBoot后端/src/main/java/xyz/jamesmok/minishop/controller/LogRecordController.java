package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.jamesmok.minishop.entiiy.LogRecordEntity;
import xyz.jamesmok.minishop.service.LogRecordService;
import xyz.jamesmok.minishop.service.RecordService;
import xyz.jamesmok.minishop.util.IpUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class LogRecordController {

    @Autowired
    private LogRecordService logRecordService;

    @Autowired
    private RecordService recordService;

    @PostMapping("/logrecord")
    public Map<String, Object> insertLogRecord(HttpServletRequest httpServletRequest, @RequestBody LogRecordEntity logRecordEntity) {
        Map<String, Object> map = new HashMap<>();
        logRecordEntity.setIp(IpUtil.getIpAddr(httpServletRequest));
        Date date = new Date();
        logRecordEntity.setTime(new Timestamp(date.getTime()));
        map.put("status", logRecordService.insertLogRecord(logRecordEntity));
        return map;
    }

    @GetMapping("/admin/logrecord/{pageNum}")
    public Map<String, Object> getLogRecordAll(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "管理员取得登入登出记录第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = logRecordService.getLogRecordAll(pageNum, 10);
        return map;
    }


}
