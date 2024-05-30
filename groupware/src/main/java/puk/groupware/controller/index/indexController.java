package puk.groupware.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import puk.groupware.service.project.ProjectFindService;

//인덱스 페이지 관련
@Controller
public class indexController {
    
    private final ProjectFindService pService;
    @Autowired
    indexController(ProjectFindService pService){
        this.pService = pService;
    }

    @GetMapping("/")
    public String getAllProject(@RequestParam(name="page", required = false, defaultValue = "0") int page,
    @RequestParam(name="projectName",required = false) String projectName,
    @RequestParam(name="projectCategory",required = false) String projectCategory,
    Model model){
        pService.paging(page,projectName,projectCategory,model);
        return "index";
    }
}
    

