package xyz.jamesmok.minishop.service;

import xyz.jamesmok.minishop.entiiy.LogRecordEntity;

import java.util.Map;

public interface LogRecordService {

    int insertLogRecord(LogRecordEntity logRecordEntity);

    Map<String, Object> getLogRecordAll(int pageNum, int pageSize);

}
