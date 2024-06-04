package puk.groupware.repository.qna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.model.qna.QnaInfo;

public interface QnaRepository extends JpaRepository<QnaInfo, Integer>{
    Page<QnaInfo> findAll(Pageable pageable);

}
