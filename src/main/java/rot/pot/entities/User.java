package rot.pot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select a from User as a"),
        @NamedQuery(name = "User.findByCredentials", query =
                "select a from User as a where a.username = :user and a.password = :pass")
})
@Table(name = "USER")
@Getter @Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
