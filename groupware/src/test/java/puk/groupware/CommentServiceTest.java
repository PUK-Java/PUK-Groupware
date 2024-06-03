package puk.groupware;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.extern.slf4j.Slf4j;
import puk.groupware.repository.projectComment.ProjectCommentJpaRepository;
import puk.groupware.service.project.ProjectSupportService;
import puk.groupware.service.projectComment.ProjectCommentService;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class CommentServiceTest {
    
    @InjectMocks
    private ProjectCommentService projectCommentService;

    @Mock
    private ProjectCommentJpaRepository projectCommentJpaRepository;

    @Mock
    private ProjectSupportService projectSupportService;

    @Test
    @DisplayName("뭐임?")
    void 서비스테스트(){
        System.out.println(projectSupportService.exexistsByProjectNoandUserId(Long.parseLong(("1")),"dfgddjd"));
    }
}
