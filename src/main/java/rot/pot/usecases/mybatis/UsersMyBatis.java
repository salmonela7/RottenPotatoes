package rot.pot.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.mybatis.User;
import rot.pot.persistence.mybatis.UserMapper;
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
public class UsersMyBatis {

    @Inject
    private UserMapper userMapper;

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
        this.userMapper.insert(userToCreate);
        return "/mybatis/index?faces-redirect=true";
    }

    public String validateCredentials(){
        User user = getUser();
        if(user != null){
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserid());
            return "/mybatis/MainPage?faces-redirect=true";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid login credentials",
                            "Please enter correct username and Password"));
            return "/mybatis/index?faces-redirect=true";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/mybatis/index?faces-redirect=true";
    }

    private User getUser(){
        User user;
        try {
            user = this.userMapper.selectByCredentials(this.username, this.password);
        }
        catch (NoResultException ex){
            return null;
        }
        return user;
    }
}