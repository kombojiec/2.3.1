package app.controller;

import app.entity.Role;
import app.entity.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private List<Role> roles;

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
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roles);
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(name = "ROLE_USER", required = false) String ROLE_USER,
                           @RequestParam(name = "ROLE_ADMIN", required = false) String ROLE_ADMIN) {
        List<Role> roles = new ArrayList<>();
        for(Role role: this.roles) {
            if(role.getRole().equals(ROLE_USER)) {
                roles.add(role);
            }
            if(role.getRole().equals(ROLE_ADMIN)) {
                roles.add(role);
            }
        }
        user.setRoles(roles);
        if(user.getId() > 0) {
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        return "redirect:/admin";
    }
}
