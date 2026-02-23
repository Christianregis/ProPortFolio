package com.proPortfolio.ProPortfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proPortfolio.ProPortfolio.models.Category;

public interface CategorieRepository extends JpaRepository<Category, Long>{
    
}
