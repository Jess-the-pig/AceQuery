package henrotaym.env.dto.request;

import henrotaym.env.annotations.ExistsInDatabase;
import henrotaym.env.enums.DiseaseName;
import henrotaym.env.repositories.PlantRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public class DiseaseRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "type is required")
    private DiseaseName type;

    @NotNull(message = "Plant ID is required")
    @ExistsInDatabase(repository = PlantRepository.class, message = "Plant not found")
    private BigInteger plantId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiseaseName getType() {
        return type;
    }

    public void setType(DiseaseName type) {
        this.type = type;
    }

    public BigInteger getPlantId() {
        return plantId;
    }

    public void setPlantId(BigInteger plantId) {
        this.plantId = plantId;
    }
}
