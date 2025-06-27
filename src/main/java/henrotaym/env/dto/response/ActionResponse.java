package henrotaym.env.dto.response;

import henrotaym.env.entities.Plant;

import java.sql.Date;

public class ActionResponse {

    private String name;
    private Date due_at;
    private Plant plant;

    public String getName() {
        return name;
    }

    public Date getDue_at() {
        return due_at;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDue_at(Date due_at) {
        this.due_at = due_at;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
