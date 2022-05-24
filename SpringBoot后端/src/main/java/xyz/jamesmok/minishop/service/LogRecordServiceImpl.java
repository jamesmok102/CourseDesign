package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.jamesmok.minishop.Mapper.LogRecordMapper;
import xyz.jamesmok.minishop.entiiy.LogRecordEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogRecordServiceImpl implements LogRecordService{

    @Autowired
    private LogRecordMapper logRecordMapper;

    @Override
    public int insertLogRecord(LogRecordEntity logRecordEntity) {
        return logRecordMapper.insertLogRecord(logRecordEntity);
    }

    @Override
    public Map<String, Object> getLogRecordAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<LogRecordEntity> pageInfo = new PageInfo<>(logRecordMapper.getLogRecordAll());
        Long total = pageInfo.getTotal();
        List<LogRecordEntity> logRecord = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", logRecord);
        return map;
    }


}
