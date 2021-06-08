package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.AuthService;
import org.example.web.dto.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final Logger logger = Logger.getLogger(LoginController.class);
    private final AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String login(Model model) {
        logger.info("GET /login");
        model.addAttribute("userLoginForm", new UserLoginForm());
        return "login_page";
    }

    @PostMapping( "/auth")
    public String auth(UserLoginForm userLoginForm) {
        if (authService.auth(userLoginForm)) {
            return "redirect:/books/shelf";
        } else {
            return "redirect:/login";
        }
    }
}
