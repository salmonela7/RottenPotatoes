package rot.pot.persistence;

import rot.pot.entities.User;

public interface IUsersDAO {
    void persist(User user);

    User findOne(Integer id);

    User findByCredentials(String user, String pass);

    User update(User user);
}
