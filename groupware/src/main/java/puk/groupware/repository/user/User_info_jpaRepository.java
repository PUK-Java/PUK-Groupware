package puk.groupware.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import puk.groupware.model.user.User_Info;


@Repository
public interface User_info_jpaRepository extends JpaRepository<User_Info, String> {

    User_Info  findByUserIdAndUserPw(String userId, String userPw);

}
