package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("allUsers", userService.allUser());
        return "users";
    }

    @GetMapping("/users/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/users/newUser/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/editUser/{id}")
    public String editUserFrom(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getById(id));
        return "editUser";

    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute User user) {
        userService.edit(user);
        return "redirect:/users";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.remove(id);
        return "redirect:/users";
    }

}
