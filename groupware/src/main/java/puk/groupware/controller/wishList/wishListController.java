package puk.groupware.controller.wishList;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.model.wishlist.WishList;
import puk.groupware.model.wishlist.WishListId;
import puk.groupware.service.project.ProjectFindService;
import puk.groupware.service.user.UserLoginService;
import puk.groupware.service.wishList.WishListService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class wishListController {
    private final WishListService wishListService;
    private final HttpSession httpSession;
    private final ProjectFindService projectFindService;
    private final UserLoginService userLoginService;

    @PostMapping("/wishList/toggle")
    @ResponseBody
    public Map<String,Object> toggleWishList(@RequestBody Map<String,Object> body) {
        
        Long projectNo = Long.valueOf((String)body.get("projectNo"));
        String userId = (String) body.get("userId");

        //프로젝트 객체 찾기
        Project_info project = projectFindService.findById(projectNo);

        //유저 객체 찾기
        User_Info user = userLoginService.findById(userId);

        //WishListId 객체 생성해서 찾는 프로젝트 객체와 유저 객체 매핑 시켜주기
        WishListId wishListId = new WishListId();
        wishListId.setProjectInfo(project);
        wishListId.setUserInfo(user);
        
        //wishList 객체를 생성해서 WishListId(기본키) 매핑 시켜주기
        WishList wishList = new WishList();
        wishList.setWishListId(wishListId);
        boolean isWished = wishListService.toggleWishList(wishList, wishListId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("isWished",isWished);
        return response;
    }
    
}
