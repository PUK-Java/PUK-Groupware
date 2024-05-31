package puk.groupware.controller.project;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import puk.groupware.model.project.Project_info;
import puk.groupware.service.project.ProjectViewService;
import puk.groupware.service.wishList.WishListService;

@Controller
@RequiredArgsConstructor
public class projectViewController {

    private final ProjectViewService viewService;
    private final WishListService wishListService;



    @GetMapping("/projectDetail/{id}")
    public String prjview(@PathVariable("id") Long id, Model model) {
        //프로젝트 객체 찾기
        Project_info projectView = viewService.getProjectById(id);

        
        long daysBetween = viewService.dayBetween(id);
        model.addAttribute("data", projectView);
        model.addAttribute("daysBetween", daysBetween);

        //처음 접속할 때 찜목록에 해당 프로젝트가 있는지 없는지 체크하기 위해 추가
        model.addAttribute("wishListCheck",wishListService.checkWishList(projectView));
        return "projectView";
    }

    @GetMapping("/test")
    public String mainprjview(Model model) {
        List<Project_info> projects = viewService.getAllprojects();
        model.addAttribute("data1", projects);
        return "test";
    }

}
