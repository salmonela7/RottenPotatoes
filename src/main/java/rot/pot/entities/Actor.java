package rot.pot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Actor.findAll", query = "select t from Actor as t")
})
@Table(name = "ACTOR")
@Getter @Setter
public class Actor {

    public Actor(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

//    @OneToMany(mappedBy = "team")
//    private List<User> players = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(actorId, actor.actorId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(actorId);
    }
}
