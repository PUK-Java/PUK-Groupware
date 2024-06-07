package puk.groupware.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.model.wishlist.WishList;
import puk.groupware.model.wishlist.WishListId;
import puk.groupware.service.project.ProjectFindService;
import puk.groupware.service.user.UserPageService;
import puk.groupware.service.wishList.WishListService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@Slf4j
public class UserPageController {
    
    private final UserPageService userPageService;
    private final HttpSession httpSession;
    private final WishListService wishListService;
    private final ProjectFindService projectFindService;

    @Autowired
    UserPageController(UserPageService userPageService, HttpSession httpSession, WishListService wishListService, ProjectFindService projectFindService){
        this.userPageService = userPageService;
        this.httpSession = httpSession;
        this.wishListService = wishListService;
        this.projectFindService = projectFindService;
    }

    @GetMapping("/userpage")
    @Transactional
    public String getUserpage(Model model){
        User_Info currentUser = (User_Info) httpSession.getAttribute("loginUser");
        if(currentUser == null){
            return "redirect:/";
        }else{
            //위시리스트
            String userId = currentUser.getUserId();
            List<Project_info> projectLists = wishListService.findByJoinProjectInfo(userId);
            model.addAttribute("projectLists", projectLists);
            //내 작품
            List<Project_info> myProjects = projectFindService.findByUserId(currentUser);
            model.addAttribute("myProjects", myProjects);
            return "userpage";
        }
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
            // System.out.println(httpSession.getAttribute("verify"));
            return "/verifyuser";
        }else return "/verifyuser";        
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
    
    //메인 갈 때 세션 삭제
    @GetMapping("/returnMain")
    public String getMain() {
        httpSession.removeAttribute("verify");
        return "redirect:/";
    }
    
    //위시리스트 삭제
    @DeleteMapping("/deleteWishlist/{projectNo}")
    @ResponseBody
    public Map<String,Object> deleteWishlist(@PathVariable(name="projectNo") Long projectNo) {
        User_Info user = (User_Info) httpSession.getAttribute("loginUser");


        wishListService.deleteByWishListIdUserInfoAndWishListIdProjectInfoProjectNo(user, projectNo);
        // WishListId delWishListId = new WishListId();
        // delWishListId.setProjectInfo(null);
        // delWishListId.setUserInfo(user);

        // System.out.println(delWishListId);
        // wishListService.deleteById(delWishListId);
        Map<String,Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }
}
