package com.proPortfolio.ProPortfolio.controllers;

import java.util.List;
import com.proPortfolio.ProPortfolio.services.CategorieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proPortfolio.ProPortfolio.models.Category;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategorieService categorieService;

    CategoryController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
    
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categorieService.showAllCategories());
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return ResponseEntity.ok(categorieService.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category newCategory){
        return ResponseEntity.ok(categorieService.updateCategory(id, newCategory));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categorieService.findCategoryById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<String>> deleteCategory(@PathVariable("id") Long id){
        Category category = categorieService.findCategoryById(id);
        if(category != null){
            categorieService.deleteCategory(category);
            return ResponseEntity.ok(List.of("message","Category with id "+id+ " deleted successfully"));
        }
        return ResponseEntity.notFound().build();
        
    }
}
