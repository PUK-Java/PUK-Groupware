package puk.groupware.service.user;



import org.springframework.data.jpa.repository.JpaRepository;

import puk.groupware.domain.user.User_info;



public interface User_info_jpaRepository extends JpaRepository<User_info, String>{

    User_info findByUserIdAndUserPw(String userId, String userPw);
}
