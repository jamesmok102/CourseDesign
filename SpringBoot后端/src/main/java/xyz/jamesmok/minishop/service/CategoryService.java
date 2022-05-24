package xyz.jamesmok.minishop.service;

import xyz.jamesmok.minishop.entiiy.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    int insertCategory(CategoryEntity category);

    Map<String, Object> getCategoryAll(int pageNum, int pageSize);

    int deleteCategoryById(long id);

    int batchDeleteCategoryById(int[] ids);

    List<CategoryEntity> getCategoryAllNoPage();

    Map<String, Object> CategoryOrder(int pageNum, int pageSize);

}
