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

    public Category addCategory(Category category) {
        categoryRepo.save(category);
        return category;
    }
    
}
