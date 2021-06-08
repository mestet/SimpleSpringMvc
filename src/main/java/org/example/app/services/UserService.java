package org.example.app.services;

import org.example.app.repository.ProjectRepository;
import org.example.web.dto.User;
import org.example.web.dto.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ProjectRepository<User> repository;

    @Autowired
    public UserService(ProjectRepository<User> repository) {
        this.repository = repository;
    }

    public boolean createUser(UserLoginForm userLoginForm) {
        User newUser = User.builder()
                .username(userLoginForm.getUsername())
                .password(userLoginForm.getPassword())
                .role(User.UserRole.COMMON)
                .build();
        return repository.store(newUser);
    }
}
