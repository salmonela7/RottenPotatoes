package rot.pot.usecases;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.Actor;
import rot.pot.persistence.ActorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class ActorCreator {

    @Inject
    private ActorsDAO actorsDAO;

    @Getter @Setter
    private Actor actorToCreate = new Actor();

    @PostConstruct
    public void init(){
    }

    @Transactional
    public String createActor() {
        this.actorsDAO.persist(actorToCreate);
        return "MainPage?faces-redirect=true";
    }
}