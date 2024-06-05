package puk.groupware.service.projectComment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    //프로젝트 번호로 찾기 날짜 별로 내림 차순
    public List<ProjectComment> findByProjectInfoProjectNoOrderByProjectCommentWritDateTimeDesc(Long projectNo){
        return projectCommentJpaRepository.findByProjectInfoProjectNoOrderByProjectCommentWritDateTimeDesc(projectNo);
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

    

    public Page<ProjectComment> findByProjectInfoProjectNoPage(Long projectNo,org.springframework.data.domain.Pageable page){
        return projectCommentJpaRepository.findByProjectInfoProjectNo(projectNo,page);
    }

    //페이지 나누기 이번에는 5번까지만 가져올 것입니다. 그리고 바로 리스트로 반환할 것입니다.
    public Page<ProjectComment> pageToFirstFiveList(Long projectNo){
        PageRequest pageable = PageRequest.of(0,5,Sort.by("projectCommentWritDateTime").descending());
        return findByProjectInfoProjectNoPage(projectNo, pageable);
    }

    //삭제하기
    public void deleteById(Long projectCommentId){
        projectCommentJpaRepository.deleteById(projectCommentId);
    }
}
