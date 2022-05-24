package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import xyz.jamesmok.minishop.entiiy.CategoryEntity;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT INTO category(item) " + "VALUES(#{item})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCategory(CategoryEntity category);

    @Select("SELECT * FROM category")
    Page<CategoryEntity> getCategoryAll();

    @Select("SELECT * FROM category")
    List<CategoryEntity> getCategoryAllNoPage();

    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteCategoryById(long id);

    @Delete("<script>DELETE FROM category WHERE id in <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int batchDeleteCategoryById(int[] ids);

}
