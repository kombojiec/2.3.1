package app.controller;

import app.entity.Role;
import app.entity.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private Set<Role> roles;

    public AdminController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
        roles = roleService.getRoles();
    }

    @GetMapping("")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("roles", roles);
        return "user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") int id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "params") String[] params) {
        Set<Role> rolesSet = Arrays.stream(params)
                .map(item -> roles.stream().filter(el -> el.getRole().equals(item)).findFirst().get())
                        .collect(Collectors.toSet());
        user.setRoles(rolesSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
