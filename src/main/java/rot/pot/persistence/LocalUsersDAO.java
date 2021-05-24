package rot.pot.persistence;

import rot.pot.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class LocalUsersDAO implements IUsersDAO {

    private User adminUser;

    public LocalUsersDAO(){
        adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("admin");
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        adminUser.setUserId(9999);
    }

    @Override
    public void persist(User user) { }

    @Override
    public User findOne(Integer id){
        return adminUser;
    }

    @Override
    public User findByCredentials(String user, String pass) {
        return adminUser;
    }

    @Override
    public User update(User user){
        return adminUser;
    }
}
