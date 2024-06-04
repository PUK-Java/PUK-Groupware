package puk.groupware.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.project.ProjectSupportService;
import puk.groupware.service.project.ProjectViewService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class projectViewController {
    @Autowired
    private ObjectMapper objectMapper;

    private final ProjectViewService viewService;
    private final ProjectSupportService supportService;
    private final HttpSession HttpSession;

    @Autowired
    projectViewController(HttpSession HttpSession,
            ProjectViewService viewService, ProjectSupportService supportService) {

        this.HttpSession = HttpSession;
        this.viewService = viewService;
        this.supportService = supportService;
    }

    @GetMapping("/projectDetail/{id}")
    public String prjview(@PathVariable("id") Long id, Model model) {
        Project_info projectView = viewService.getProjectById(id);
        long daysBetween = viewService.dayBetween(id);
        // 몇명이 후원을 했는지 count해주는 서비스 메소드
        // 가져온 값은 long타입으로 전환한다
        long supportCount = supportService.getcount(id);
        // 상세페이지에서 후원치가 목표지를 달성할시 테이블에 저장된 상태를 바꾸기위한 서비스 메소드
        supportService.stateUp(id, projectView.getTargetCost(), projectView.getCost());
        // 뷰에서 쓰기 위하여 모델이 값을 넣는다.
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
        return "projectDetail";
    }

    // 후원 테이블에 값을 저장
    @PostMapping("/sponTable/{projectNo}")
    public String postMethodName(@PathVariable("projectNo") Long projectNo) {
        // 후원 테이블에 값을 저장하기 위한 작업
        User_Info loginUser = (User_Info) HttpSession.getAttribute("loginUser");
        // 유저 아이디와 projecNo(기본키)를 받아서 후원 테이블에 저장하기 위한 서비스 메소드
        supportService.saveSupport(loginUser, projectNo);
        // 로그인을 안한고 후원을 누를시 로그인 페이지로 보낸다
        if (loginUser == null) {
            return "redirect:/login";
        } else {
            return "redirect:/projectDetail/{projectNo}";
        }

    }

    // 프로젝트 수정하기 뷰
    @GetMapping("/projectModify/{id}")
    public String projectModify01(@PathVariable("id") Long id, Model model) {
        Project_info projectView = viewService.getProjectById(id);
        model.addAttribute("data", projectView);
        return "projectModify";
    }

    // 프로젝트 수정하기 구현
    @PostMapping("/projectModify/{id}")
    public String projectModify02(@PathVariable("id") Long id, @ModelAttribute Project_info projectInfo,
            @RequestParam(name = "strEndDate") String strEndDate,
            @RequestParam(name = "imageFile") MultipartFile imageFile) {
        try {
            // 수정한정보를 데이터베이스에 업데이트 하기 위한 서비스 메소드
            viewService.upProject(id, projectInfo, strEndDate, imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/projectDetail/{id}";
    }

}
