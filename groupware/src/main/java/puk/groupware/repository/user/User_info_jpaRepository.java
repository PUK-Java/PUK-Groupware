package puk.groupware.repository.user;



import org.springframework.data.jpa.repository.JpaRepository;
import puk.groupware.model.user.User_info;



public interface User_info_jpaRepository extends JpaRepository<User_info, String> {

    User_info findByUserIdAndUserPw(String userId, String userPw);

}
