package puk.groupware.service.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import puk.groupware.repository.project.Project_info_jpaRepository;
import puk.groupware.model.project.*;

@Service
public class ProjectViewService {
    private final Project_info_jpaRepository projectRepository;

    @Autowired
    public ProjectViewService(Project_info_jpaRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project_info> getAllprojects() {
        return projectRepository.findAll();
    }

    // project를 넘버로 찾는다
    public Project_info getProjectById(Long id) {

        return projectRepository.findById(id).orElse(null);
    }

    // 시작일과 종료일의 차이를 일수로 나타내기위한 서비스
    public long dayBetween(Long projectDate) {
        Project_info project = getProjectById(projectDate);
        // 둘다 null값이 아닐시 작동한다.
        if (project != null && project.getStartDate() != null && project.getEndDate() != null) {
            // 두 날짜의 차이를 계산하는 라이브러리
            return ChronoUnit.DAYS.between(project.getStartDate(), project.getEndDate());
        }
        return 0;
    }

    // 프로젝트 수정을 완료하면 DB에 저장하는 서비스

    public void upProject(Long id, Project_info project, String strEndDate, MultipartFile imageFile) {
        Project_info project1 = getProjectById(id);
        // 변수 수정필요
        Long testData = project1.getProjectNo();
        // 프로젝트페이지 파라미터와 프로젝트넘버를 비교
        if (id == testData) {
            try {
                // image 처리
                if (!imageFile.isEmpty()) {
                    String imgName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                    Path uploadPath = Paths.get("src", "main", "resources", "static", "images", "projectThumbnails");
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    Path filePath = uploadPath.resolve(imgName);
                    Files.copy(imageFile.getInputStream(), filePath);

                    // 이미지명 저장
                    project1.setImage(imgName);
                }
            } catch (Exception e) {
                System.out.println("오류발생!!!");
            }
            project1.setTitle(project.getTitle());
            project1.setProjectNo(testData);
            project1.setCategory(project.getCategory());
            project1.setCost(project.getCost());
            project1.setDescription(project.getDescription());
            project1.setStartDate(project.getStartDate());
            project1.setState("1");
            project1.setTargetCost(project.getTargetCost());
            // 날짜 처리
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            project1.setStartDate(LocalDate.now());
            LocalDate endDate = LocalDate.parse(strEndDate, formatter);
            project1.setEndDate(endDate);
            // DB에 수정된 값을 저장
            System.out.print("저장중....");
            System.out.print(project.getCategory());
            projectRepository.save(project1);
            System.out.print("저장완료");
        }
    }

}
