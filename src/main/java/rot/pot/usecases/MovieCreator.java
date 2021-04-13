package rot.pot.usecases;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.Movie;
import rot.pot.persistence.MoviesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class MovieCreator {

    @Inject
    private MoviesDAO moviesDAO;

    @Getter @Setter
    private Movie movieToCreate = new Movie();

    @PostConstruct
    public void init() {
    }

    @Transactional
    public String createMovie() {
        this.moviesDAO.persist(movieToCreate);
        return "MainPage?faces-redirect=true";
    }
}