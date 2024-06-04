package puk.groupware.repository.qna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puk.groupware.model.qna.QnaCommentInfo;
import puk.groupware.model.qna.QnaInfo;

@Repository
public interface QnaCommentRepository extends JpaRepository<QnaCommentInfo, Integer> {
    List<QnaCommentInfo> findByQnaNo(QnaInfo qnaNo);
}
