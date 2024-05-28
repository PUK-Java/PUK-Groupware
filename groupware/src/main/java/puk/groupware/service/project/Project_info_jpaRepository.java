package puk.groupware.service.project;

import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.domain.project.Project_info;

public interface Project_info_jpaRepository extends JpaRepository<Project_info, Integer>{
    
}
