package xyz.jamesmok.minishop.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.entiiy.GoodEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GoodService {

    int insertGood(String name, long category_id, String intro, MultipartFile pic, Double price, long count, long saler_id, long browse) throws IOException, SQLException;

    Map<String, Object> getGoodAll(int pageNum, int pageSize);

    Map<String, Object> getGoodBySaler(int pageNum, int pageSize, long id);

    int deleteGoodById(long id);

    int batchDeleteGoodById(int[] ids);

    GoodEntity getGoodById(long id);

    Map<String, Object> queryGoodImg(long fileid);

    int updateGood(GoodEntity good);

    int updateGoodImg(String fileid, MultipartFile file) throws IOException, SQLException;

    List<GoodEntity> getRandomGood(long count);

    int updateCountOfBroweringGood(long id);

    Map<String, Object> getGoodAllCount0(int pageNum, int pageSize);

    Map<String, Object> getGoodSell(int pageNum, int pageSize);

}
