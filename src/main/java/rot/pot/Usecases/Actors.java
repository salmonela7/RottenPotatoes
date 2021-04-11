package rot.pot.Usecases;

import lombok.Getter;
import rot.pot.entities.Actor;
import rot.pot.persistence.ActorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Actors {

    @Inject
    private ActorsDAO actorsDAO;

    @Getter
    private List<Actor> allActors;

    @PostConstruct
    public void init(){
        loadAllActors();
    }

    private void loadAllActors(){
        this.allActors = actorsDAO.loadAll();
    }
}