package puk.groupware.controller.qna;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import puk.groupware.model.qna.QnaCommentInfo;
import puk.groupware.model.qna.QnaInfo;
import puk.groupware.model.user.User_Info;
import puk.groupware.repository.qna.QnaRepository;
import puk.groupware.service.qna.QnaCommentService;
import puk.groupware.service.qna.QnaService;
import puk.groupware.service.user.UserPageService;

@Controller
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;
    private final QnaRepository qnaRepository;
    private final QnaCommentService qnaCommentService;
    private final UserPageService userPageService;
    private HttpSession httpSession;

    
  

    // request에 home을 받으면 index.jsp로 이동하게 설정
    // 딱히 민감하지는 않지만 회원가입이나 로그인의 상황을 생각해서 POST방식으로 진행
    @PostMapping("/qnaSave")
    public String saveOnBoard(@RequestParam("title") String title, @RequestParam("content") String content, HttpServletRequest request) {
       // 로그인 된 회원만 저장 권한을 부여하도록 (1차적으로 write에서 필터링) 
       httpSession = request.getSession();
       if(httpSession.getAttribute("loginUser") == null) {
        return "redirect:/login";
       }
        // service단으로 보내서 실질적인 DB로의 저장은 service에서 실행하도록
        qnaService.saveQnaInfo(title, content, request);
        return "redirect:/qnamain";
    }
    
    // 메인 페이지에서 페이징된 게시물 목록 조회가 이루어 지도록 수정
    @GetMapping("/qnamain")
    public String qnaMainBoard(HttpServletRequest request, @RequestParam(name="page", required = false, defaultValue = "0") int page, Model model) {
        httpSession = request.getSession();
        User_Info loginUser = (User_Info)httpSession.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("CurrunetPage", page);
        qnaService.getQnaPagingBoard(page, model); // 페이징된 게시물 가져오기
        return "/qboard/qnaIndex"; 
    }



    @GetMapping("/qnaWrite")
    public String writeOnBoard(HttpServletRequest request) {
        // 로그인 되지 않은 사용자는 로그인 창으로 보내기
        HttpSession session = request.getSession();
        // 로그인 컨트롤러에서 세션을 저장할 때 선언한 변수의 값을 getAttribute를 통해 받아와 비교할 것
        if(session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        
        return "/qboard/qnaWrite"; 
    }

    @GetMapping("/qnadetail")
    public String getQnaDetail(HttpServletRequest request, @RequestParam("qnaNo") int qnaNo, Model model) {
        httpSession = request.getSession();
        User_Info loginUser = (User_Info)httpSession.getAttribute("loginUser");
        if (loginUser != null) {
            boolean isAdmin = userPageService.isAdmin(loginUser.getUserId());
            model.addAttribute("isAdmin", isAdmin);
        }
        model.addAttribute("loginUser", loginUser);

        
        QnaInfo qnaInfo = qnaService.getQnaBoardByNo(qnaNo);
        model.addAttribute("board", qnaInfo);
        List<QnaCommentInfo> comments = qnaCommentService.getQnaCommentByQnaNo(qnaInfo);
        model.addAttribute("comments", comments);
        // 조회수 메서드 호출
        qnaService.qnaViewCount(qnaNo);

        return "/qboard/qnaDetail";
    }

    // @GetMapping("/boardOnSearchList")
    // public String searchBoards(@RequestParam("searchText") String searchText, Model model) {
    //     List<BoardInfo> boards = boardInfoService.searchBoardsByTitle(searchText);
    //     model.addAttribute("boards", boards);
    //     return "/board/boardOnSearchList";
    // }

    // // 게시물 삭제 (Delete)
    // // 삭제 버튼 노출 자체에서 작성자와 로그인 된 회원이 동일한 지 검증되기 때문에 여기서는 별도 검증 X
    @GetMapping("/deleteQna")
    public String deleteQna(@RequestParam("qnaNo") int qnaNo) {
        qnaService.deleteOnQna(qnaNo);
        // boardNo를 구분자로 하여 테이블의 해당 컬럼을 삭제하도록 서비스 호출
        return "redirect:/qnamain";
    }
    
    // // 수정 버튼 클릭 시 기존에 작성했던 데이터를 불러와 수정 작성 페이지로 넘겨주는 과정
    @Transactional
    @GetMapping("/preQnaUpdate")
    public String preQnaUpdate(@RequestParam("qnaNo") int qnaNo, Model model) {
        qnaService.preUpdateQna(qnaNo, model);
        return "qboard/qnaModify";
    }    

    // // 게시물 수정 (Update) : 진행중
    // // 사용자의 수정 내역을 입력받아 DB에 저장하는 과정
    @Transactional
    @PostMapping("/updateOnQna")
    public String updateQna(@RequestParam("qnaNo") String strQnaNo, @RequestParam("title") String title, @RequestParam("content") String content) {
        int qnaNo = Integer.parseInt(strQnaNo);
        qnaService.saveQnaUpdate(qnaNo, title, content);

        // return "redirect:/qnamain";
        return "redirect:/qnamain?success=true";
    }

    
    @GetMapping("/addComment")
    public String saveComment(@RequestParam("qnaNo") int qnaNo, @RequestParam("content") String content){
        QnaInfo qnaInfo = qnaService.getQnaBoardByNo(qnaNo);

        // qnaInfo.setQnaTitle(title);
        qnaCommentService.saveQnaComment(qnaNo, content);
        // return qnaRepository.save(qnaInfo);
        return "redirect:/qnadetail?qnaNo=" + qnaNo;
    }
}
