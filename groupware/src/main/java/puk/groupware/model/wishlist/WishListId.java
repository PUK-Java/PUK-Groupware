package puk.groupware.model.wishlist;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import puk.groupware.model.project.Project_info;
import puk.groupware.model.user.User_Info;

@NoArgsConstructor
@Embeddable
@Data
public class WishListId implements Serializable{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PROJECT_NO", referencedColumnName = "PROJECT_NO")
    private Project_info projectNo;

    @ManyToOne
    @JoinColumn(name="USER_ID", referencedColumnName = "USER_ID")
    private User_Info userId;


}
