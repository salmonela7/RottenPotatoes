package rot.pot.persistence;

import rot.pot.entities.Actor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ActorsDAO {

    @Inject
    private EntityManager em;

    public List<Actor> loadAll() {
        return em.createNamedQuery("Actor.findAll", Actor.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Actor actor){
        this.em.persist(actor);
    }

    public Actor findOne(Integer id) {
        return em.find(Actor.class, id);
    }
}
