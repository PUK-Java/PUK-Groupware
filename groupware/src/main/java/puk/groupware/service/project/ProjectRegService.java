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

import jakarta.servlet.http.HttpSession;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.repository.project.Project_info_jpaRepository;

@Service
public class ProjectRegService {
    private final Project_info_jpaRepository pRepository;
    private final HttpSession httpSession;

    @Autowired
    ProjectRegService(Project_info_jpaRepository pRepository, HttpSession httpSession) {
        this.pRepository = pRepository;
        this.httpSession = httpSession;
    }

    // 상품 등록 서비스
    public void registerProject(Project_info prjInfo, String strEndDate, MultipartFile imageFile)
            throws Exception {

        // 이미지에 대한 처리
        if (!imageFile.isEmpty()) {
            String imgName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Path uploadPath = Paths.get("src", "main", "resources", "static", "images", "projectThumbnails");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(imgName);
            Files.copy(imageFile.getInputStream(), filePath);

            // 이미지명 저장
            prjInfo.setImage(imgName);
        }

        // 시간 타입으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        prjInfo.setStartDate(LocalDate.now());
        LocalDate endDate = LocalDate.parse(strEndDate, formatter);
        prjInfo.setEndDate(endDate);

        // 로그인한 유저의 객체 넣어주기 (로그인 Service에서 구현해주길 바람)
        User_Info logineduser = (User_Info) httpSession.getAttribute("loginUser");
        prjInfo.setUserId(logineduser);

        // STATE를 1로 저장
        prjInfo.setState("1");

        // 저장하기
        pRepository.save(prjInfo);

    }

}
