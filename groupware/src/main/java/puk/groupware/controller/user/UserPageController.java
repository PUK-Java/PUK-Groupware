package puk.groupware.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController {
    
    @GetMapping("/userpage")
    public String getlogin() {
        return "userpage";
    }
}
