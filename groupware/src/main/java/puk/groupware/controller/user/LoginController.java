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
            httpSession.setAttribute("loginUser", user);
            return "redirect:/";
        }else{
            model.addAttribute("msg", "회원정보를 다시 확인해 주세요.");
            return "loginform";
        }
        // 지금 user를 id와 pw을 이용해서 확인하기 때문에 id 혹은 pw 요소 하나만 틀릴 경우에는 회원정보가 있음에도 불구하고 user를 찾지못함
        // 로그인 성공 시 환영합니다 문구 구현할 필요 있음
    }

    @GetMapping("/logout")
    public String getlogout() {
        httpSession.removeAttribute("loginUser");
        return "redirect:/";
    }
    
}
