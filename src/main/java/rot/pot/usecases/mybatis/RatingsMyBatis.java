package rot.pot.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.mybatis.Movie;
import rot.pot.entities.mybatis.Rating;
import rot.pot.entities.mybatis.User;
import rot.pot.persistence.mybatis.MovieMapper;
import rot.pot.persistence.mybatis.RatingMapper;
import rot.pot.persistence.mybatis.UserMapper;
import rot.pot.utilities.SessionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
@ViewScoped
public class RatingsMyBatis implements Serializable {

    @Inject
    private RatingMapper ratingMapper;

    @Inject
    private MovieMapper movieMapper;

    @Inject
    private UserMapper userMapper;

    @Getter @Setter
    private Rating ratingToCreate = new Rating();

    @Getter @Setter
    private Integer currentMovieId;

    @PostConstruct
    private void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (!requestParameters.isEmpty()) {
            String parameter = requestParameters.get("movieId");
            if(parameter != null){
                this.currentMovieId = Integer.parseInt(requestParameters.get("movieId"));
            }
        }
    }

    @Transactional
    public String createRating(){
        User user = getUser(SessionUtils.getUserId());
        Movie movie = getMovie(currentMovieId);
        this.ratingToCreate.setUser(user);
        this.ratingToCreate.setMovieid(movie.getMovieid());
        this.ratingMapper.insert(ratingToCreate);
        return "/mybatis/MovieDetails?movieId=" + currentMovieId + "&faces-redirect=true";
    }

    private Movie getMovie(Integer movieId) {
        return this.movieMapper.selectMovie(movieId).get(0);
    }

    private User getUser(Integer userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }
}