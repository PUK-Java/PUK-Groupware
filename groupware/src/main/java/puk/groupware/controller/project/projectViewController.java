package puk.groupware.controller.project;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.project.ProjectSupportService;
import puk.groupware.service.project.ProjectViewService;
import puk.groupware.service.wishList.WishListService;

@Controller
@RequiredArgsConstructor
public class projectViewController {

    private final ProjectViewService viewService;
    private final WishListService wishListService;
    private final HttpSession HttpSession;
    private final ProjectSupportService supportService;



    @GetMapping("/projectDetail/{id}")
    public String prjview(@PathVariable("id") Long id, Model model) {
        //프로젝트 객체 찾기
        Project_info projectView = viewService.getProjectById(id);

        
        long daysBetween = viewService.dayBetween(id);
        long supportCount = supportService.getcount(id);
        supportService.stateUp(id, projectView.getTargetCost(), projectView.getCost());
        model.addAttribute("data", projectView);
        model.addAttribute("daysBetween", daysBetween);


        //처음 접속할 때 찜목록에 해당 프로젝트가 있는지 없는지 체크하기 위해 추가
        model.addAttribute("wishListCheck",wishListService.checkWishList(projectView));
        
        //로그인 한 유저가 있다면 로그인한 유저가 해당 프로젝트를 후원 했는지 여부를 확인
        if(HttpSession.getAttribute("loginUser") != null){
            //유저 이름 구하기
            User_Info loginUser= (User_Info) HttpSession.getAttribute("loginUser");
            String loginUserId = loginUser.getUserId();

            //해당 유저와 해당 페이지의 프로젝트 정보로 후원했는지 여부를 체크
            boolean supportCheck = supportService.exexistsByProjectNoandUserId(id, loginUserId);
            model.addAttribute("supportCheck", supportCheck);
        }

        model.addAttribute("count", supportCount);
        return "projectDetail";
    }

    @PostMapping("/sponTable/{projectNo}")
    public String postMethodName(@PathVariable("projectNo") Long projectNo) {

        User_Info loginUser = (User_Info) HttpSession.getAttribute("loginUser");

        supportService.saveSupport(loginUser, projectNo);

        return "redirect:/projectDetail/{projectNo}";
    }

}
