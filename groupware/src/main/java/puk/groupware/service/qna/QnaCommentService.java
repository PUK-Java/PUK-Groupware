package puk.groupware.service.qna;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import puk.groupware.model.qna.QnaCommentInfo;
import puk.groupware.model.qna.QnaInfo;
import puk.groupware.repository.qna.QnaCommentRepository;

@Service
@RequiredArgsConstructor

// @RequiredArgsConstructor
public class QnaCommentService {

    private final QnaCommentRepository qnaCommentRepository;
    private final QnaService qnaService;

    public List<QnaCommentInfo> getQnaCommentByQnaNo(QnaInfo qnaInfo) {
        return qnaCommentRepository.findByQnaNo(qnaInfo);
    }

    public void saveQnaComment(int qnaNo, String content) {
        QnaCommentInfo qnaCommentInfo = new QnaCommentInfo();

        QnaInfo qnaInfo = qnaService.getQnaBoardByNo(qnaNo);        
        qnaCommentInfo.setQnaNo(qnaInfo);
        qnaCommentInfo.setQnaCommentContent(content);
        qnaCommentRepository.save(qnaCommentInfo);
    }
}
