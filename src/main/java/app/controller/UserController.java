package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userservice;

    public UserController(UserService userService) {
        this.userservice = userService;
    }

    @GetMapping("")
    public String getUserPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userservice.getUserByName(auth.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
