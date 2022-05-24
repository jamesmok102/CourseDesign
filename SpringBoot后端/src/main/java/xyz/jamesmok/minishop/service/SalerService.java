package xyz.jamesmok.minishop.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.entiiy.SalerEntity;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

public interface SalerService {

    Map<String, Object> getSalerAll(int pageNum, int pageSize);

    int deleteSalerById(long id);

    int batchDeleteSalerById(int[] ids);

    int insertSaler(String name, String email, String password, String phone, MultipartFile avatar) throws IOException, SQLException;

    int insertImgTest(MultipartFile file) throws IOException, SQLException;

    Map<String, Object> queryImgTest(String fileid);

    SalerEntity getSalerById(long id);

    int updateSaler(SalerEntity saler);

    int updateSalerImg(String fileid, MultipartFile file) throws IOException, SQLException;

    Map<String, Object> querySalerImg(long fileid);

    SalerEntity getSalerByNmae(String name);

}
