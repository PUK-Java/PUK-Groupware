package puk.groupware.service.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import puk.groupware.model.board.BoardInfo;
import puk.groupware.repository.board.BoardInfoJpaRepository;

@Service
public class BoardInfoService {

    @Autowired
    private final BoardInfoJpaRepository boardInfoJpaRepository;
   

    @Autowired
    public BoardInfoService(BoardInfoJpaRepository boardInfoJpaRepository) {
        this.boardInfoJpaRepository = boardInfoJpaRepository;
    }

    // BoradInfoJpaRepository 객체의 save 메서드를 통해 파라미터로 들어온 boardInfo 객체를 DB로 저장
    // 하려고 했으나, 게시물 번호, 작성자는 사용자 입력없이 진행 될 예정이라 입력에 받은 항목을 파라미터로 지정
    public BoardInfo saveBoardInfo(String title, String content, String writer) {
        BoardInfo boardInfo = new BoardInfo();
        boardInfo.setTitle(title);
        boardInfo.setContent(content);
        // 로그인 구현 이후에는 사용자 입력으로 받지말고 세션에서 회원명 받아와서 수정해줄것.
        boardInfo.setWriter(writer);
        return boardInfoJpaRepository.save(boardInfo);
    }

    // public List<BoardInfo> getAllBoards() {
    //     return boardInfoJpaRepository.findAll();
    // }

    public BoardInfo getBoardByNo(int boardNo) {
        Optional<BoardInfo> boardInfoOptional = boardInfoJpaRepository.findById(boardNo);
            return boardInfoOptional.get();
    }

    // 메인 화면 출력 페이지 개수 조절
    public void getPagingBoard(int page, Model model) {
        PageRequest pagable = PageRequest.of(page,10,Sort.by("boardNo").descending());
        Page<BoardInfo> pageNumber = boardInfoJpaRepository.findAll(pagable);
        List<BoardInfo> boards = pageNumber.getContent();
        model.addAttribute("boards", boards);
        model.addAttribute("pageNumber", pageNumber);
    } 


    public List<BoardInfo> searchBoardsByTitle(String title) {
        return boardInfoJpaRepository.findByTitleContaining(title);
    }

}
