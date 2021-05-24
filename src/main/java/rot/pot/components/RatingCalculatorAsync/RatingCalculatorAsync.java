package rot.pot.components.RatingCalculatorAsync;

import rot.pot.entities.Movie;
import rot.pot.entities.Rating;
import rot.pot.persistence.AsyncInjection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class RatingCalculatorAsync implements IRatingCalculatorAsync {
    
    @AsyncInjection
    @Inject
    EntityManager em;

    public Float CalculateMovieRating(Integer movieId){
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
