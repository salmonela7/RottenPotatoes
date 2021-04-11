package rot.pot.Usecases;

import lombok.Getter;
import rot.pot.entities.Movie;
import rot.pot.persistence.MoviesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Movies {

    @Inject
    private MoviesDAO moviesDAO;

    @Getter
    private List<Movie> allMovies;

    @PostConstruct
    public void init() {
        loadAllMovies();
    }

    private void loadAllMovies() {
        this.allMovies = moviesDAO.loadAll();
    }
}