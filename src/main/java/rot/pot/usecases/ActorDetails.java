package rot.pot.usecases;

import lombok.Getter;
import lombok.Setter;
import rot.pot.entities.Actor;
import rot.pot.persistence.ActorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class ActorDetails {
    @Inject
    private ActorsDAO actorsDAO;

    @Getter @Setter
    private Actor currentActor;

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
        this.currentActor = actorsDAO.findOne(actorId);
    }
}
