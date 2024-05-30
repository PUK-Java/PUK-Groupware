package puk.groupware.service.project;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.repository.project.Project_info_jpaRepository;
import puk.groupware.model.project.*;

@Service
public class ProjectViewService {
    @Autowired
    private Project_info_jpaRepository project_info;

    public List<Project_info> getAllprojects() {
        return project_info.findAll();
    }

    public Project_info getProjectById(Long id) {
        return project_info.findById(id).orElse(null);
    }

    public long dayBetween(Long projectDate) {
        Project_info project = getProjectById(projectDate);
        if (project != null && project.getStartDate() != null && project.getEndDate() != null) {
            return ChronoUnit.DAYS.between(project.getStartDate(), project.getEndDate());
        }
        return 0;
    }

}
