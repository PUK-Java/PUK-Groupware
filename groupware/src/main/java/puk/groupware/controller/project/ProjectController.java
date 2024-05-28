package puk.groupware.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
    
    @GetMapping("/projectreg")
    public String projectreg() {
        return "projectregform";
    }
    
}
