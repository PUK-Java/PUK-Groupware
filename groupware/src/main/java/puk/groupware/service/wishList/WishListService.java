package puk.groupware.service.wishList;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import puk.groupware.model.wishlist.WishList;
import puk.groupware.model.wishlist.WishListId;
import puk.groupware.repository.wishlist.WishList_jpaRepository;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishList_jpaRepository wishList_jpaRepository;

    public Optional<WishList> findById(WishListId wishListId){
        return wishList_jpaRepository.findById(wishListId);
    }

    public void deleteById(WishListId wishListId){
        wishList_jpaRepository.deleteById(wishListId);
    }

    @Transactional
    public boolean toggleWishList(WishList wishList,WishListId wishListId){
        if(findById(wishList.getWishListId()).isPresent()){
            deleteById(wishList.getWishListId());
            return false;
        }else{
            wishList.setWishListId(wishListId);
            wishList_jpaRepository.save(wishList);
            return true;
        }
    }
}
