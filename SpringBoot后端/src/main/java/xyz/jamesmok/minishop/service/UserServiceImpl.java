package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.Mapper.UserMapper;
import xyz.jamesmok.minishop.entiiy.SalerEntity;
import xyz.jamesmok.minishop.entiiy.UserEntity;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(String name, String email, String password, String phone, String area, long age, MultipartFile avatar) throws IOException, SQLException {
        Blob blob = new SerialBlob(avatar.getBytes());
        return userMapper.insertUser(name, email, password, phone, area, age, blob);
    }

    @Override
    public Map<String, Object> getUserAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UserEntity> pageInfo = new PageInfo<>(userMapper.getUserAll());
        Long total = pageInfo.getTotal();
        List<UserEntity> users = pageInfo.getList();
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
    public int deleteUserById(long id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int batchDeleteUserById(int[] ids) {
        return userMapper.batchDeleteUserById(ids);
    }

    @Override
    public UserEntity getUserById(long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int updateUser(UserEntity user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserImg(String fileid, MultipartFile file) throws IOException, SQLException {
        Blob blob = null;
        blob = new SerialBlob(file.getBytes());
        return userMapper.updateUserImg(fileid, blob);
    }

    @Override
    public Map<String, Object> queryUserImg(long fileid) {
        return userMapper.queryUserImg(fileid);
    }

    @Override
    public UserEntity getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
