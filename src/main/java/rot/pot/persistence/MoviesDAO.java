package rot.pot.persistence;

import rot.pot.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MoviesDAO {

    @Inject
    protected EntityManager em;

    public List<Movie> loadAll() {
        return em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public boolean persist(Movie movie){
        try{
            em.persist(movie);
        }
        catch (Exception exception){
            return false;
        }
        return true;
    }

    public boolean merge(Movie movie){
        try{
            em.merge(movie);
        }
        catch (Exception exception){
            return false;
        }
        return true;
    }

    public Movie findOne(Integer id) {
        return em.find(Movie.class, id);
    }
}
