package puk.groupware.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_INFO")
public class User_Info {
    @Id
    @Column(name = "USER_ID", length = 14)
    private String userId;

    @Column(name = "USER_PW", length = 20)
    private String userPw;

    @Column(name = "USER_NAME", length = 10)
    private String userName;

    @Column(name = "ADDRESS", length = 50)
    private String address;

    @Column(name = "EMAIL", length = 80)
    private String email;

    @Column(name = "PHONE_NUMBER", length = 13)
    private String phoneNumber;

    @Column(name = "ACCOUNT", length = 30)
    private String account;

    @Column(name = "BIRTH", length = 50)
    private String birth;

    @Column(name = "ADMIN", length = 30)
    private String admin;

}
