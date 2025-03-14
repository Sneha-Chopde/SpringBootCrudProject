package com.project.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.demo.Model.Category;
import com.project.demo.Repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

   
    public Page<Category> getAllCategories(int page) {
        int pageSize = 5;  // Set page size
        Pageable pageable = PageRequest.of(page, pageSize);
        return categoryRepository.findAll(pageable);  // Return paginated result
    }

   
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

  
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

   
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElse(null);
    }


    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

