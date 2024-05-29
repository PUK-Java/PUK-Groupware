package puk.groupware.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.repository.user.User_info_jpaRepository;

@Service
public class LoginService {
    @Autowired
    private User_info_jpaRepository userRepository;

    public userRepository findByUserId(String userId){
        
    }
}
