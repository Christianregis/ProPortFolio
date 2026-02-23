package com.proPortfolio.ProPortfolio.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.proPortfolio.ProPortfolio.models.Article;
import com.proPortfolio.ProPortfolio.repository.ArticleRepository;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(Article article){
        return articleRepository.save(article);
    }

    public List<Article> findAllArticles(){
        return articleRepository.findAll();
    }

    public Article findArticleById(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article updateArticle(Long id, Article newArticle){
        Article article = articleRepository.findById(id).orElse(null);
        if(article!=null) {
            article.setTitle(newArticle.getTitle() != null ? newArticle.getTitle() : article.getTitle());
            article.setContent(newArticle.getContent() != null ? newArticle.getContent() : article.getContent());
            article.setStatus(newArticle.getStatus() != null ? newArticle.getStatus() : article.getStatus());
            return articleRepository.save(article);
        }
        return null;
    }
    public void deleteArticle(Long id){
        articleRepository.deleteById(id);
    }
}
