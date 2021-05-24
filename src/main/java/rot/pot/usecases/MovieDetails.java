package rot.pot.usecases;

import lombok.Getter;
import lombok.Setter;
import rot.pot.components.RatingCalculator.IRatingCalculator;
import rot.pot.components.RatingCalculatorAsync.IRatingCalculatorAsync;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Model
@ViewScoped
public class MovieDetails implements Serializable {

    @Inject
    private MoviesDAO moviesDAO;

    @Inject
    private ActorsDAO actorsDAO;

    @Inject
    private IRatingCalculatorAsync ratingCalculator;

    private CompletableFuture<Float> ratingTask = null;

    @Inject
    private IRatingCalculator ratingCalculatorSync;

    @Getter
    @Setter
    private float ratingSync;

    @Getter
    @Setter
    private Movie currentMovie;

    @Getter
    @Setter
    private String currentMovieRating;

    @Getter
    @Setter
    private boolean isRatingLoaded;

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
                calculateRatingSync();
                getRating();
            }
        }
    }

    @Transactional
    public String addActor(){
        Actor actorToAdd = actorsDAO.findOne(selectedActor);
        this.currentMovie.addActor(actorToAdd);
        moviesDAO.merge(currentMovie);
        return "MovieDetails?movieId=" + this.currentMovie.getMovieId() + "&faces-redirect=true";
    }

    private void loadMovie(Integer movieId) {
        this.currentMovie = moviesDAO.findOne(movieId);
    }

    public void calculateRatingSync() {
        ratingSync = ratingCalculatorSync.CalculateMovieRating(currentMovie.getMovieId());
    }

    public void getRating() {
        ratingTask = CompletableFuture.supplyAsync(() -> ratingCalculator.CalculateMovieRating(currentMovie.getMovieId()));
    }

    public boolean isRatingBeingRetreived() {
        return ratingTask != null && !ratingTask.isDone();
    }

    public void checkRatingStatus() throws ExecutionException, InterruptedException {
        if (ratingTask == null) {
            return;
        } else if (isRatingBeingRetreived()) {
            currentMovieRating = "Getting the rating...";
            return;
        }
        isRatingLoaded = true;
        currentMovieRating = "Rating: " + ratingTask.get();
    }
}