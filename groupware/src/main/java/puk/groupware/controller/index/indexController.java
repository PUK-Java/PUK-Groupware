package puk.groupware.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import puk.groupware.service.project.ProejctFindAllService;

//인덱스 페이지 관련
@Controller
public class indexController {
    
    private final ProejctFindAllService pService;
    @Autowired
    indexController(ProejctFindAllService pService){
        this.pService = pService;
    }

    @GetMapping("/")
    public String getAllProject(@RequestParam(name="page", required = false, defaultValue = "0") int page,Model model){
        pService.paging(page,model);
        return "index";
    }
}
    

