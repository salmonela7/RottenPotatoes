package rot.pot.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import rot.pot.components.IRatingCalculatorMyBatis;
import rot.pot.entities.mybatis.Actor;
import rot.pot.entities.mybatis.Movie;
import rot.pot.persistence.mybatis.ActorMapper;
import rot.pot.persistence.mybatis.MovieMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
@ViewScoped
public class MovieDetailsMyBatis implements Serializable {

    @Inject
    private MovieMapper movieMapper;

    @Inject
    private ActorMapper actorMapper;

    @Inject
    private IRatingCalculatorMyBatis ratingCalculator;

    @Getter
    @Setter
    private Movie currentMovie;

    @Getter
    @Setter
    private float currentMovieRating;

    @Getter
    @Setter
    private Integer selectedActor;

    @Getter @Setter
    private List<Actor> movieActors;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (!requestParameters.isEmpty()) {
            String parameter = requestParameters.get("movieId");
            if(parameter != null){
                Integer movieId = Integer.parseInt(requestParameters.get("movieId"));
                loadMovie(movieId);
                calculateRating(movieId);
            }
        }
    }

    @Transactional
    public String addActor(){
        actorMapper.assignActorToMovie(selectedActor, currentMovie.getMovieid());
        return "/mybatis/MovieDetails?movieId=" + this.currentMovie.getMovieid() + "&faces-redirect=true";
    }

    private void loadMovie(Integer movieId) {
        this.movieActors = movieMapper.selectMovieActors(movieId);
        this.currentMovie = movieMapper.selectMovie(movieId).get(0);
    }

    private void calculateRating(Integer movieId) {
        this.currentMovieRating = ratingCalculator.ClaculateMovieRating(movieId);
    }
}