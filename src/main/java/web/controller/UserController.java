package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.RoleServiceImpl;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String listUsers(Model model) {
        model.addAttribute("allUsers", userService.allUser());
        return "admin";
    }

    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping("/admin/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.getAllRoles());
        return "newUser";
    }

    @PostMapping("/admin/newUser/add")
    public String addUser(@ModelAttribute User user, @RequestParam(value = "roles") String[] roles) {
        Set<Role> roleSet = new HashSet<Role>();
        for (String role : roles) {
            roleSet.add(roleService.getRole(role));
        }
        user.setRoles(roleSet);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/editUser/{id}")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "editUser";
    }

    @PostMapping("/admin/editUser")
    public String editUser(@ModelAttribute User user, @RequestParam(value = "roles") String[] roles) {
        Set<Role> roleSet = new HashSet<Role>();
        for (String role : roles) {
            roleSet.add(roleService.getRole(role));
        }
        user.setRoles(roleSet);
        userService.edit(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/remove/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.remove(id);
        return "admin";
    }

}
