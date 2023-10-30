package com.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.advices.CategoryException;
import com.entity.Category;
import com.repository.CategoryRepository;
import com.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int categoryId) throws CategoryException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category not found with id: " + categoryId));
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int categoryId, Category updatedCategory) throws CategoryException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryException("Category not found with id: " + categoryId);
        }
        updatedCategory.setId(categoryId);
        return categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(int categoryId) throws CategoryException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryException("Category not found with id: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }
    
    @Override
    public Category getCategoryByName(String categoryName) throws CategoryException {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new CategoryException("Category not found with name: " + categoryName));
    }
}
