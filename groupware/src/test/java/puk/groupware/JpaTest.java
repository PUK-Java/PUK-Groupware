package puk.groupware;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import puk.groupware.repository.wishlist.WishList_jpaRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class JpaTest {
    @MockBean
    private  WishList_jpaRepository wishList_jpaRepository;
   
    @Test
    void test(){
    }
}
