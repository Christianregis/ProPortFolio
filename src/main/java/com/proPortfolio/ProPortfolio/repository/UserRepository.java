package com.proPortfolio.ProPortfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proPortfolio.ProPortfolio.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
