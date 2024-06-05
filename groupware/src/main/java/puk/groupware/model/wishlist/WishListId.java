package puk.groupware.model.wishlist;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;

@NoArgsConstructor
@Embeddable
@Getter
@Setter
public class WishListId implements Serializable{

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="PROJECT_NO", referencedColumnName = "PROJECT_NO")
    private Project_info projectInfo;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="USER_ID", referencedColumnName = "USER_ID")
    private User_Info userInfo;


}
