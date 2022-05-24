package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.Mapper.SalerMapper;
import xyz.jamesmok.minishop.entiiy.SalerEntity;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SalerServiceImpl implements SalerService {

    @Autowired
    private SalerMapper salerMapper;

    @Override
    public Map<String, Object> getSalerAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SalerEntity> pageInfo = new PageInfo<>(salerMapper.getSalerAll());
        Long total = pageInfo.getTotal();
        List<SalerEntity> users = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", users);
        return map;
    }

    @Override
    public int deleteSalerById(long id) {
        return salerMapper.deleteSalerById(id);
    }

    @Override
    public int batchDeleteSalerById(int[] ids) {
        return salerMapper.batchDeleteSalerById(ids);
    }

    @Override
    public int insertSaler(String name, String email, String password, String phone, MultipartFile avatar) throws IOException, SQLException {
//        SalerEntity saler = new SalerEntity();
//        saler.setName(name);
//        saler.setEmail(email);
//        saler.setPassword(password);
//        saler.setPhone(phone);
        Blob blob = new SerialBlob(avatar.getBytes());
//        saler.setAvatar(blob);

        return salerMapper.insertSaler(name, email, password, phone, blob);
    }

    @Override
    public int insertImgTest(MultipartFile file) throws IOException, SQLException {

        String id = String.valueOf(UUID.randomUUID());
        Blob blob = null;
        blob = new SerialBlob(file.getBytes());

        return salerMapper.insertImgTest(id, blob);
    }

    @Override
    public Map<String, Object> queryImgTest(String fileid) {
        return salerMapper.queryImgTest(fileid);
    }

    @Override
    public SalerEntity getSalerById(long id) {
        return salerMapper.getSalerById(id);
    }

    @Override
    public int updateSaler(SalerEntity saler) {
        return salerMapper.updateSaler(saler);
    }

    @Override
    public int updateSalerImg(String fileid, MultipartFile file) throws IOException, SQLException {
        Blob blob = null;
        blob = new SerialBlob(file.getBytes());

        return salerMapper.updateSalerImg(fileid, blob);
    }

    @Override
    public Map<String, Object> querySalerImg(long fileid) {
        return salerMapper.querySalerImg(fileid);
    }

    @Override
    public SalerEntity getSalerByNmae(String name) {
        return salerMapper.getSalerByName(name);
    }
}
