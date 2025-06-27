package henrotaym.env.mappers;

import henrotaym.env.dto.request.PlantRequest;
import henrotaym.env.dto.response.PlantResponse;
import henrotaym.env.entities.Plant;

import org.springframework.stereotype.Component;

@Component
public class PlantMapper {

    public Plant toEntity(PlantRequest plantrequest) {
        Plant plant = new Plant();
        plant.setSpecies(plantrequest.getSpecies());
        plant.setName(plantrequest.getName());
        plant.setBuyingDate(plantrequest.getBuyingDate());
        plant.setStatus(plantrequest.getStatus());

        return plant;
    }

    public PlantResponse fromEntity(Plant plant) {
        PlantResponse plantResponse = new PlantResponse();
        plantResponse.setSpecies(plant.getSpecies());
        plantResponse.setName(plant.getName());
        plantResponse.setBuyingDate(plant.getBuyingDate());
        plantResponse.setStatus(plant.getStatus());

        return plantResponse;
    }
}
