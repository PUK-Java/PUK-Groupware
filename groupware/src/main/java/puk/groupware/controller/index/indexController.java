package puk.groupware.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.project.ProjectFindService;
import puk.groupware.service.user.Userservicesave;

//인덱스 페이지 관련
@Controller
public class indexController {

    private final Userservicesave userFind;
    private final ProjectFindService pService;
    private final HttpSession httpSession;

    @Autowired
    indexController(ProjectFindService pService, Userservicesave userFind, HttpSession httpSession) {
        this.pService = pService;
        this.userFind = userFind;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String getAllProject(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "projectName", required = false) String projectName,
            @RequestParam(name = "projectCategory", required = false) String projectCategory,
            Model model) {
        pService.paging(page, projectName, projectCategory, model);

        if (httpSession != null) {
            User_Info loginUser = (User_Info) httpSession.getAttribute("loginUser");
            String adminCheck = userFind.userAdminCheck(loginUser);
            model.addAttribute("adminCheck", adminCheck);
        }

        return "index";
    }

}
