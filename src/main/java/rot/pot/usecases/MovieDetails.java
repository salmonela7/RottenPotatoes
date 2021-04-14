package rot.pot.usecases;

import lombok.Getter;
import lombok.Setter;
import rot.pot.components.IRatingCalculator;
import rot.pot.entities.Actor;
import rot.pot.entities.Movie;
import rot.pot.persistence.ActorsDAO;
import rot.pot.persistence.MoviesDAO;

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
public class MovieDetails implements Serializable {

    @Inject
    private MoviesDAO moviesDAO;

    @Inject
    private ActorsDAO actorsDAO;

    @Inject
    private IRatingCalculator ratingCalculator;

    @Getter
    @Setter
    private Movie currentMovie;

    @Getter
    @Setter
    private float currentMovieRating;

    @Getter
    @Setter
    private Integer selectedActor;

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
        Actor actorToAdd = actorsDAO.findOne(selectedActor);
        this.currentMovie.addActor(actorToAdd);
        moviesDAO.persist(currentMovie);
        return "MovieDetails?movieId=" + this.currentMovie.getMovieId() + "&faces-redirect=true";
    }

    private void loadMovie(Integer movieId) {
        this.currentMovie = moviesDAO.findOne(movieId);
    }

    private void calculateRating(Integer movieId) {
        this.currentMovieRating = ratingCalculator.ClaculateMovieRating(movieId);
    }
}