package puk.groupware.controller.project;


import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.projectComment.ProjectComment;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.project.ProjectSupportService;
import puk.groupware.service.project.ProjectViewService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import puk.groupware.service.projectComment.ProjectCommentService;
import puk.groupware.service.wishList.WishListService;


@Controller
@RequiredArgsConstructor
public class projectViewController {

    @Autowired
    private ObjectMapper objectMapper;



    private final ProjectViewService viewService;
    private final WishListService wishListService;
    private final HttpSession HttpSession;
    private final ProjectSupportService supportService;
    private final ProjectCommentService projectCommentService;




    @GetMapping("/projectDetail/{id}")
    public String prjview(@PathVariable("id") Long id, Model model) {
        //프로젝트 객체 찾기
        Project_info projectView = viewService.getProjectById(id);

        
        long daysBetween = viewService.dayBetween(id);
        long supportCount = supportService.getcount(id);

        //해당 프로젝트 객체에 해당하는 커멘트 리스트 구하기

        supportService.stateUp(id, projectView.getTargetCost(), projectView.getCost());

        model.addAttribute("data", projectView);
        model.addAttribute("daysBetween", daysBetween);


        // projectView.getUserId() 같은경우 객체이기때문에 스트링으로 변환하기 위한 작업
        // null값이 있을경우 오류가 발생하기때문에 조건문으로 처리
        if (projectView.getUserId() != null) {
            String checkId = (projectView.getUserId().getUserId());
            model.addAttribute("checkId", checkId);
        }

        // 처음 접속할 때 찜목록에 해당 프로젝트가 있는지 없는지 체크하기 위해 추가
        model.addAttribute("wishListCheck", wishListService.checkWishList(projectView));


        model.addAttribute("count", supportCount);

         //로그인 한 유저가 있다면 로그인한 유저가 해당 프로젝트를 후원 했는지 여부를 확인
         if(HttpSession.getAttribute("loginUser") != null){
            //유저 이름 구하기
            User_Info loginUser= (User_Info) HttpSession.getAttribute("loginUser");
            String loginUserId = loginUser.getUserId();

            //해당 유저와 해당 페이지의 프로젝트 정보로 후원했는지 여부를 체크
            boolean supportCheck = supportService.exexistsByProjectNoandUserId(id, loginUserId);
            model.addAttribute("supportCheck", supportCheck);
        }

        //프로젝트 댓글도 리스트로 model에 추가해서 넣어주기
        Page<ProjectComment> projectCommentsPage = projectCommentService.pageToFirstFiveList(id);
        List<ProjectComment> projectComments = projectCommentsPage.getContent();
        model.addAttribute("projectComments", projectComments);

        //토탈 페이지가 2 이상이라면 화면에 전체 보기 버튼을 넣어줄 것이고, 전체 코멘트도 모델에 추가해줄 것입니다.
        if(projectCommentsPage.getTotalPages() > 1){
            model.addAttribute("isCommentTotalPages", true);
            List<ProjectComment> projectAllComments = projectCommentService.findByProjectInfoProjectNoOrderByProjectCommentWritDateTimeDesc(id);
            model.addAttribute("projectAllComments", projectAllComments);
        }
        

        return "projectDetail";
    }

    @PostMapping("/sponTable/{projectNo}")
    public String postMethodName(@PathVariable("projectNo") Long projectNo) {

        User_Info loginUser = (User_Info) HttpSession.getAttribute("loginUser");

        supportService.saveSupport(loginUser, projectNo);

        return "redirect:/projectDetail/{projectNo}";
    }

}
