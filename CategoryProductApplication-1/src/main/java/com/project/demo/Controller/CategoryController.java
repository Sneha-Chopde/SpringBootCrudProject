package com.project.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.demo.Model.Category;
import com.project.demo.Service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    
    @GetMapping
    public ResponseEntity<Page<Category>> getAllCategories(
            @RequestParam(name = "page", required = false) Integer page) {

        if (page == null) {
            page = 3;  // ✅ Set default page = 3
        }

        Page<Category> categories = categoryService.getAllCategories(page);
        return ResponseEntity.ok(categories);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return (updatedCategory != null) ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
