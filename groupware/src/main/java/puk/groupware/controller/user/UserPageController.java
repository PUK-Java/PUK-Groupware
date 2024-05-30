package puk.groupware.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.user.UserPageService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserPageController {
    
    private final UserPageService userPageService;
    private final HttpSession httpSession;

    @Autowired
    UserPageController(UserPageService userPageService, HttpSession httpSession){
        this.userPageService = userPageService;
        this.httpSession = httpSession;
    }

    @GetMapping("/userpage")
    public String getUserpage() {
        return "userpage";
    }

    //회원정보 수정
    @PostMapping("/updateUser")
    public String modifyCurrentUser() {
        User_Info user = (User_Info) httpSession.getAttribute("loginUser");
        
        return "userpage";
    }
    
    //회원정보 삭제
    @GetMapping("/deleteUser")
    public String deleteCurrentUser() {
        User_Info user = (User_Info) httpSession.getAttribute("loginUser");
        String userId = user.getUserId();
        userPageService.deleteUser(userId);
        httpSession.removeAttribute("loginUser");
        return "redirect:";
    }
    
}
