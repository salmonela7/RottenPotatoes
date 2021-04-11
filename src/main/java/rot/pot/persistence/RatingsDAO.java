package rot.pot.persistence;

import rot.pot.entities.Rating;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class RatingsDAO {

    @Inject
    private EntityManager em;

    public List<Rating> loadAll() {
        return em.createNamedQuery("Rating.findAll", Rating.class).getResultList();
    }

    public List<Rating> findByMovieId(Integer movieId) {
        TypedQuery<Rating> query = em.createNamedQuery("Rating.findByMovie", Rating.class);
        query.setParameter("movieId", movieId);
        return query.getResultList();
    }

    public List<Rating> findByUserId(Integer userId) {
        TypedQuery<Rating> query = em.createNamedQuery("Rating.findByUser", Rating.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public Rating findByRatingId(Integer ratingId) { return em.find(Rating.class, ratingId); }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Rating rating){
        this.em.persist(rating);
    }
}
