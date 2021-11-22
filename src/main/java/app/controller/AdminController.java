package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public String getUsers(Model model) {
        model.addAttribute("users", service.getUsers());
        return "admin";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user",new User());
        return "user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") int id, Model model) {
        System.out.println("==================updateUser===================\n" + id);
        model.addAttribute("user", service.getUserById(id));
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        System.out.println("==================saveUser===================\n" + user.getId());
        System.out.println(user);
        if(user.getId() > 0) {
            service.updateUser(user);
        } else {
            service.addUser(user);
        }
        return "redirect:/admin";
    }
}
