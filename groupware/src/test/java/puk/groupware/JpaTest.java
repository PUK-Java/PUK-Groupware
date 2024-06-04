package puk.groupware;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;
import puk.groupware.model.wishlist.WishList;
import puk.groupware.repository.wishlist.WishList_jpaRepository;
import puk.groupware.service.wishList.WishListService;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
public class JpaTest {
    private final WishListService wishListService;
   
}
