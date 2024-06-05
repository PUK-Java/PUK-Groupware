package puk.groupware.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.model.user.User_Info;
import puk.groupware.repository.user.User_info_jpaRepository;

@Service
public class Userservicesave {

    @Autowired
    private User_info_jpaRepository userRepository;

    public void saveUser(User_Info user) {
        // 기본키 값이 중복되는지 체크
        if (userRepository.existsById(user.getUserId())) {
            throw new IllegalArgumentException("아이디가 중복을 확인하세요");
        }

        // 중복되지 않으면 저장
        userRepository.save(user);
    }

    public String userAdminCheck(User_Info userId) {
        if (userId != null) {
            String test1 = (String) userId.getUserId();
            User_Info userInfo = userRepository.findByUserId(test1);
            return userInfo.getAdmin();
        }
        return "null값";

    }

}