package rot.pot.components.RatingCalculatorAsync;

import rot.pot.entities.Movie;
import rot.pot.entities.Rating;
import rot.pot.persistence.MoviesDAO;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
public class RatingCalculatorAsync implements IRatingCalculatorAsync {

    @Inject
    MoviesDAO moviesDAO;

    @Asynchronous
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Future<Float> CalculateMovieRating(Integer movieId){
        Movie movie = moviesDAO.findOne(movieId);

        try {
            Thread.sleep(1500);
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

        return new AsyncResult<>(ratingSum / ratings.size());
    }
}
