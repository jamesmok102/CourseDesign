package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jamesmok.minishop.Mapper.RecordMapper;
import xyz.jamesmok.minishop.entiiy.GoodEntity;
import xyz.jamesmok.minishop.entiiy.Record;
import xyz.jamesmok.minishop.util.IpUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Service
public class RecordServiceImpl implements RecordService{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public int insertRecord(HttpServletRequest httpServletRequest, String content) {
        String role = (String) httpServletRequest.getAttribute("role");
        Record record = new Record();
        record.setContent(content);
        if (Objects.equals(role, "saler")) {
            record.setName((String) httpServletRequest.getAttribute("salername"));
        } else if (Objects.equals(role, "user")) {
            record.setName((String) httpServletRequest.getAttribute("username"));
        } else if (Objects.equals(role, "admin")) {
            record.setName((String) httpServletRequest.getAttribute("adminname"));
        }
        Date date = new Date();
        record.setTime(new Timestamp(date.getTime()));
        record.setIp(IpUtil.getIpAddr(httpServletRequest));
        return recordMapper.insertRecord(record);
    }

    @Override
    public Map<String, Object> getAllRecord(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Record> pageInfo = new PageInfo<>(recordMapper.getAllRecord());
        Long total = pageInfo.getTotal();
        List<Record> record = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", record);
        return map;
    }
}
