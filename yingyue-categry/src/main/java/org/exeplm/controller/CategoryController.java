package org.exeplm.controller;

import org.exeplm.entity.Category;
import org.exeplm.service.CategoryService;
import org.exeplm.utils.TaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 分类(Category)表控制层
 *
 * @author makejava
 * @since 2022-07-06 11:52:59
 */
@RestController
@RequestMapping("categories")
public class CategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<TaoResult> getCategoryList(){
        List<Category> categoryList = categoryService.getCategoryList();
        return new ResponseEntity<>(TaoResult.ok(categoryList), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaoResult> updateCategory(@PathVariable("id") Integer id,@RequestBody Category category){
        category.setId(id);
        category.setUpdatedAt(new Date());
        Category update = categoryService.update(category);
        return new ResponseEntity<>(TaoResult.ok(update),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<TaoResult> addCategory(@RequestBody Category category){
        Category insert = categoryService.insert(category);
        return new ResponseEntity<>(TaoResult.ok(insert),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaoResult> deleteCategory(@PathVariable("id") Integer id){
        List<Integer> ids = categoryService.queryByParentId(id);
        for (Integer cid : ids) {
            categoryService.deleteById(cid);
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(TaoResult.ok(),HttpStatus.NO_CONTENT);
    }

}

