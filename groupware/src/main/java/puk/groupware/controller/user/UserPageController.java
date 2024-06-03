package puk.groupware.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.user.UserPageService;
import puk.groupware.service.wishList.WishListService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class UserPageController {
    
    private final UserPageService userPageService;
    private final HttpSession httpSession;
    private final WishListService wishListService;

    @Autowired
    UserPageController(UserPageService userPageService, HttpSession httpSession, WishListService wishListService){
        this.userPageService = userPageService;
        this.httpSession = httpSession;
        this.wishListService = wishListService;
    }

    @GetMapping("/userpage")
    public String getUserpage(){
        wishListService.findById(null);
        return "userpage";
    }

    //비밀번호 확인 창
    @GetMapping("/verifyUser")
    public String verifyUser() {
        return "verifyuser";
    }
    
    //비밀번호 확인 후 다시 유저페이지로
    @PostMapping("/userPwCheck")
    public String pwCheck(@RequestParam("password") String password) {
        User_Info currentUser = (User_Info) httpSession.getAttribute("loginUser");
        if(currentUser.getUserPw().equals(password)){
            httpSession.setAttribute("verify", "ok");
            System.out.println(httpSession.getAttribute("verify"));
            return "/userpage";
        }else return "redirect:/verifyuser";        
    }
    
    //회원정보 수정
    @PostMapping("/updateUser")
    public String modifyCurrentUser(User_Info user) {
        userPageService.saveUser(user);
        httpSession.setAttribute("loginUser", user);
        httpSession.removeAttribute("verify");
        return "userpage";
    }
    
    //회원정보 삭제
    @GetMapping("/deleteUser")
    public String deleteCurrentUser() {
        User_Info user = (User_Info) httpSession.getAttribute("loginUser");
        String userId = user.getUserId();
        userPageService.deleteUser(userId);
        httpSession.removeAttribute("loginUser");
        httpSession.removeAttribute("verify");
        return "redirect:";
    }
    
    //메인으로
    @GetMapping("returnMain")
    public String returnMain() {
        httpSession.removeAttribute("verify");
        return "redirect:/";
    }
}
