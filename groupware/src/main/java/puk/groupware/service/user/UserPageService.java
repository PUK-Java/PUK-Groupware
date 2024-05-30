package puk.groupware.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import puk.groupware.model.user.User_Info;
import puk.groupware.repository.user.User_info_jpaRepository;

@Service
public class UserPageService {

    private final User_info_jpaRepository uJpaRepository;

    @Autowired
    UserPageService(User_info_jpaRepository uJpaRepository){
        this.uJpaRepository = uJpaRepository;
    }
    
    @Transactional
    public void saveUser(User_Info user){
        uJpaRepository.save(user);
    }

    public int deleteUser(String userId){
        return uJpaRepository.deleteByUserId(userId);
    }
}
