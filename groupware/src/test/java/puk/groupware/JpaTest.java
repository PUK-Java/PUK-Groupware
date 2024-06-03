package puk.groupware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import puk.groupware.repository.projectComment.ProjectCommentJpaRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest {
    @Autowired
    private ProjectCommentJpaRepository pcj;

    
    @Test
    void aa(){
        System.out.println(pcj.findByProjectInfoProjectNo(Long.parseLong("1")));
    }
}
