package puk.groupware.repository.projectComment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puk.groupware.model.projectComment.ProjectComment;

@Repository
public interface ProjectCommentJpaRepository extends JpaRepository<ProjectComment,Long>{

    //프로젝트 번호로 찾기
    List<ProjectComment> findByProjectInfoProjectNo(Long projectNo);

    //유저 이름으로 찾기
    List<ProjectComment> findByUserInfoUserId(String userId);

    //프로젝트 번호로 count세기
    int countByProjectInfoProjectNo(Long projectNo);


    //페이지 가져오기
    Page<ProjectComment> findByProjectInfoProjectNo(Long projectNo,Pageable page);
}
