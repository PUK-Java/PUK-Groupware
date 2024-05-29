package puk.groupware.controller.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import puk.groupware.model.user.User_Info;
import puk.groupware.service.user.Userservicesave;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SingupController {
    @Autowired
    private Userservicesave userservice;

    @GetMapping("/")
    public String mainview() {
        return "index";
    }

    @GetMapping("/signupform")
    public String signup() {
        return "signupform";
    }

    @PostMapping("/submitForm")
    public String submitSignup(@ModelAttribute User_Info user, Model model) {

        try {
            userservice.saveUser(user);
            model.addAttribute("userForm", user);
            return "signupSuccess";
        } catch (IllegalArgumentException e) {
            model.addAttribute("userForm", user);
            model.addAttribute("idErrorMessage", e.getMessage());
            return "signupform";
        } catch (Exception e) {
            model.addAttribute("userForm", user);
            model.addAttribute("errorMessage", e.getMessage());
            return "signupform";
        }

    }

    @GetMapping("/projectregform")
    public String projectform() {
        return "projectregform";
    }

}