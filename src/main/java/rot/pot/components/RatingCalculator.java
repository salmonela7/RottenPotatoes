package rot.pot.components;

import rot.pot.entities.Movie;
import rot.pot.entities.Rating;
import rot.pot.persistence.MoviesDAO;
import rot.pot.persistence.mybatis.MovieMapper;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class RatingCalculator implements Serializable {

    @Inject
    MoviesDAO moviesDAO;

    @Inject
    MovieMapper movieMapper;

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

    public float ClaculateMovieRatingMyBatis(Integer movieId){
        rot.pot.entities.mybatis.Movie movie = movieMapper.selectMovie(movieId).get(0);

        if(movie == null){
            throw new NotFoundException("Movie does not exist!");
        }

        List<rot.pot.entities.mybatis.Rating> ratings = movie.getRatings();

        float ratingSum = 0;
        for (rot.pot.entities.mybatis.Rating rating : ratings) {
            ratingSum += rating.getRating();
        }

        return ratingSum / ratings.size();
    }
}
