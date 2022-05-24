package xyz.jamesmok.minishop.service;

import xyz.jamesmok.minishop.entiiy.BrowseRecordEntity;

import java.util.Map;

public interface BrowseRecordService {

    int insertBrowseRecord(BrowseRecordEntity browseRecordEntity);

    Map<String, Object> getBrowseRecordByUserId(int pageNum, int pageSize, long id);

}
