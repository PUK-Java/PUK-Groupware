package puk.groupware.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puk.groupware.model.project.Project_info;

@Repository
public interface Project_info_jpaRepository extends JpaRepository<Project_info, Long> {

    Page<Project_info> findAll(Pageable pageable);

    Page<Project_info> findByTitleContains(String title, Pageable pageable);

    Page<Project_info> findByCategory(String category, Pageable pageable);

    Page<Project_info> findByTitleContainsAndCategory(String title, String category, Pageable pageable);

}
