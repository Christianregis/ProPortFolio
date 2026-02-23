package com.proPortfolio.ProPortfolio.controllers;

import com.proPortfolio.ProPortfolio.dto.ProjectDto;
import com.proPortfolio.ProPortfolio.models.Category;
import com.proPortfolio.ProPortfolio.models.Project;
import com.proPortfolio.ProPortfolio.models.User;
import com.proPortfolio.ProPortfolio.services.CategorieService;
import com.proPortfolio.ProPortfolio.services.ProjectService;
import com.proPortfolio.ProPortfolio.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    final ProjectService projectService;
    private final UserService userService;
    private final CategorieService categorieService;

    public ProjectController(ProjectService projectService, UserService userService, CategorieService categorieService) {
        this.projectService = projectService;
        this.userService = userService;
        this.categorieService = categorieService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(){
        return ResponseEntity.ok(projectService.findAllProjects());
    }

    @PostMapping
    public ResponseEntity<Project> saveProject(@RequestBody ProjectDto projectDto){
        User user = userService.findUserById(projectDto.getAuthorId());
        Category category = categorieService.findCategoryById(projectDto.getCategoryId());

        Project project  = new Project();
        project.setName(projectDto.getName());
        project.setImageUrl(projectDto.getImageLink());
        project.setGithubLink(projectDto.getGithubLink());
        project.setDescription(projectDto.getDescription());
        project.setDemoLink(project.getDemoLink());

        project.setAuthor(user);
        project.setCategory(category);

        return ResponseEntity.ok(projectService.saveProject(project));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable  Long id){
        return ResponseEntity.ok(projectService.findProjectById(id));
    }
}
