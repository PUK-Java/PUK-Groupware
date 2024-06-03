package puk.groupware.service.wishList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;
import puk.groupware.model.wishlist.WishList;
import puk.groupware.model.wishlist.WishListId;
import puk.groupware.repository.wishlist.WishList_jpaRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishListService {

    private final WishList_jpaRepository wishList_jpaRepository;
    private final HttpSession httpSession;


    public Optional<WishList> findById(WishListId wishListId){
        return wishList_jpaRepository.findById(wishListId);
    }

    @Transactional
    public List<WishList> findByWishListIdUserInfoUserId(String userId){
        return wishList_jpaRepository.findByWishListIdUserInfoUserId(userId);
    }

    public void deleteById(WishListId wishListId){
        wishList_jpaRepository.deleteById(wishListId);
    }

    //위시리스트가 존재하면 삭제하고 없으면 추가하는 메소드
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


    //WishList가 존재하는지 찾는 메소드
    public boolean checkWishList(Project_info project){
        //유저 객체 찾기
        User_Info user = (User_Info) httpSession.getAttribute("loginUser");
        if(user == null){
            return false;
        }

        //WishListId 매핑 시켜줄 객체 생성
        WishListId wishListId = new WishListId();
        wishListId.setUserInfo(user);
        wishListId.setProjectInfo(project);
        if(findById(wishListId).isPresent()){
            return true;
        }else{
            return false;
        }
    }

  
}
