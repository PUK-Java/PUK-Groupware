package puk.groupware.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import puk.groupware.domain.user.User_info;
import puk.groupware.repository.user.User_info_jpaRepository;

@Service
public class UserService {
    private final User_info_jpaRepository uJpaRepository;
    private final HttpSession httpSession;
    
    @Autowired
    public UserService(User_info_jpaRepository uJpaRepository, HttpSession httpSession){
        this.uJpaRepository = uJpaRepository;
        this.httpSession = httpSession;
    };

    //회원가입
    public void signUp(User_info user_info, String address1, String address2){
        user_info.setAddress(address1 + " " + address2);
        uJpaRepository.save(user_info);
    }

    //로그인
    public void login(User_info user){
        String userId = user.getUserId();
        String userPw = user.getUserPw();
        User_info loginedUser = uJpaRepository.findByUserIdAndUserPw(userId, userPw);
        httpSession.setAttribute("loginedUser", loginedUser);
    }

    //로그인체크
    public boolean loginCheck(){
        if(httpSession.getAttribute("loginedUser") == null){
            return false;
        }
        return true;
    }

    //로그아웃
    public void logout(){
        httpSession.removeAttribute("loginedUser");
    }
}
