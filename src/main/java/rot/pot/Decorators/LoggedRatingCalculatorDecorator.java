package rot.pot.Decorators;

import rot.pot.components.RatingCalculator.IRatingCalculator;
import rot.pot.entities.Movie;
import rot.pot.persistence.MoviesDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@Decorator
public abstract class LoggedRatingCalculatorDecorator implements IRatingCalculator {

    @Inject
    @Delegate
    @Any
    IRatingCalculator  ratingCalculator;

    @Inject
    MoviesDAO moviesDAO;

    public float CalculateMovieRating(Integer movieId){
        Movie movie = moviesDAO.findOne(movieId);

        if (movie == null){
            return 0;
        }

        float rating = 0;

        System.out.println("Starting rating calculation for movie: " + movie.getName() + "; movieId: " + movieId);

        try{
            rating = ratingCalculator.CalculateMovieRating(movieId);
        }
        catch (NotFoundException ex){
            System.out.println("Calculator threw and exception: " + ex.getMessage());
            rating = 0;
        }

        System.out.println("Calculation finished... Result: " + rating);

        return rating;
    }
}
