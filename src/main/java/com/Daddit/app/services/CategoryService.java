package com.Daddit.app.services;

import com.Daddit.app.models.Category;
import com.Daddit.app.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryService {

    private CategoryRepository categoryRepo;

    @Autowired
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepo.findById(id);
    }
    
    public Optional<Category> findByName(String name) {
        return categoryRepo.findByname(name);
    }

    public Category addCategory(Category category) {

        if (!categoryRepo.findByname(category.getName()).isPresent()) {
            categoryRepo.save(category);
            return category;
        } else {
            return null;
        }
    }

}
