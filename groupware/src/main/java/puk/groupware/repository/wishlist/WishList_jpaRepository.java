package puk.groupware.repository.wishlist;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import puk.groupware.model.wishlist.WishList;
import puk.groupware.model.wishlist.WishListId;

@Repository
public interface WishList_jpaRepository extends JpaRepository<WishList, WishListId>{

    
    Optional<WishList> findById(WishListId id);

    
    void deleteById(WishListId id);

    List<WishList> findByWishListIdUserInfoUserId(String userId);
}
