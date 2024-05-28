package puk.groupware.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import puk.groupware.model.User_Info;
import puk.groupware.service.Userservicesave;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupControl {
    @Autowired
    private Userservicesave userservice;

    @GetMapping("/")
    public String mainview() {
        return "index";
    }

    @GetMapping("/test1")
    public String signup() {
        return "test1";
    }

    @PostMapping("/submitForm")
    public String submitSignup(User_Info user, Model model) {

        try {
            userservice.saveUser(user);
            model.addAttribute("userForm", user);
            return "test";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "test1";
        }

    }

    @GetMapping("/projectregform")
    public String projectform() {
        return "projectregform";
    }

}
