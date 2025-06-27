package henrotaym.env.dto.request;

import henrotaym.env.annotations.ExistsInDatabase;
import henrotaym.env.enums.StatusName;
import henrotaym.env.repositories.DiseaseRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

public class PlantRequest {

    @NotBlank(message = "Species is required")
    private String species;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Buying date is required")
    @PastOrPresent(message = "Buying date cannot be in the future")
    private Date buyingDate;

    @NotNull(message = "Status is required")
    private StatusName status;

    @ExistsInDatabase(
            repository = DiseaseRepository.class,
            message = "One or more disease IDs are invalid")
    private List<BigInteger> diseaseIds;

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

    public List<BigInteger> getDiseaseIds() {
        return diseaseIds;
    }

    public void setDiseaseIds(List<BigInteger> diseaseIds) {
        this.diseaseIds = diseaseIds;
    }
}
