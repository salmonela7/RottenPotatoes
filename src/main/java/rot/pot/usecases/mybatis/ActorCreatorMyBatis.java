package rot.pot.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.mybatis.Actor;
import rot.pot.persistence.mybatis.ActorMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class ActorCreatorMyBatis {

    @Inject
    private ActorMapper actorMapper;

    @Getter @Setter
    private Actor actorToCreate = new Actor();

    @PostConstruct
    public void init(){
    }

    @Transactional
    public String createActor() {
        actorMapper.insert(actorToCreate);
        return "/mybatis/MainPage?faces-redirect=true";
    }
}