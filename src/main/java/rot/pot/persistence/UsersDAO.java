package rot.pot.persistence;

import rot.pot.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.UUID;

@ApplicationScoped
public class UsersDAO {

    @Inject
    private EntityManager em;

    public void persist(User user){
        this.em.persist(user);
    }

    public User findOne(Integer id){
        return em.find(User.class, id);
    }

    public User update(User user){
        return em.merge(user);
    }
}
