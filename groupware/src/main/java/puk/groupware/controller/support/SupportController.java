package puk.groupware.controller.support;


import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import puk.groupware.model.project.ProjectSupport;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.repository.project.Project_info_jpaRepository;

@Controller
@RequiredArgsConstructor
public class SupportController {

    private final Project_info_jpaRepository pJpaRepository;
    private final HttpSession httpSession;

    @ResponseBody
    @GetMapping("/support/{projectNo}")
    public String support(@PathVariable Long projectNo) {
        Project_info project_info =  pJpaRepository.findById(projectNo).orElseThrow();
        User_Info user_Info = (User_Info) httpSession.getAttribute("loginUser");
        ProjectSupport projectSupport = new ProjectSupport();

        projectSupport.setProjectNo(project_info);
        projectSupport.setUserId(user_Info);
        projectSupport.setDate(LocalDateTime.now());

        
        return new String();
    }
    
}
