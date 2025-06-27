package henrotaym.env.dto.response;

import henrotaym.env.enums.StatusName;

import java.sql.Date;

public class PlantResponse {

    private String species;
    private String name;
    private Date buyingDate;
    private StatusName status;

    // Getters
    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public Date getBuyingDate() {
        return buyingDate;
    }

    public StatusName getStatus() {
        return status;
    }

    // Setters
    public void setSpecies(String species) {
        this.species = species;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }
}
