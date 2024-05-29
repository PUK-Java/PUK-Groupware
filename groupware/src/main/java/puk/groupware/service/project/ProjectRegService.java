package puk.groupware.service.project;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import puk.groupware.model.project.Project_info;
import puk.groupware.repository.project.Project_info_jpaRepository;

@Service
public class ProjectRegService {
        private final Project_info_jpaRepository pRepository;

        @Autowired
        ProjectRegService(Project_info_jpaRepository pRepository){
            this.pRepository = pRepository;
        }
        

        //상품 등록 서비스
        public void registerProject(Project_info prjInfo, String strEndDate, MultipartFile imageFile) throws Exception {
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


        //로그인한 유저의 객체 넣어주기 (로그인 Service에서 구현해주길 바람)
        //User_info logineduser = (User_info) httpSession.getAttribute("loginedUser");
        //prjInfo.setUserId(logineduser);
        
        //저장하기
        pRepository.save(prjInfo);
    }
}
