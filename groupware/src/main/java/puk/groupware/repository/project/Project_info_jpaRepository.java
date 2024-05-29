package puk.groupware.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.model.project.Project_info;
import java.util.List;

public interface Project_info_jpaRepository extends JpaRepository<Project_info, Integer> {

    Project_info findByProjectNo(Long projectNo);
}
