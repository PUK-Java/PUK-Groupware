package puk.groupware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;
import puk.groupware.repository.user.User_info_jpaRepository;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest {
    private final User_info_jpaRepository user_info_jpaRepository;

    @Autowired
    JpaTest(User_info_jpaRepository user_info_jpaRepository){
        this.user_info_jpaRepository = user_info_jpaRepository;
    }

    @Test
    void s(){
    }
}
