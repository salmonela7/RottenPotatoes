package rot.pot.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

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
    @JoinTable(name = "MOVIE_ACTOR",
            joinColumns = {@JoinColumn(name = "MOVIE_MOVIEID")},
            inverseJoinColumns = {@JoinColumn(name = "MOVIE_ACTORID")})
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings = new ArrayList<>();

    public void addActor(Actor a){
        this.actors.add(a);
    }
}