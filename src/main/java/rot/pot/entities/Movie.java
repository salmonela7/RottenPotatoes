package rot.pot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Movie.findAll", query = "select t from Movie as t")
})
@Table(name = "MOVIE")
@Getter @Setter
@EqualsAndHashCode(of={"movieId"})
public class Movie {

    public Movie(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    private String name;

    private Date releaseDate;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "MOVIE_ACTOR",
            joinColumns = {@JoinColumn(name = "MOVIE_MOVIEID")},
            inverseJoinColumns = {@JoinColumn(name = "MOVIE_ACTORID")})
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public void addActor(Actor a){
        this.actors.add(a);
    }
}