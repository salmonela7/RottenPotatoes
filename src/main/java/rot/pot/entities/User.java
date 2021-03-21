package rot.pot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select a from User as a")
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

//    @Size(max = 50)
//    @Column(name = "NAME")
//    private String name;
//
//    @Column(name = "JERSEY_NUMBER")
//    private Integer jerseyNumber;

//    @ManyToOne
//    @JoinColumn(name="TEAM_ID")
//    private Actor actor;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

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
