package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import xyz.jamesmok.minishop.entiiy.GoodEntity;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Mapper
public interface GoodMapper {

    @Insert("INSERT INTO good(name, category_id, intro, pic, price, count, saler_id, browse) " + "VALUES(#{name}, #{category_id}, #{intro}, #{pic}, #{price}, #{count}, #{saler_id}, #{browse})")
    //@Options(useGeneratedKeys = true, keyProperty = "id")
    int insertGood(String name, long category_id, String intro, Blob pic, double price, long count, long saler_id, long browse);

    //@Select("SELECT * FROM good")
    @Select("select good.id,good.name,category.item,good.intro,good.browse,good.pic,good.saler_id,good.price,good.count,salers.name salername from good,category,salers where category_id = category.id and saler_id = salers.id")
    Page<GoodEntity> getGoodAll();

    @Select("select DISTINCT good.id,good.name,category.item,good.intro,good.browse,good.pic,good.saler_id,good.price,good.count,salers.name salername from good,category,salers where category_id = category.id and good.saler_id = salers.id and good.saler_id=#{id}")
    Page<GoodEntity> getGoodBySaler(long id);

    @Select("select good.id,good.name,category.item,good.intro,good.browse,good.pic,good.saler_id,good.price,good.count from good,category where category_id = category.id and good.id = #{id}")
    GoodEntity getGoodById(long id);

    @Select("select good.id,good.name,category.item,good.intro,good.browse,good.pic,good.saler_id,good.price,good.count,salers.name salername from good,category,salers where category_id = category.id and saler_id = salers.id order by rand() limit #{count}")
    List<GoodEntity> getRandomGood(long count);

    @Delete("DELETE FROM good WHERE id = #{id}")
    int deleteGoodById(long id);

    @Delete("<script>DELETE FROM good WHERE id in <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int batchDeleteGoodById(int[] ids);

    @Select("SELECT pic FROM good WHERE id=#{fileid}")
    Map<String, Object> queryGoodImg(long fileid);

    //@Update("UPDATE good SET name=#{name}, category_id, intro, price, count) " + "VALUES(#{name}, #{category_id}, #{intro}, #{price}, #{count})")
    @Update("UPDATE good SET name=#{name},category_id=#{category_id},intro=#{intro},price=#{price},count=#{count} WHERE id = #{id}")
    int updateGood(GoodEntity good);

    @Update("UPDATE good SET count=#{count} WHERE id = #{id}")
    int updateGoodCount(GoodEntity good);

    @Update("UPDATE good SET pic=#{file} WHERE id=#{fileid}")
    int updateGoodImg(String fileid, Blob file);

    @Update("update good set browse=browse+1 where id=#{id}")
    int updateCountOfBroweringGood(long id);

    @Select("select good.id,good.name,category.item,good.intro,good.browse,good.pic,good.saler_id,good.price,good.count,salers.name salername from good,category,salers where category_id = category.id and saler_id = salers.id and good.count=0")
    Page<GoodEntity> getGoodAllCount0();




}
