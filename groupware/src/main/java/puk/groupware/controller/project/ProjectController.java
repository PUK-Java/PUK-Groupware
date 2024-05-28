package puk.groupware.controller.project;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import puk.groupware.domain.project.Project_info;
import puk.groupware.service.project.ProjectService;

@Controller
public class ProjectController {
    private final HttpSession httpSession;
    private final ProjectService projectService;

    @Autowired
    ProjectController(ProjectService projectService, HttpSession httpSession){
        this.httpSession = httpSession;
        this.projectService = projectService;
    }
    @GetMapping("/projectreg")
    public String projectreg() {
         //로그인하지 않았더라면
        if(httpSession.getAttribute("loginedUser") == null){
            return "redirect:";
        }
        return "projectregform";
    }

    @ResponseBody
    @PostMapping("/regRequest")
    public String regRequest(@ModelAttribute Project_info prjInfo,
        @RequestParam(name="strEndDate") String strEndDate,@RequestParam(name ="imageFile") MultipartFile imageFile) throws Exception{
        
        projectService.registerProject(prjInfo, strEndDate, imageFile);
        return "성공했습니다.";
    }
    
    
}
