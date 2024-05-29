package puk.groupware.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import puk.groupware.model.project.Project_info;
import puk.groupware.service.project.ProjectViewService;

@Controller
public class projectViewController {
    @Autowired
    private ProjectViewService viewService;

    @GetMapping("projectView")
    public String prjview(@PathVariable Long Id, Model model) {
        Project_info projectView = viewService.findByProjectNo(Id);
        model.addAttribute("data", projectView);
        return "projectView";
    }

}
