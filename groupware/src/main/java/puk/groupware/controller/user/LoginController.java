package puk.groupware.controller.user;

<<<<<<< Updated upstream
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
    
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.servlet.http.HttpSession;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.user.UserLoginService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginController {

    private final UserLoginService userLoginService;
    private final HttpSession httpSession;
    @Autowired
    LoginController(UserLoginService userLoginService, HttpSession httpSession){
        this.userLoginService = userLoginService;
        this.httpSession = httpSession;
    }
    @GetMapping("/login")
    public String login() {
        return "loginform";
    }
    

    @PostMapping("/loginRequest")
    public String login(@RequestParam(name="userId") String userId, @RequestParam(name="userPw") String userPw, Model model) {
        User_Info user = userLoginService.findUser(userId,userPw);
        if(user != null){
            httpSession.setAttribute("loginedUser", user);
            return "redirect:/";
        }else{
            model.addAttribute("error", "아이디나 비밀번호가 불일치합니다.");
            return "loginform";
        }


    }
    
>>>>>>> Stashed changes
}
