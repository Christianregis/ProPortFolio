package com.proPortfolio.ProPortfolio.controllers;

import com.proPortfolio.ProPortfolio.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proPortfolio.ProPortfolio.models.Article;
import com.proPortfolio.ProPortfolio.models.Project;
import com.proPortfolio.ProPortfolio.models.User;
import com.proPortfolio.ProPortfolio.repository.UserRepository;
import com.proPortfolio.ProPortfolio.services.UserService;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController{

    private final UserService userService;
    
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());

        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User newUser){
        return ResponseEntity.ok(userService.updateUser(id, newUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<String>> deleteUser(@PathVariable("id") Long id){
        User user =  userService.findUserById(id);
        if(user != null){
            userService.deleteUser(user);
            return ResponseEntity.ok(List.of("message","User with id "+id+ " deleted successfully"));
        }
        return ResponseEntity.of(Optional.of(List.of("message","User with id "+id+ " not found")));
    }
    
    @GetMapping("/{id}/articles")
    public ResponseEntity<List<Article>> getAllArticles(@PathVariable("id") Long id){
        if(userService.findUserById(id)!=null){
            return ResponseEntity.ok(userService.showArticle(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Project>> getAllProjects(@PathVariable("id") Long id){
        if(userService.findUserById(id) !=null){
            return ResponseEntity.ok((userService.showProject(id)));
        }
        return ResponseEntity.notFound().build();
    }
}
