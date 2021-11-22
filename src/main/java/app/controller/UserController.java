package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userservice;

    public UserController(UserService userService) {
        this.userservice = userService;
    }

    @GetMapping("")
    public String getUserPage(@RequestParam("id") int id, Model model) {
        User user = userservice.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", userservice.getUsersRolesById(id));
        return "user";
    }

}
