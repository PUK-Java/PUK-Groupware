package puk.groupware.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import puk.groupware.domain.user.User_info;
import puk.groupware.repository.user.User_info_jpaRepository;




@Controller
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

    @GetMapping("/loginform")
    public String loginform() {
        return "loginform";
    }
    

    @PostMapping("/signupRequest")
    public String signUpRequest(@ModelAttribute User_info uInfo,@RequestParam(name="address1") String address1, @RequestParam(name = "address2") String address2) {
        uInfo.setAddress(address1 + " " + address2);
        uJpaRepository.save(uInfo);
        return "redirect:";
    }

    @PostMapping("/loginRequest")
    public String loginRequest(@ModelAttribute User_info user) {
        String userId = user.getUserId();
        String userPw = user.getUserPw();
        User_info loginedUser = uJpaRepository.findByUserIdAndUserPw(userId, userPw);
        httpSession.setAttribute("loginedUser", loginedUser);
        return "redirect:";
    }
    
    @GetMapping("/logout")
    public String logout() {
        httpSession.removeAttribute("loginedUser");
        return "redirect:";
    }
    
}
