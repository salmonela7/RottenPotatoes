package rot.pot.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select a from User as a"),
        @NamedQuery(name = "User.findByCredentials", query =
                "select a from User as a where a.username = :user and a.password = :pass")
})
@Table(name = "USER")
@Getter @Setter
@EqualsAndHashCode(of={"userId"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Date dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings = new ArrayList<>();

    public User() {
    }
}
