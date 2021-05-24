package rot.pot.persistence;

import rot.pot.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class UsersDAO implements IUsersDAO {

    @Inject
    private EntityManager em;

    @Override
    public void persist(User user){
        this.em.persist(user);
    }

    @Override
    public User findOne(Integer id){
        return em.find(User.class, id);
    }

    @Override
    public User findByCredentials(String user, String pass) {
        TypedQuery<User> query = em.createNamedQuery("User.findByCredentials", User.class);
        query.setParameter("user", user);
        query.setParameter("pass", pass);
        return query.getSingleResult();
    }

    @Override
    public User update(User user){
        return em.merge(user);
    }
}
