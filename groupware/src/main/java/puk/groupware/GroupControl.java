package puk.groupware;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GroupControl {
    @GetMapping("/")
    public String mainview() {
        return "index";
    }

    @GetMapping("/signupform")
    public String signup() {
        return "signupform";
    }

    @PostMapping("/submitForm")
    public String submitSignup(@ModelAttribute("userForm") User_Info userForm, Model model) {
        model.addAttribute("userForm", userForm);

        return "test";
    }

    @GetMapping("/projectregform")
    public String projectform() {
        return "projectregform";
    }

}
