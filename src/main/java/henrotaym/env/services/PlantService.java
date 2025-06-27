package henrotaym.env.services;

import henrotaym.env.dto.request.PlantRequest;
import henrotaym.env.dto.response.PlantResponse;
import henrotaym.env.entities.Disease;
import henrotaym.env.entities.Plant;
import henrotaym.env.enums.StatusName;
import henrotaym.env.exceptions.PlantIsDeadException;
import henrotaym.env.mappers.OptionalMapper;
import henrotaym.env.mappers.PlantMapper;
import henrotaym.env.repositories.DiseaseRepository;
import henrotaym.env.repositories.PlantRepository;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository plantRepository;
    private final PlantMapper plantMapper;
    private final OptionalMapper optionalMapper;
    private final DiseaseRepository diseaseRepository;

    public PlantService(
            PlantRepository plantRepository,
            PlantMapper plantMapper,
            OptionalMapper optionalMapper,
            DiseaseRepository diseaseRepository) {
        this.plantRepository = plantRepository;
        this.plantMapper = plantMapper;
        this.optionalMapper = optionalMapper;
        this.diseaseRepository = diseaseRepository;
    }

    // Créer une plante
    public PlantResponse add(PlantRequest plantRequest) {
        Plant plantToSave = plantMapper.toEntity(plantRequest);

        if (plantRequest.getDiseaseIds() != null && !plantRequest.getDiseaseIds().isEmpty()) {
            List<Disease> diseases = diseaseRepository.findAllById(plantRequest.getDiseaseIds());
            plantToSave.setDiseases(diseases);
            plantToSave.setStatus(StatusName.SICK);
        } else {
            plantToSave.setDiseases(new ArrayList<>());
            plantToSave.setStatus(plantRequest.getStatus());
            updatePlantStatus(plantToSave);
        }

        Plant savedPlant = plantRepository.save(plantToSave);
        return plantMapper.fromEntity(savedPlant);
    }

    // Obtenir toutes les plantes
    public List<PlantResponse> findAll() {
        List<Plant> listOfPlants = plantRepository.findAll();
        List<PlantResponse> responseList = new ArrayList<>();

        for (Plant plant : listOfPlants) {
            PlantResponse response = plantMapper.fromEntity(plant);
            responseList.add(response);
        }

        return responseList;
    }

    // Obtenir une plante par ID
    public Optional<PlantResponse> findById(BigInteger id) {
        Optional<Plant> plantFound = plantRepository.findById(id);
        return optionalMapper.mapOptional(plantFound, plantMapper::fromEntity);
    }

    // Mettre à jour une plante
    public Optional<PlantResponse> update(BigInteger id, PlantRequest updatedRequest) {
        return plantRepository
                .findById(id)
                .map(
                        existingPlant -> {
                            if (existingPlant.getStatus() == StatusName.DEAD) {
                                throw new PlantIsDeadException();
                            }

                            existingPlant.setName(updatedRequest.getName());
                            existingPlant.setSpecies(updatedRequest.getSpecies());
                            existingPlant.setBuyingDate(updatedRequest.getBuyingDate());

                            if (updatedRequest.getDiseaseIds() != null
                                    && !updatedRequest.getDiseaseIds().isEmpty()) {
                                List<Disease> diseases =
                                        diseaseRepository.findAllById(
                                                updatedRequest.getDiseaseIds());
                                existingPlant.setDiseases(diseases);
                            } else {
                                existingPlant.setDiseases(new ArrayList<>());
                            }

                            updatePlantStatus(existingPlant);

                            Plant saved = plantRepository.save(existingPlant);
                            return plantMapper.fromEntity(saved);
                        });
    }

    // Supprimer une plante
    public boolean delete(BigInteger id) {
        if (plantRepository.existsById(id)) {
            plantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Appliquer la logique de statut automatique
    private void updatePlantStatus(Plant plant) {
        if (plant.getStatus() == StatusName.DEAD) return;

        if (plant.getDiseases() == null || plant.getDiseases().isEmpty()) {
            plant.setStatus(StatusName.ALIVE);
        } else {
            plant.setStatus(StatusName.SICK);
        }
    }
}
