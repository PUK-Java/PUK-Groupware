package puk.groupware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.model.user.User_info;
import puk.groupware.repository.UserRepository;
import puk.groupware.repository.user.User_info_jpaRepository;

@Service
public class Userservicesave {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User_info_jpaRepository user) {
        // 기본키 값이 중복되는지 체크
        if (userRepository.existsById(user.getID())) {
            throw new IllegalArgumentException("User with the same ID already exists!");
        }

        // 중복되지 않으면 저장
        userRepository.save(user);
    }
}