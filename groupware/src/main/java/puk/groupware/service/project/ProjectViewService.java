package puk.groupware.service.project;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.repository.project.Project_info_jpaRepository;
import puk.groupware.model.project.*;

@Service
public class ProjectViewService {
    @Autowired
    private Project_info_jpaRepository project_info;

    public Optional<Project_info> getExampleById(Integer id) {
        return project_info.findById(id);
    }

    public Project_info findByProjectNo(Long projectNo) {
        return findByProjectNo(projectNo);
    }

}
