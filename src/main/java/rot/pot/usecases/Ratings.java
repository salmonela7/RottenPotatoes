package rot.pot.usecases;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.Movie;
import rot.pot.entities.Rating;
import rot.pot.entities.User;
import rot.pot.persistence.MoviesDAO;
import rot.pot.persistence.RatingsDAO;
import rot.pot.persistence.UsersDAO;
import rot.pot.utilities.SessionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
public class Ratings implements Serializable {

    @Inject
    private RatingsDAO ratingsDAO;

    @Inject
    private MoviesDAO moviesDAO;

    @Inject
    private UsersDAO usersDAO;

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
        this.ratingToCreate.setMovie(movie);
        this.ratingsDAO.persist(ratingToCreate);
        return "MovieDetails?movieId=" + currentMovieId + "&faces-redirect=true";
    }

    private Movie getMovie(Integer movieId) {
        return this.moviesDAO.findOne(movieId);
    }

    private User getUser(Integer userId) {
        return this.usersDAO.findOne(userId);
    }
}