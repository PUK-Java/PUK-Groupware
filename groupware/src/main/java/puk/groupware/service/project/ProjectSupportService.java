package puk.groupware.service.project;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.model.project.ProjectSupport;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.repository.project.ProjectSupportRepository;
import puk.groupware.repository.project.Project_info_jpaRepository;

@Service
public class ProjectSupportService {
    private final ProjectSupportRepository supportRepository;
    private final Project_info_jpaRepository projectRepository;

    // 후원 테이블에 접근하는 정보를 처리하는 서비스
    @Autowired
    public ProjectSupportService(ProjectSupportRepository supportRepository,
            Project_info_jpaRepository projectRepository) {
        this.supportRepository = supportRepository;
        this.projectRepository = projectRepository;
    }

    public Project_info getProjectById(Long id) {
        // 기본키로 정보찾기
        return projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));
    }

    // 후원하기 테이블에 정보를 저장하는 메소드
    public void saveSupport(User_Info userInfo, Long projectInfo) {
        // 후원 테이블은 유저,프로젝트 테이블에 영향을 받기때문에 2개 값을 다 불러온다.
        Project_info project = getProjectById(projectInfo);
        ProjectSupport projectSupport = new ProjectSupport();
        // 후원 테이블의 유저아이디는 세션에 저장된 userId를 넣고
        // projectNo는 프로젝트의 기본키를 넣는다.
        // 값 불러오는 작업 *set
        projectSupport.setUserId(userInfo);
        projectSupport.setProjectNo(project);
        // 날짜는 datetime을 사용
        projectSupport.setDate(LocalDateTime.now());
        // 세션에 값이 없을시 저장하지 않기위한 조건
        if (userInfo != null) {
            supportRepository.save(projectSupport);
        }

    }

    // count하는 메소드
    public long getcount(Long projectNo) {
        Project_info project = getProjectById(projectNo);
        // 후원테이블의 값을 외래키로 count 해준다.
        return supportRepository.countByProjectNo(project);
    }

    // DB에 프로젝트상태 컬럼을 바꾸는 메소드
    public void stateUp(Long projectNo, int target, int cost) {
        Project_info project = getProjectById(projectNo);
        project.setTargetCost(target);
        project.setCost(cost);
        getcount(projectNo);
        // 불러온 값들을 계산하기위해 변수에 넣는다.
        int var1 = project.getTargetCost();
        int var2 = project.getCost();
        long var3 = getcount(projectNo);
        // 목표금액이 count한 값과 cost 금액의 곱보다 작을시 상태는 1에서 0으로 수정한다.
        if (var2 * var3 >= var1) {
            project.setState("0");
            projectRepository.save(project);
        }
    }
}
