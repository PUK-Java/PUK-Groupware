package puk.groupware.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.model.user.User_Info;
import puk.groupware.repository.user.User_info_jpaRepository;

@Service
public class UserLoginService {
    
    private final User_info_jpaRepository uJpaRepository;

    @Autowired
    UserLoginService(User_info_jpaRepository uJpaRepository){
        this.uJpaRepository = uJpaRepository;
    }

    public User_Info findUser(String userId,String userPw){
        return uJpaRepository.findByUserIdAndUserPw(userId, userPw);
    }

    public User_Info findById(String userId){
        return uJpaRepository.findByUserId(userId);
    }
}
