package com.proPortfolio.ProPortfolio.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.proPortfolio.ProPortfolio.models.Article;
import com.proPortfolio.ProPortfolio.models.Project;
import com.proPortfolio.ProPortfolio.models.User;
import com.proPortfolio.ProPortfolio.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    public User findUserById(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(Long id, User newUser){
        User user = userRepository.findById(id).orElse(null);
        if(user !=null){
            user.setUsername(newUser.getUsername()!=null ? newUser.getUsername() : user.getUsername());
            user.setPassword(newUser.getPassword()!=null ? newUser.getPassword() : user.getPassword());
            user.setEmail(newUser.getEmail()!=null ? newUser.getEmail() : user.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<Article> showArticle(Long id){
        return userRepository.findById(id).orElse(null).getListArticle();
    }

    public List<Project> showProject(Long id){
        return userRepository.findById(id).orElse(null).getListProject();
    }
}
