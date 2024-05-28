package puk.groupware.domain.project;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

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
import puk.groupware.domain.user.User_info;

@Entity
@Table
@Getter
@Setter
public class Project_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_NO")
    private Long ProjectNo;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USER_ID")
    private User_info userId;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="TARGET_COST")
    private int targetCost;


    @CreationTimestamp
    @Column(name="START_DATE")
    private LocalDateTime startDate;

    @DateTimeFormat
    @Column(name="END_DATE")
    private LocalDateTime endDate;

    @Column(name="IMAGE")
    private String image;

    @Column(name="STATE")
    private String state;

    @Column(name="CATEGORY")
    private String category;
}
