package puk.groupware.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import puk.groupware.model.project.Project_info;
import puk.groupware.service.project.ProjectRegService;

@Controller
@Slf4j
public class ProjectRegController {
    private final ProjectRegService projectService;;

    @Autowired
    ProjectRegController(ProjectRegService projectService) {
        this.projectService = projectService;
    }



    @RequestMapping("/projectregform")
    public String regForm() {
        return "projectregform";
    }
    

    @PostMapping("/regRequest")
    public String regRequest(@ModelAttribute Project_info prjInfo,
        @RequestParam(name="strEndDate") String strEndDate,@RequestParam(name ="imageFile") MultipartFile imageFile)  {
        try{
        projectService.registerProject(prjInfo, strEndDate, imageFile);
        }catch(Exception e){
            return "/projectregform";
        }
        return "success";
    }

}
