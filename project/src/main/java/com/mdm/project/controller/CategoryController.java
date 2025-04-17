package com.mdm.project.controller;


import com.mdm.project.dto.ResponseDto;
import com.mdm.project.entity.Category;
import com.mdm.project.entity.CategoryNode;
import com.mdm.project.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertCategory(@RequestParam String code, @RequestParam String name, @RequestParam(required = false) String parentCode) {
        categoryService.insertCategory(code, name, parentCode);

        return ResponseEntity.ok(new ResponseDto("200", "Insert category successful"));
    }

    @GetMapping("/all-categories")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryNode> res = categoryService.findAllCategories();
        return ResponseEntity.ok(res);
    }

}
