package puk.groupware.controller.projectComment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.service.project.ProjectFindService;
import puk.groupware.service.projectComment.ProjectCommentService;
import puk.groupware.service.user.UserLoginService;;


@Controller
@RequiredArgsConstructor
public class ProjectCommentController {
    private final ProjectFindService projectFindService;
    private final UserLoginService userLoginService;
    private final ProjectCommentService projectCommentService;


    @Transactional
    @ResponseBody
    @PostMapping("/projectCommentReg")
    public Map<String,Object> projectCommentReg(@RequestBody Map<String,Object> body) {
        //받아온 JSON 정보에서 정보들 꺼내서 변수에 저장하기
        String loginUserId = (String) body.get("loginUserId");
        Long projectNo = Long.valueOf((String) body.get("projectNo"));
        String commentContent = (String) body.get("commentContent");

        // 해당 정보로 각각의 객체 찾기.
        User_Info user = userLoginService.findById(loginUserId);
        Project_info project = projectFindService.findById(projectNo);
        
        //저장 메서드 불러오기
        projectCommentService.save(project, user, commentContent);

        Map<String,Object> response = new HashMap<>();
        response.put("success",true);
        return response;
    }
    
}
