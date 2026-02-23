package com.proPortfolio.ProPortfolio.controllers;

import java.util.List;

import com.proPortfolio.ProPortfolio.dto.ArticleDto;
import com.proPortfolio.ProPortfolio.models.Category;
import com.proPortfolio.ProPortfolio.models.User;
import com.proPortfolio.ProPortfolio.services.CategorieService;
import com.proPortfolio.ProPortfolio.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proPortfolio.ProPortfolio.models.Article;
import com.proPortfolio.ProPortfolio.services.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;
    private final CategorieService categorieService;

    public ArticleController(ArticleService articleService, UserService userService, CategorieService categorieService) {
        this.articleService = articleService;
        this.userService = userService;
        this.categorieService = categorieService;
    }


    @PostMapping
    public ResponseEntity<Article> saveArticle(@RequestBody ArticleDto articleDto){

        User user = userService.findUserById(articleDto.getAuthorId()); // Permettra d'envoyer le userId pour la liasion avec l'article
        Category category = categorieService.findCategoryById(articleDto.getCategoryId()); // Permettra d'envoyer le categoryId pour la liasion avec l'article

        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setStatus(articleDto.getStatus());
        article.setCategory(category);
        article.setAuthor(user);

        return ResponseEntity.ok(articleService.createArticle(article));
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles(){
        return ResponseEntity.ok(articleService.findAllArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        return ResponseEntity.ok(articleService.findArticleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto){
        Article article = new Article();

        User user = userService.findUserById(articleDto.getAuthorId()); // Permettra d'envoyer le userId pour la liasion avec l'article
        Category category = categorieService.findCategoryById(articleDto.getCategoryId()); // Permettra d'envoyer le categoryId pour la liasion avec l'article

        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setStatus(articleDto.getStatus());
        article.setCategory(category);
        article.setAuthor(user);
        return ResponseEntity.status(HttpStatus.OK).body(articleService.updateArticle(id, article));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<List<String>> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
        return ResponseEntity.ok(List.of("message","Article supprimee !"));
    }
}
