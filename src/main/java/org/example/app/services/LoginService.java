package org.example.app.services;


import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class);

    public boolean auth(LoginForm loginForm) {
        logger.info("Auth attempt with " + loginForm);
        return "root".equals(loginForm.getUsername())
                && "12345".equals(loginForm.getPassword());
    }
}
