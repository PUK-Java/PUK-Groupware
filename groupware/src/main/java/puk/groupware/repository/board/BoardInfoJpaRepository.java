package puk.groupware.repository.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import puk.groupware.model.board.BoardInfo;

// 게시판 정보를 DB로 매핑하기 위함
@Repository
public interface BoardInfoJpaRepository extends JpaRepository<BoardInfo, Integer> {

    // 게시물 페이징 처리 목적
    Page<BoardInfo> findAll(Pageable pageable);
    // 입력된 제목으로 게시물 검색 목적
    List<BoardInfo> findByTitleContaining(String title);

    // @Modifying
    // @Query("UPDATE BOARD_INFO SET VIEW_COUNT = VIEW_COUNT + 1 WHERE BOARDNO = :boardNo")
    // int updateViewCount(int boardNo);
}