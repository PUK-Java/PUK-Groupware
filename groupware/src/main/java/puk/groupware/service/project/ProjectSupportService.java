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

    @Autowired
    public ProjectSupportService(ProjectSupportRepository supportRepository,
            Project_info_jpaRepository projectRepository) {
        this.supportRepository = supportRepository;
        this.projectRepository = projectRepository;
    }

    public Project_info getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));
    }

    public void saveSupport(User_Info userInfo, Long projectInfo) {
        Project_info project = getProjectById(projectInfo);
        ProjectSupport projectSupport = new ProjectSupport();

        projectSupport.setUserId(userInfo);
        projectSupport.setProjectNo(project);
        projectSupport.setDate(LocalDateTime.now());

        supportRepository.save(projectSupport);
    }

    public long getcount(Long projectNo) {
        Project_info project = getProjectById(projectNo);
        return supportRepository.countByProjectNo(project);
    }

    public void stateUp(Long projectNo, int target, int cost) {
        Project_info project = getProjectById(projectNo);
        project.setTargetCost(target);
        project.setCost(cost);
        getcount(projectNo);
        int var1 = project.getTargetCost();
        int var2 = project.getCost();
        long var3 = getcount(projectNo);

        if (var2 * var3 >= var1) {
            project.setState("0");
            projectRepository.save(project);
        }
    }
}
