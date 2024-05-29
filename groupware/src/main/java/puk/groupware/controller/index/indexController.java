package puk.groupware.controller.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import puk.groupware.model.project.Project_info;
import puk.groupware.service.project.ProejctFindAllService;


@Controller
public class indexController {
    
    private final ProejctFindAllService pService;
    @Autowired
    indexController(ProejctFindAllService pService){
        this.pService = pService;
    }

    @GetMapping("/")
    public String getAllProject(@RequestParam(name="page", required = false, defaultValue = "0") int page,Model model){
        PageRequest pageable = PageRequest.of(page,9,Sort.by("startDate").descending());
        Page<Project_info> pageNumber = pService.findAll(pageable);
        List<Project_info> projects = pageNumber.getContent();
        
        model.addAttribute("projects", projects);
        return "index";
    }
}
    

