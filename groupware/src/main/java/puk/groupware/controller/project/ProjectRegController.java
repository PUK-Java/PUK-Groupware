package puk.groupware.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import puk.groupware.model.project.Project_info;
import puk.groupware.service.project.ProjectRegService;

@Controller
@RequiredArgsConstructor
public class ProjectRegController {
    private final ProjectRegService projectService;
    private final HttpSession httpSession;



    @RequestMapping("/projectregform")
    public String regForm() {
        if(httpSession.getAttribute("loginUser") == null){
            return "loginform";
        }
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
