package puk.groupware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.model.user.User_info;
import puk.groupware.repository.user.User_info_jpaRepository;

public interface UserRepository extends JpaRepository<User_info_jpaRepository, String> {

}
