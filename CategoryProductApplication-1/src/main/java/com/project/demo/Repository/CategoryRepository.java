package com.project.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
