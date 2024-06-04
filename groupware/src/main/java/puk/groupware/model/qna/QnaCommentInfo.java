package puk.groupware.model.qna;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="QNA_COMMENT")
@NoArgsConstructor
public class QnaCommentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qna_comment_sequence")
    @SequenceGenerator(name="qna_comment_sequence", sequenceName = "qna_comment_seq", allocationSize = 1)    
    private int qnaCommentNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QNA_NO", referencedColumnName = "QNANO")
    private QnaInfo qnaNo;

    @Column(name="QNA_COMMENT_CONTENT")
    private String qnaCommentContent;

    @Column(name="QNA_COMMENT_WRITEDATE")
    private LocalDateTime qnaCommentWriteDate;

    @PrePersist
    protected void onCreate() {
        this.qnaCommentWriteDate = LocalDateTime.now().withSecond(0).withNano(0);
    }
}

