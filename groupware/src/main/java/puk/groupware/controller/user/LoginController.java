package puk.groupware.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//로그인 화면으로 보내주는 컨트롤러
//localhost:8080/login
@Controller
public class LoginController {
    @GetMapping("/login")
    public String getlogin() {
        return "loginform";
    }
    
}
