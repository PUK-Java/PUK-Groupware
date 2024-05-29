package puk.groupware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.model.User_Info;
import puk.groupware.repository.UserRepository;

@Service
public class Userserviceget {
    @Autowired
    private UserRepository userRepository;

}
