package puk.groupware.model.projectComment;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;

@Table(name = "PROJECTCOMMENT")
@Entity
public class ProjectComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_comment_sequence")
    @SequenceGenerator(name="project_comment_sequence", sequenceName = "project_comment_seq", allocationSize = 1)
    @Column(name = "PROJECT_COMMENT_NO")
    private Long projectCommentNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="USER_ID", referencedColumnName = "USER_ID")
    private User_Info userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="PROJECT_NO", referencedColumnName = "PROJECT_NO")
    private Project_info projectInfo;

    @Column(name="PROJECT_COMMENT_WRITE_DATE")
    private LocalDateTime projectCommentWritDateTime;

    @Column(name="PROJECT_COMMENT_COTENT",length = 100)
    private String projectCommentContent;


    @PrePersist
    protected void onCreate() {
        this.projectCommentWritDateTime = LocalDateTime.now().withSecond(0).withNano(0);
    }
    
}
