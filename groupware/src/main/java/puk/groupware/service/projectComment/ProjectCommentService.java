package puk.groupware.service.projectComment;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import puk.groupware.model.projectComment.ProjectComment;
import puk.groupware.repository.projectComment.ProjectCommentJpaRepository;

@Service
@RequiredArgsConstructor
public class ProjectCommentService {

    private final ProjectCommentJpaRepository projectCommentJpaRepository;


    //프로젝트 번호로 찾기
    public List<ProjectComment> findByProjectInfoProjectNo(Long projectNo){
        return projectCommentJpaRepository.findByProjectInfoProjectNo(projectNo);
    }

    //유저 이름으로 찾기
    public List<ProjectComment> findByUserInfoUserId(String userId){
        return projectCommentJpaRepository.findByUserInfoUserId(userId);
    }
    
    //프로젝트 번호로 카운트 세기
    public int countByProjectInfoProjectNo(Long projectNo){
        return projectCommentJpaRepository.countByProjectInfoProjectNo(projectNo);
    }
}
