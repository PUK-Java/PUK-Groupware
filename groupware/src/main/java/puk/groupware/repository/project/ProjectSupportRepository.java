package puk.groupware.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.model.project.ProjectSupport;
import puk.groupware.model.project.Project_info;

public interface ProjectSupportRepository extends JpaRepository<ProjectSupport, Long> {
    long countByProjectNo(Project_info projectNo);

}
