package rot.pot.components.RatingCalculatorAsync;

import rot.pot.entities.Movie;
import rot.pot.entities.Rating;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.SynchronizationType;
import javax.ws.rs.NotFoundException;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class RatingCalculatorAsync implements Serializable, IRatingCalculatorAsync {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public Float CalculateMovieRating(Integer movieId){
        EntityManager em = emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
        Movie movie = em.find(Movie.class, movieId);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(movie == null){
            throw new NotFoundException("Movie does not exist!");
        }

        List<Rating> ratings = movie.getRatings();

        float ratingSum = 0;
        for (Rating rating : ratings) {
            ratingSum += rating.getRating();
        }

        return ratingSum / ratings.size();
    }
}
