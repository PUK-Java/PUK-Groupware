package puk.groupware.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import puk.groupware.domain.user.User_info;
import puk.groupware.service.user.UserService;




@Controller
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    };
    

    @GetMapping("/signUpform")
    public String singUp(){
        return "signupform";
    }

    @GetMapping("/loginform")
    public String loginform() {
        return "loginform";
    }
    

    @PostMapping("/signupRequest")
    public String signUpRequest(@ModelAttribute User_info uInfo,@RequestParam(name="address1") String address1, @RequestParam(name = "address2") String address2) {
        userService.signUp(uInfo, address1, address2);
        return "redirect:";
    }

    @PostMapping("/loginRequest")
    public String loginRequest(@ModelAttribute User_info user) {
        userService.login(user);
        return "redirect:";
    }
    
    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:";
    }
    
}
