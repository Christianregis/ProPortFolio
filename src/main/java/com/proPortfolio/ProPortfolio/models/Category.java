package com.proPortfolio.ProPortfolio.models;
import java.util.List;


import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Project> projects;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles(){
        return articles;
    }
    public List<Project> getProjects(){
        return projects;
    }
}