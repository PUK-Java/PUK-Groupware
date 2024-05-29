package puk.groupware.model.project;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import puk.groupware.model.user.User_info;

@Entity
@Table(name="PROJECT_INFO")
@Getter
@Setter
public class Project_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_NO")
    private Long projectNo;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID",referencedColumnName = "USER_ID")
    private User_info userId;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="TARGET_COST")
    private int targetCost;


    @CreationTimestamp
    @Column(name="START_DATE")
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE")
    private LocalDate endDate;

    @Column(name="IMAGE")
    private String image;


    @Column(name="STATE")
    @ColumnDefault("1")
    private String state;

    @Column(name="CATEGORY")
    private String category;
}
