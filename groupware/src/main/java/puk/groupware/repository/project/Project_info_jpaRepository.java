package puk.groupware.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.model.project.Project_info;

public interface Project_info_jpaRepository extends JpaRepository<Project_info, Integer>{
    
    
    Page<Project_info> findAll(Pageable pageable);

    Page<Project_info> findByTitleContains(String title, Pageable pageable);
}
