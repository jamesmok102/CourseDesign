package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import xyz.jamesmok.minishop.entiiy.SalerEntity;
import xyz.jamesmok.minishop.entiiy.UserEntity;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(name, password, email, phone, avatar, area, age) " + "VALUES(#{name}, #{password}, #{email}, #{phone}, #{avatar}, #{area}, #{age})")
    int insertUser(String name, String email, String password, String phone, String area, long age, Blob avatar);

    @Select("SELECT * FROM user")
    Page<UserEntity> getUserAll();

    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUserById(long id);

    @Delete("<script>DELETE FROM user WHERE id in <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int batchDeleteUserById(int[] ids);

    @Select("SELECT id,name,email,password,phone,area,age FROM user WHERE id = #{id}")
    UserEntity getUserById(long id);

    @Update("UPDATE user SET name=#{name},password=#{password},email=#{email},phone=#{phone},area=#{area} WHERE id = #{id}")
    int updateUser(UserEntity user);

    @Update("UPDATE user SET avatar=#{file} WHERE id=#{fileid}")
    int updateUserImg(String fileid, Blob file);

    @Select("SELECT avatar FROM user WHERE id=#{fileid}")
    Map<String, Object> queryUserImg(long fileid);

    @Select("select * from user where name=#{name}")
    UserEntity getUserByName(String name);

}
