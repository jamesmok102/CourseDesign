package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.entiiy.SalerEntity;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Mapper
public interface SalerMapper {

    @Select("SELECT * FROM salers")
    Page<SalerEntity> getSalerAll();


    @Select("SELECT id,name,email,password,phone FROM salers WHERE id = #{id}")
    SalerEntity getSalerById(long id);

    @Select("SELECT id,name,email,password,phone FROM salers WHERE name = #{name}")
    SalerEntity getSalerByName(String name);

    @Delete("DELETE FROM salers WHERE id = #{id}")
    int deleteSalerById(long id);

    @Delete("<script>DELETE FROM salers WHERE id in <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int batchDeleteSalerById(int[] ids);

//    @Insert("INSERT INTO salers(name, password, email, phone) " + "VALUES(#{name}, #{password}, #{email}, #{phone})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    int insertSaler(SalerEntity saler);

    @Insert("INSERT INTO salers(name, password, email, phone, avatar) " + "VALUES(#{name}, #{password}, #{email}, #{phone}, #{avatar})")
    //@Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSaler(String name, String email, String password, String phone, Blob avatar);

    @Update("UPDATE salers SET name=#{name},password=#{password},email=#{email},phone=#{phone} WHERE id = #{id}")
    int updateSaler(SalerEntity saler);

    @Update("UPDATE salers SET avatar=#{file} WHERE id=#{fileid}")
    int updateSalerImg(String fileid, Blob file);

    @Select("SELECT avatar FROM salers WHERE id=#{fileid}")
    Map<String, Object> querySalerImg(long fileid);

    @Insert("INSERT INTO imgfile(fileid, file) VALUES(#{fileid},#{file})")
    int insertImgTest(String fileid, Blob file);

    @Select("SELECT file FROM imgfile WHERE fileid = #{fileId}")
    Map<String, Object> queryImgTest(String fileid);

}
