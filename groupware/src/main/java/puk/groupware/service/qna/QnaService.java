package puk.groupware.service.qna;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import puk.groupware.model.board.BoardInfo;
import puk.groupware.model.qna.QnaInfo;
import puk.groupware.model.user.User_Info;
import puk.groupware.repository.qna.QnaRepository;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    

    public void getQnaPagingBoard(int page, Model model) {
        PageRequest pagable = PageRequest.of(page,10,Sort.by("qnaNo").descending());
        Page<QnaInfo> pageNumber = qnaRepository.findAll(pagable);
        List<QnaInfo> qnaBoards = pageNumber.getContent();
        model.addAttribute("qnaBoards", qnaBoards);
        model.addAttribute("pageNumber", pageNumber);
    }

    // 신규 저장
    public QnaInfo saveQnaInfo(String title, String content, HttpServletRequest request) {
        // 작성자를 입력받지 않고, 로그인 된 유저의 ID를 자동 저장 해보자
        HttpSession session = request.getSession();
        User_Info loginUser = (User_Info)session.getAttribute("loginUser");
        QnaInfo qnaInfo = new QnaInfo();
        qnaInfo.setQnaTitle(title);
        qnaInfo.setQnaContent(content);
        // 로그인 구현 이후에는 사용자 입력으로 받지말고 세션에서 회원명 받아와서 수정해줄것.
        qnaInfo.setQnaWriter(loginUser);
        return qnaRepository.save(qnaInfo);
    }

    public QnaInfo getQnaBoardByNo(int qnaNo) {
        Optional<QnaInfo> qnaInfoOptional = qnaRepository.findById(qnaNo);
            return qnaInfoOptional.get();
    }

    @Transactional
    public void qnaViewCount(int qnaNo) {
        // 컨트롤러에서 호출받으면 BoardInfo의 setViewCount 호출
        QnaInfo qnaInfo = qnaRepository.findById(qnaNo).get();
        qnaInfo.setViewCount(qnaInfo.getViewCount() + 1);
    }

    // 수정 전 기존 정보 가져오기
    @Transactional
    public void preUpdateQna(int qnaNo, Model model) {
        QnaInfo qnaInfo = qnaRepository.findById(qnaNo).get();
        model.addAttribute("qnaNo", qnaInfo.getQnaNo());
        model.addAttribute("qnaTitle", qnaInfo.getQnaTitle());
        model.addAttribute("qnaContent", qnaInfo.getQnaContent());
    }

    // QnA 삭제 - 본인 작성에 한해
    @Transactional
    public void deleteOnQna(int qnaNo) {
        
        qnaRepository.deleteById(qnaNo);
    }
}
