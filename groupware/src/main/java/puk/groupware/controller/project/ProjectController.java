package puk.groupware.controller.project;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import puk.groupware.domain.project.Project_info;
import puk.groupware.domain.user.User_info;
import puk.groupware.repository.project.Project_info_jpaRepository;

@Controller
@Slf4j
public class ProjectController {
    private final HttpSession httpSession;
    private final Project_info_jpaRepository pJpaRepository;

    @Autowired
    ProjectController(Project_info_jpaRepository pJpaRepository, HttpSession httpSession){
        this.httpSession = httpSession;
        this.pJpaRepository = pJpaRepository;
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

        //이미지에 대한 처리
        if(!imageFile.isEmpty()){
            String imgName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Path uploadPath = Paths.get("src","main","resources","images","projectThumbnails");
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(imgName);
            Files.copy(imageFile.getInputStream(),filePath);

        //문자열로 변환해서 저장
        String originalPath = filePath.toString();
        String convertPath = originalPath.replace("src\\main\\", "");
        prjInfo.setImage(convertPath);
        }
        
        

        
        //시간 타입으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        prjInfo.setStartDate(LocalDate.now());
        LocalDate endDate = LocalDate.parse(strEndDate,formatter);
        prjInfo.setEndDate(endDate);


        //로그인한 유저의 객체 넣어주기
        User_info logineduser = (User_info) httpSession.getAttribute("loginedUser");
        prjInfo.setUserId(logineduser);
        
        pJpaRepository.save(prjInfo);
        return "성공했습니다.";
    }
    
    
}
