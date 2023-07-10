package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/")
    public String printUsers(ModelMap model) {
        List<User> messages = userService.listUser();
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/del")
    public String delUser(ModelMap model) {
        model.addAttribute("userDel", new User());
        return "del";
    }

    @DeleteMapping(value = "/del/{id}")
    public String deletedUser(@PathVariable Long id) {
        userService.delUser(id);
        return "redirect:/";
    }

    @GetMapping(value = "edit/{id}")
    public String editUser(ModelMap model, @PathVariable Long id) {
        model.addAttribute("userEdit", userService.findUserById(id));
        return "edit";
    }

    @PatchMapping("")
    public String editedUser(@ModelAttribute("userEdit") User userEdit) {
        userService.changeUser(userEdit);
        return "redirect:/";
    }


}