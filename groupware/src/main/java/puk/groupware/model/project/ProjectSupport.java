package puk.groupware.model.project;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import puk.groupware.model.user.User_Info;

@Entity
@Table(name = "SPON")
@Getter
@Setter
public class ProjectSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPON_NO")
    private Long sponNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User_Info userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PROJECT_INFO", referencedColumnName = "PROJECT_NO")
    private Project_info projectNo;

    @Column(name = "SPON_DATE")
    private LocalDateTime date;

}