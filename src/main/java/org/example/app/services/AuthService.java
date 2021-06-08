package org.example.app.services;


import org.apache.log4j.Logger;
import org.example.app.repository.UserRepository;
import org.example.web.dto.User;
import org.example.web.dto.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Logger logger = Logger.getLogger(AuthService.class);

    private final UserRepository repository;

    @Autowired
    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean auth(UserLoginForm userLoginForm) {
        String username = userLoginForm.getUsername();
        User actor = repository.getByName(username);
        logger.info("Auth attempt for " + username);
        return actor.getPassword().equals(userLoginForm.getPassword());
    }
}
