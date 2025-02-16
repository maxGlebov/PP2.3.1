package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
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

    @PostMapping("/addUser")
    public String addNewUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "Пользователь успешно добавлен!");
        return "redirect:/";
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
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