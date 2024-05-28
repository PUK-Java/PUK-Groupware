package puk.groupware.domain.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@ToString
@Setter
public class User_info {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;
    
    @Column(name="USER_PW")
    private String userPw;

    @Column(name="ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;

    @Column(name="ACCOUNT")
    private int account;

    @Column(name="BIRTH")
    private int birth;

    @Column(name="ADMIN")
    private String admin;
}
