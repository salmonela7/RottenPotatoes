package rot.pot.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Actor.findAll", query = "select t from Actor as t")
})
@Table(name = "ACTOR")
@Getter @Setter
@EqualsAndHashCode(of={"actorId"})
public class Actor {

    public Actor(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies = new ArrayList<>();
}
