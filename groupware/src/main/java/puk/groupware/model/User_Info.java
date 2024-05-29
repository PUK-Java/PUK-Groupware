package puk.groupware.model;

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
    private String ID;
    @Column(name = "USER_NAME", length = 10)
    private String NAME;
    @Column(name = "USER_PW", length = 20)
    private String PW;
    @Column(name = "ADDRESS", length = 50)
    private String ADDRESS;
    @Column(name = "EMAIL", length = 80)
    private String EMAIL;
    @Column(name = "PHONE_NUMBER", length = 13)
    private String PHONE_NUMBER;
    @Column(name = "ACCOUNT", length = 30)
    private String ACCOUNT;
    @Column(name = "BIRTH", length = 50)
    private String BIRTH;
    @Column(name = "ADMIN", length = 1)
    private String ADMIN;

}
