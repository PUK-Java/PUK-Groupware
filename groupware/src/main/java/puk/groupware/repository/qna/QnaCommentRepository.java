package puk.groupware.repository.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.model.qna.QnaCommentInfo;
import puk.groupware.model.qna.QnaInfo;

import java.util.List;


public interface QnaCommentRepository extends JpaRepository<QnaCommentInfo, Integer> {
    List<QnaCommentInfo> findByQnaNo(QnaInfo qnaNo);

}
