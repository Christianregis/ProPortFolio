package com.proPortfolio.ProPortfolio.repository;

import com.proPortfolio.ProPortfolio.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
