package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping (value = "/")
    public String showAllUsers(ModelMap modelMap) {
        List<User> allUsers = userService.getAllUsers();
        modelMap.addAttribute("allUsers", allUsers);
        return "users";
    }

    @PostMapping(value = "/addUser")
    public String addNewUser(@ModelAttribute ("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        user.setId(user.getId());
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}