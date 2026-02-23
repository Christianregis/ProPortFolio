package com.proPortfolio.ProPortfolio.services;

import com.proPortfolio.ProPortfolio.models.Project;
import com.proPortfolio.ProPortfolio.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    public final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public Project saveProject(Project project){
        return  projectRepository.save(project);
    }

    public Project findProjectById(Long id){
        return projectRepository.findById(id).orElse(null);
    }

    public Project updateProject(Long id, Project newProject){
        Project project = findProjectById(id);
        if(project != null){
            project.setName(newProject.getName()!=null?newProject.getName():project.getName());
            project.setDescription(newProject.getDescription()!=null ?newProject.getDescription():project.getDescription());
            project.setDemoLink(newProject.getDemoLink()!=null ? newProject.getDemoLink(): project.getDemoLink());
            project.setGithubLink(newProject.getGithubLink()!=null ? newProject.getGithubLink():project.getGithubLink());
            project.setImageUrl(newProject.getImageUrl()!=null?newProject.getImageUrl() : project.getImageUrl());

            return projectRepository.save(project);
        }
        return null;
    }

    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }
}
