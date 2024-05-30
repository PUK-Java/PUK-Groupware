package puk.groupware.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class BoardController  {
    @Autowired
    private final BoardInfoService boardInfoService;

    @Autowired
    public BoardController(BoardInfoService boardInfoService) {
        this.boardInfoService = boardInfoService;
    }
    // request에 home을 받으면 index.jsp로 이동하게 설정
    // 딱히 민감하지는 않지만 회원가입이나 로그인의 상황을 생각해서 POST방식으로 진행
    @PostMapping("/save")
    public String saveOnBoard(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("writer") String writer) {
       
        // service단으로 보내서 실질적인 DB로의 저장은 service에서 실행하도록
        boardInfoService.saveBoardInfo(title, content, writer);
        return "redirect:/index";
    }
    
    // 메인 페이지에서 페이징된 게시물 목록 조회가 이루어 지도록 수정
    @GetMapping("/board/boardmain")
    public String mainBoard(@RequestParam(name="page", required = false, defaultValue = "0") int page, Model model) {
        boardInfoService.getPagingBoard(page, model); // 페이징된 게시물 가져오기
        return "/board/boardindex";                               // 게시글 목록이 포함된 메인 페이지로 이동
    }

    @GetMapping("/write")
    public String writeOnBoard() {
        return "/board/write"; 
    }

    @GetMapping("/detail")
    public String getBoardDetail(@RequestParam("boardNo") int boardNo, Model model) {
        BoardInfo boardInfo = boardInfoService.getBoardByNo(boardNo);
        model.addAttribute("board", boardInfo);
        return "/board/subpage";
    }

    @GetMapping("/boardOnSearchList")
    public String searchBoards(@RequestParam("searchText") String searchText, Model model) {
        List<BoardInfo> boards = boardInfoService.searchBoardsByTitle(searchText);
        model.addAttribute("boards", boards);
        return "/board/boardOnSearchList";
    }
    
    // 게시물 수정 (Update) : 진행중, index를 반환해서 index.jsp로 진입하게 되면 게시물 목록이 출력되지 않음.
    @GetMapping("/updateOnBoard")
    public String updateBoard() {

        return "/board/boardindex";
    }

    // 게시물 삭제 (Delete)
    // @GetMapping("deleteOnBoard")
    // public String deleteBoard(@RequestParam int boardNo) {
    //     // boardNo를 구분자로 하여 테이블의 해당 컬럼을 삭제하도록 서비스 호출
    //     return "/";
    // }
    

}
