package rot.pot.components;

import rot.pot.entities.mybatis.Movie;
import rot.pot.entities.mybatis.Rating;
import rot.pot.persistence.mybatis.MovieMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class RatingCalculatorMyBatis implements IRatingCalculatorMyBatis{

    @Inject
    MovieMapper movieMapper;

    public float ClaculateMovieRating(Integer movieId){
        Movie movie = movieMapper.selectMovie(movieId).get(0);

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
