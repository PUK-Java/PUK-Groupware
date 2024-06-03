package puk.groupware.service.projectComment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.projectComment.ProjectComment;
import puk.groupware.model.user.User_Info;
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

    //저장하기
    @Transactional
    public void save(Project_info project,User_Info user,String commentContent){
        ProjectComment projectComment = new ProjectComment();

        projectComment.setProjectInfo(project);
        projectComment.setUserInfo(user);
        projectComment.setProjectCommentContent(commentContent);
        
        projectCommentJpaRepository.save(projectComment);
    }
}
