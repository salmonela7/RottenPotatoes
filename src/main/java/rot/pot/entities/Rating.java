package rot.pot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Rating.findAll", query = "select t from Rating as t"),
        @NamedQuery(name = "Rating.findByMovie", query = "select t from Rating as t where t.movie.movieId = :movieId"),
        @NamedQuery(name = "Rating.findByUser", query = "select t from Rating as t where t.user.userId = :userId")
})
@Table(name = "RATING")
@Getter @Setter
public class Rating {

    public Rating(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    @Min(1)
    @Max(10)
    private Integer rating;

    @Size(max = 500)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(ratingId, rating.ratingId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ratingId);
    }
}
