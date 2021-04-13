package rot.pot.usecases.mybatis;

import lombok.Getter;
import rot.pot.entities.mybatis.Actor;
import rot.pot.persistence.mybatis.ActorMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class ActorsMyBatis {

    @Inject
    private ActorMapper actorMapper;

    @Getter
    private List<Actor> allActors;

    @PostConstruct
    public void init(){
        loadAllActors();
    }

    private void loadAllActors(){
        this.allActors = actorMapper.selectAll();
    }
}