package rot.pot.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.mybatis.Actor;
import rot.pot.entities.mybatis.Movie;
import rot.pot.persistence.mybatis.MovieMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class MoviesMyBatis {

    @Inject
    private MovieMapper movieMapper;

    @Getter
    private List<Movie> allMovies;

    @PostConstruct
    public void init() {
        loadAllMovies();
    }

    private void loadAllMovies() {
        this.allMovies = movieMapper.selectAll();
    }
}