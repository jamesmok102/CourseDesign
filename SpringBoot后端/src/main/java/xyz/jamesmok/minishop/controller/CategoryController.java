package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.jamesmok.minishop.entiiy.CategoryEntity;
import xyz.jamesmok.minishop.service.CategoryService;
import xyz.jamesmok.minishop.service.RecordService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RecordService recordService;

    @PostMapping("/saler/insertCategory")
    public Map<String, Object> insertCategory(HttpServletRequest httpServletRequest, @RequestBody CategoryEntity category) {
        String content = "销售员增加分类";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", categoryService.insertCategory(category));
        return map;
    }

    @GetMapping("/saler/category/{pageNum}")
    public Map<String, Object> getCategoryAll(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "销售员取得分类第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = categoryService.getCategoryAll(pageNum, 5);
        return map;
    }

    @DeleteMapping("/saler/category/{id}")
    public Map<String, Object> deleteCategoryById(HttpServletRequest httpServletRequest, @PathVariable long id) {
        String content = "销售员删除分类id:"+id;
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", categoryService.deleteCategoryById(id));
        return map;
    }

    @DeleteMapping("/saler/category/batchDelete")
    public Map<String, Object> batchDeleteCategoryById(HttpServletRequest httpServletRequest, @RequestBody int[] ids) {
        String content = "销售员批量删除分类";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = new HashMap<>();
        map.put("status", categoryService.batchDeleteCategoryById(ids));
        return map;
    }

    @GetMapping("/saler/category_nopage")
    public List<CategoryEntity> getCategoryAllNoPage() {
        return categoryService.getCategoryAllNoPage();
    }

    @GetMapping("/admin/categoryOrder/{pageNum}")
    public Map<String, Object> getCategoryOrder(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "管理员取得分类出售情况第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = categoryService.CategoryOrder(pageNum, 5);
        return map;
    }

}
