package rot.pot.persistence;

import rot.pot.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MoviesDAO {

    @Inject
    private EntityManager em;

    public List<Movie> loadAll() {
        return em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Movie movie){
        this.em.persist(movie);
    }

    public Movie findOne(Integer id) {
        return em.find(Movie.class, id);
    }
}
