package puk.groupware.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.user.UserLoginService;


//로그인 화면으로 보내주는 컨트롤러
//localhost:8080/login
@Controller
public class LoginController {

    
    private final UserLoginService userloginService;
    private final HttpSession httpSession;

    @Autowired
    LoginController(UserLoginService userloginService, HttpSession httpSession){
        this.userloginService = userloginService;
        this.httpSession = httpSession;
    }

    @GetMapping("/login")
    public String getlogin() {
        return "loginform";
    }
    

    @PostMapping("/loginRequest")
    public String postloginRequest(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, Model model) {
        User_Info user = userloginService.findUser(userId, userPw);
        if(user != null && user.getUserId().equals(userId) && user.getUserPw().equals(userPw)){
            httpSession.setAttribute("user", user);
            // System.out.println(user.getUserId());
            return "redirect:/";
        }else{
            model.addAttribute("error", "아이디나 비밀번호가 불일치 합니다.");
            return "loginform";
        }
    }

    @GetMapping("/logout")
    public String getlogout() {
        httpSession.removeAttribute("user");
        return "redirect:/";
    }
    
}
