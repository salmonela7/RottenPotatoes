package rot.pot.components;

import rot.pot.entities.Movie;
import rot.pot.entities.Rating;
import rot.pot.persistence.MoviesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class RatingCalculator implements IRatingCalculator{

    @Inject
    MoviesDAO moviesDAO;

    public float ClaculateMovieRating(Integer movieId){
        Movie movie = moviesDAO.findOne(movieId);

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
