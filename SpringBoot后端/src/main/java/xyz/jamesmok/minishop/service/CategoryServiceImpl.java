package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jamesmok.minishop.Mapper.CategoryMapper;
import xyz.jamesmok.minishop.Mapper.OrderDetailMapper;
import xyz.jamesmok.minishop.entiiy.CategoryEntity;
import xyz.jamesmok.minishop.entiiy.OrderDetailEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public int insertCategory(CategoryEntity category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public Map<String, Object> getCategoryAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<CategoryEntity> pageInfo = new PageInfo<>(categoryMapper.getCategoryAll());
        Long total = pageInfo.getTotal();
        List<CategoryEntity> category = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", category);
        return map;

    }

    @Override
    public int deleteCategoryById(long id) {
        return categoryMapper.deleteCategoryById(id);
    }

    @Override
    public int batchDeleteCategoryById(int[] ids) {
        return categoryMapper.batchDeleteCategoryById(ids);
    }

    @Override
    public List<CategoryEntity> getCategoryAllNoPage() {
        return categoryMapper.getCategoryAllNoPage();
    }

    @Override
    public Map<String, Object> CategoryOrder(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<CategoryEntity> categoryPageInfo = new PageInfo<>(categoryMapper.getCategoryAll());
        List<CategoryEntity> category = categoryPageInfo.getList();
        List<CategoryEntity> categoryOrder = new ArrayList<>();
        for (CategoryEntity c : category) {
            PageHelper.startPage(pageNum, pageSize);
            PageInfo<OrderDetailEntity> orderDetailPageInfo = new PageInfo<>(orderDetailMapper.getOrderDetailByCategoryId(c.getId()));
            List<OrderDetailEntity> orderDetail = orderDetailPageInfo.getList();
            int count = 0;
            for (OrderDetailEntity od : orderDetail) {
                count += od.getCount();
            }
            CategoryEntity temp = new CategoryEntity();
            temp.setItem(c.getItem());
            temp.setCount(count);
            categoryOrder.add(temp);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", categoryOrder);
        map.put("PageLength", categoryPageInfo.getPages());
        map.put("PageSize", categoryPageInfo.getSize());
        map.put("PageTotal", categoryPageInfo.getTotal());
        return map;
    }
}
