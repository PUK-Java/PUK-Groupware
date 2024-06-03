package puk.groupware.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.model.wishlist.WishList;
import puk.groupware.model.wishlist.WishListId;
import puk.groupware.service.user.UserPageService;
import puk.groupware.service.wishList.WishListService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@Slf4j
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
    public String getUserpage(Model model){
        User_Info currentUser = (User_Info) httpSession.getAttribute("loginUser");
        String userId = currentUser.getUserId();
        log.error(userId);
        List<WishList> wishLists = wishListService.findByWishListIdUserInfoUserId(userId);
        log.error("123");
        List<Project_info> projectLists = new ArrayList<Project_info>();
        log.error("321");
        for(WishList wishList : wishLists){
            log.error("333");
            projectLists.add(wishList.getWishListId().getProjectInfo());
        }
        model.addAttribute("projectLists", projectLists);
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
            return "/userpage";
        }else return "redirect:/userpage";        
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
