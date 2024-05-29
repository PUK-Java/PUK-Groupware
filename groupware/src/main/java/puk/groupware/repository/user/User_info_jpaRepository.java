package puk.groupware.repository.user;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< Updated upstream
import java.util.Optional;
import puk.groupware.model.user.User_info;
import java.util.List;
=======
import puk.groupware.model.user.User_Info;



public interface User_info_jpaRepository extends JpaRepository<User_Info, String> {
>>>>>>> Stashed changes

    User_Info findByUserIdAndUserPw(String userId, String userPw);


@Repository
public interface User_info_jpaRepository extends JpaRepository<User_info, String>{
    // id를 통해 사용자를 조회
    //optional을 null point exception 방지
    User_info findByUserId(String userId);
    // User_info findByUserIdAndUserPw(String userId, String userPw);
}
