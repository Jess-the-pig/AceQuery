package henrotaym.env.mappers;

import henrotaym.env.dto.request.ActionRequest;
import henrotaym.env.dto.response.ActionResponse;
import henrotaym.env.entities.Action;

import org.springframework.stereotype.Component;

@Component
public class ActionMapper {

    public Action toEntity(ActionRequest actionrequest) {
        Action action = new Action();
        action.setName(actionrequest.getName());
        action.setDue_at(actionrequest.getDue_at());
        action.setPlant(actionrequest.getPlant());

        return action;
    }

    public ActionResponse fromEntity(Action action) {
        ActionResponse actionResponse = new ActionResponse();
        actionResponse.setName(action.getName());
        actionResponse.setDue_at(action.getDue_at());
        actionResponse.setPlant(action.getPlant());
        return actionResponse;
    }
}
