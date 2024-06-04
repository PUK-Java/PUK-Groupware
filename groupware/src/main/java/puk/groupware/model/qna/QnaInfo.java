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
import puk.groupware.model.user.User_Info;

@Getter @Setter
@Entity
@Table(name="QNA_BOARD")
@NoArgsConstructor
public class QnaInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qna_sequence")
    @SequenceGenerator(name="qna_sequence", sequenceName = "qna_seq", allocationSize = 1)    
    private int qnaNo;

    @Column(name="QNA_TITLE", length = 100)
    private String qnaTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QNA_WRITER", referencedColumnName = "USER_ID")
    private User_Info qnaWriter;


    @Column(name="QNA_CONTENT")
    private String qnaContent;

    @Column(name="QNA_WRITEDATE")
    private LocalDateTime qnaWriteDate;

    @Column(name="VIEW_COUNT", columnDefinition = "integer default 0", nullable = false)
    private int viewCount;

    @PrePersist
    protected void onCreate() {
        this.qnaWriteDate = LocalDateTime.now().withSecond(0).withNano(0);
    }
}
