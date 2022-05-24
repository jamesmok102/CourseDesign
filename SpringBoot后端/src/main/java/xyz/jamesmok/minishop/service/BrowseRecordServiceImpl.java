package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jamesmok.minishop.Mapper.BrowseRecordMapper;
import xyz.jamesmok.minishop.entiiy.BrowseRecordEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrowseRecordServiceImpl implements BrowseRecordService{

    @Autowired
    private BrowseRecordMapper browseRecordMapper;

    @Override
    public int insertBrowseRecord(BrowseRecordEntity browseRecordEntity) {
        return browseRecordMapper.insertBrowseRecord(browseRecordEntity);
    }

    @Override
    public Map<String, Object> getBrowseRecordByUserId(int pageNum, int pageSize, long id) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<BrowseRecordEntity> pageInfo = new PageInfo<>(browseRecordMapper.getBrowseRecordByUserId(id));
        Long total = pageInfo.getTotal();
        List<BrowseRecordEntity> browseRecord = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", browseRecord);
        return map;
    }
}
