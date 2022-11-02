package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;

@Controller
public class PeopleController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", this.userService.listUsers());
        this.userService.listUsers().forEach(System.out::println);
        return "users";
    }

    @GetMapping("user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("user-create")
    public String createUser(User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return "redirect:/";
    }

    @GetMapping("user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model modelMap) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("user-update")
    public String updateUser(User user) {
        userService.update(user.getId(), user);
        return "redirect:/";
    }

}
