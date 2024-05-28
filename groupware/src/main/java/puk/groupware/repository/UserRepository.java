package puk.groupware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.model.User_Info;

public interface UserRepository extends JpaRepository<User_Info, String> {

}
