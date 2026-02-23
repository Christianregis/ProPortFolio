package com.proPortfolio.ProPortfolio.dto;

public class ArticleDto {
    private String title;
    private String content;
    private String status;
    private Long authorId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categryId) {
        this.categoryId = categryId;
    }

    private Long categoryId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }


}
