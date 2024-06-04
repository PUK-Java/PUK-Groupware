package puk.groupware.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.project.ProjectSupportService;
import puk.groupware.service.project.ProjectViewService;

@Controller
public class projectViewController {


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
        long supportCount = supportService.getcount(id);
        supportService.stateUp(id, projectView.getTargetCost(), projectView.getCost());
        model.addAttribute("data", projectView);
        model.addAttribute("daysBetween", daysBetween);
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
