package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public String getUsers(Model model) {
        model.addAttribute("users", service.getUsers());
        return "user";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user",new User());
        return "user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        service.deleteUser(id);
        return "redirect:/user";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "user-form";
    }

    @PostMapping("user/save")
    public String saveUser(@ModelAttribute("user") User user) {
        if(user.getId() > 0) {
            service.updateUser(user);
        } else {
            service.addUser(user);
        }
        return "redirect:/user";
    }
}
