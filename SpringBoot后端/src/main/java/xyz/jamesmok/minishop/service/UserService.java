package xyz.jamesmok.minishop.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.entiiy.SalerEntity;
import xyz.jamesmok.minishop.entiiy.UserEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface UserService {

    int insertUser(String name, String email, String password, String phone, String area, long age, MultipartFile avatar) throws IOException, SQLException;

    Map<String, Object> getUserAll(int pageNum, int pageSize);

    int deleteUserById(long id);

    int batchDeleteUserById(int[] ids);

    UserEntity getUserById(long id);

    int updateUser(UserEntity user);

    int updateUserImg(String fileid, MultipartFile file) throws IOException, SQLException;

    Map<String, Object> queryUserImg(long fileid);

    UserEntity getUserByName(String name);

}
