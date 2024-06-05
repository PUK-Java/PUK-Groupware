package puk.groupware.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import puk.groupware.model.board.BoardInfo;
import puk.groupware.model.user.User_Info;
import puk.groupware.repository.board.BoardInfoJpaRepository;
import puk.groupware.service.board.BoardInfoService;
import puk.groupware.service.user.UserPageService;



@Controller
@RequiredArgsConstructor
public class BoardController  {
    
    private final BoardInfoService boardInfoService;
    private final BoardInfoJpaRepository boardInfoJpaRepository;
    private final UserPageService userPageService;
    

    
    // request에 home을 받으면 index.jsp로 이동하게 설정
    // 딱히 민감하지는 않지만 회원가입이나 로그인의 상황을 생각해서 POST방식으로 진행
    @PostMapping("/save")
    public String saveOnBoard(@RequestParam("title") String title, @RequestParam("content") String content, HttpServletRequest request) {
       // 로그인 된 회원만 저장 권한을 부여하도록 (1차적으로 write에서 필터링) 
       HttpSession session = request.getSession();
       if(session.getAttribute("loginUser") == null) {
        return "redirect:/login";
       }
        // service단으로 보내서 실질적인 DB로의 저장은 service에서 실행하도록
        boardInfoService.saveBoardInfo(title, content, request);
        return "redirect:/boardmain";
    }
    
    // 메인 페이지에서 페이징된 게시물 목록 조회가 이루어 지도록 수정
    @GetMapping("/boardmain")
    public String mainBoard(HttpServletRequest request, @RequestParam(name="page", required = false, defaultValue = "0") int page, Model model) {
        HttpSession session = request.getSession();
        // JSP에서 읽어갈 수 있게
        User_Info loginUser = (User_Info)session.getAttribute("loginUser");

        model.addAttribute("loginUser", loginUser);
        if (loginUser != null) {
            boolean isAdmin = userPageService.isAdmin(loginUser.getUserId());
            model.addAttribute("isAdmin", isAdmin);
        }
        boardInfoService.getPagingBoard(page, model); // 페이징된 게시물 가져오기
        return "/board/boardIndex";
    }

    @GetMapping("/write")
    public String writeOnBoard(HttpServletRequest request) {
        // 로그인 되지 않은 사용자는 로그인 창으로 보내기
        HttpSession session = request.getSession();
        // 로그인 컨트롤러에서 세션을 저장할 때 선언한 변수의 값을 getAttribute를 통해 받아와 비교할 것
        if(session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        return "/board/write"; 
    }

    @GetMapping("/detail")
    public String getBoardDetail(@RequestParam("boardNo") int boardNo, Model model) {
        BoardInfo boardInfo = boardInfoService.getBoardByNo(boardNo);
        model.addAttribute("board", boardInfo);
        // 조회수 메서드 호출
        boardInfoService.viewCount(boardNo);
        return "/board/subpage";
    }

    @GetMapping("/boardOnSearchList")
    public String searchBoards(@RequestParam("searchText") String searchText, Model model) {
        List<BoardInfo> boards = boardInfoService.searchBoardsByTitle(searchText);
        model.addAttribute("boards", boards);
        return "/board/boardOnSearchList";
    }

    // 게시물 삭제 (Delete)
    // 삭제 버튼 노출 자체에서 작성자와 로그인 된 회원이 동일한 지 검증되기 때문에 여기서는 별도 검증 X
    @GetMapping("/deleteOnBoard")
    public String deleteOnBoard(@RequestParam("boardNo") int boardNo) {
        boardInfoService.deleteOnBoard(boardNo);
        // boardNo를 구분자로 하여 테이블의 해당 컬럼을 삭제하도록 서비스 호출
        return "redirect:/boardmain";
    }
    
    // 수정 버튼 클릭 시 기존에 작성했던 데이터를 불러와 수정 작성 페이지로 넘겨주는 과정
    @Transactional
    @GetMapping("/update")
    public String preBoardUpdate(@RequestParam("boardNo") int boardNo, Model model) {
        boardInfoService.preUpdate(boardNo, model);
        return "board/boardUpdate";
    }    

    // 게시물 수정 (Update) : 진행중
    // 사용자의 수정 내역을 입력받아 DB에 저장하는 과정
    @Transactional
    @PostMapping("/updateOnBoard")
    public String updateBoard(@RequestParam("boardNo") String strboardNo, @RequestParam("title") String title, @RequestParam("content") String content) {
        int boardNo = Integer.parseInt(strboardNo);
        boardInfoService.saveBoardUpdate(boardNo, title, content);

        return "redirect:/boardmain";
    }

    
    
    
}
