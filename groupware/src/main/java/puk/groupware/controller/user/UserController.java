package puk.groupware.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import puk.groupware.domain.user.User_info;
import puk.groupware.service.user.User_info_jpaRepository;


@Controller
@Slf4j
public class UserController {
    private final User_info_jpaRepository uJpaRepository;
    private final HttpSession httpSession;
    
    @Autowired
    public UserController(User_info_jpaRepository uJpaRepository, HttpSession httpSession){
        this.uJpaRepository = uJpaRepository;
        this.httpSession = httpSession;
    };
    

    @GetMapping("/signUpform")
    public String singUp(){
        return "signupform";
    }

    @ResponseBody
    @PostMapping("/signupRequest")
    public String signUpRequest(@ModelAttribute User_info uInfo) {
        return uInfo.toString();
    }
    
}
