package com.proPortfolio.ProPortfolio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proPortfolio.ProPortfolio.models.Category;
import com.proPortfolio.ProPortfolio.repository.CategorieRepository;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public Category createCategory(Category category) {
        return categorieRepository.save(category);
    }

    public List<Category> showAllCategories() {
        return categorieRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Category category) {
        categorieRepository.delete(category);
    }

    public Category updateCategory(Long id, Category newCategory) {
        if (categorieRepository.findById(id) != null) {
            Category category = categorieRepository.findById(id).orElse(null);
            assert category != null;
            category.setName(newCategory.getName() != null ? newCategory.getName() : category.getName());
            return categorieRepository.save(category);
        }
        return null;
    }
}
