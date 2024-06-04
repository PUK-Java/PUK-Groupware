package puk.groupware.service.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import puk.groupware.model.qna.QnaCommentInfo;
import puk.groupware.model.qna.QnaInfo;
import puk.groupware.repository.qna.QnaCommentRepository;

@Service
// @RequiredArgsConstructor
public class QnaCommentService {

    @Autowired
    private QnaCommentRepository qnaCommentRepository;

    public List<QnaCommentInfo> getQnaCommentByQnaNo(QnaInfo qnaInfo) {
        return qnaCommentRepository.findByQnaNo(qnaInfo);
    }

    public void saveQnaComment(QnaCommentInfo qnaCommentInfo) {
            
    }
}
