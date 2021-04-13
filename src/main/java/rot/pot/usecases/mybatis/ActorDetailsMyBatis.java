package rot.pot.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.mybatis.Actor;
import rot.pot.entities.mybatis.Movie;
import rot.pot.persistence.ActorsDAO;
import rot.pot.persistence.mybatis.ActorMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model
public class ActorDetailsMyBatis {
    @Inject
    private ActorMapper actorMapper;

    @Getter @Setter
    private Actor currentActor;

    @Getter @Setter
    private List<Movie> actorMovies;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (!requestParameters.isEmpty()) {
            String parameter = requestParameters.get("actorId");
            if(parameter != null){
                Integer actorId = Integer.parseInt(requestParameters.get("actorId"));
                loadActor(actorId);
            }
        }
    }

    private void loadActor(Integer actorId) {
        this.actorMovies = actorMapper.selectActorMovies(actorId);
        this.currentActor = actorMapper.selectByPrimaryKey(actorId);
    }
}
