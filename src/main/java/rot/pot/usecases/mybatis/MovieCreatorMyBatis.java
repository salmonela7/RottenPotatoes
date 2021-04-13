package rot.pot.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.mybatis.Movie;
import rot.pot.persistence.mybatis.MovieMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class MovieCreatorMyBatis {

    @Inject
    private MovieMapper movieMapper;

    @Getter @Setter
    private Movie movieToCreate = new Movie();

    @PostConstruct
    public void init() {
    }

    @Transactional
    public String createMovie() {
        this.movieMapper.insert(movieToCreate);
        return "/mybatis/MainPage?faces-redirect=true";
    }
}