package puk.groupware.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import puk.groupware.model.user.User_Info;
import puk.groupware.service.user.Userservicesave;


@Controller
public class SingupController {
    @Autowired
    private Userservicesave userservice;

    

    @GetMapping("/signupform")
    public String signup() {
        return "signupform";
    }

    @PostMapping("/submitForm")
    public String submitSignup(@ModelAttribute User_Info user, Model model) {

        try {
            userservice.saveUser(user);
            model.addAttribute("userForm", user);
            return "success";
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


}
