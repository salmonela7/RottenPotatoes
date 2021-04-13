package rot.pot.usecases;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.User;
import rot.pot.persistence.UsersDAO;
import rot.pot.utilities.SessionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Model
public class Users {

    @Inject
    private UsersDAO usersDAO;

    @Getter @Setter
    private User userToCreate = new User();

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @PostConstruct
    public void init(){
    }

    @Transactional
    public String createUser(){
        this.usersDAO.persist(userToCreate);
        return "index?faces-redirect=true";
    }

    public String validateCredentials(){
        User user = getUser();
        if(user != null){
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserId());
            return "MainPage?faces-redirect=true";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid login credentials",
                            "Please enter correct username and Password"));
            return "index?faces-redirect=true";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index?faces-redirect=true";
    }

    private User getUser(){
        User user;
        try {
            user = this.usersDAO.findByCredentials(this.username, this.password);
        }
        catch (NoResultException ex){
            return null;
        }
        return user;
    }
}