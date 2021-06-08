package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.UserService;
import org.example.web.dto.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = Logger.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        logger.info("GET /user/register");
        model.addAttribute("userLoginForm", new UserLoginForm());
        return "register_page";
    }

    @PostMapping("/register")
    public String registerUser(UserLoginForm userLoginForm) {
        logger.info("POST /user/register " + userLoginForm.getUsername());
        if (userService.createUser(userLoginForm)) {
            return "redirect:/login";
        } else {
            return "redirect:/user/register"; // TODO error message
        }
    }
}
