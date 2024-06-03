package puk.groupware.repository.projectComment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puk.groupware.model.projectComment.ProjectComment;

@Repository
public interface ProjectCommentJpaRepository extends JpaRepository<ProjectComment,Long>{
    Optional<ProjectComment> findByProjectInfoProjectNo(Long projectNo);
}
