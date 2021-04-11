package rot.pot.persistence;

import rot.pot.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

    public User findByCredentials(String user, String pass) {
        TypedQuery<User> query = em.createNamedQuery("User.findByCredentials", User.class);
        query.setParameter("user", user);
        query.setParameter("pass", pass);
        return query.getSingleResult();
    }

    public User update(User user){
        return em.merge(user);
    }
}
