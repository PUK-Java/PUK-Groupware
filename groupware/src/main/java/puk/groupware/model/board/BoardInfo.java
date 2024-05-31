package puk.groupware.model.board;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@Entity
@Table(name="BOARD_INFO")
@NoArgsConstructor
// Entity 클래스
// 우선적으로 번호, 제목, 내용, 날짜만 작성
public class BoardInfo {
    // 이 컬럼이 해당 테이블의 기본키임을 선언
    // 게시번호를 시퀀스로 설정, 다른 컬럼과 달리 사용자 입력 없이 저장
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_sequence")
    @SequenceGenerator(name="board_sequence", sequenceName = "board_seq", allocationSize = 1)    
    private int boardNo;

    @Column(name="TITLE")
    private String title;

    @Column(name="WRITER")
    private String writer;

    @Column(name="CONTENT")
    private String content;

    @Column(name="WRITE_DATE")
    private LocalDateTime writeDate;

    @Column(name="VIEW_COUNT", columnDefinition = "integer default 0", nullable = false)
    private int viewCount;

    @PrePersist
    protected void onCreate() {
        this.writeDate = LocalDateTime.now().withSecond(0).withNano(0);
    }
}
