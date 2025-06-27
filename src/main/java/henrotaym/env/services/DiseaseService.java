package henrotaym.env.services;

import henrotaym.env.dto.request.DiseaseRequest;
import henrotaym.env.dto.response.DiseaseResponse;
import henrotaym.env.entities.Disease;
import henrotaym.env.entities.Plant;
import henrotaym.env.enums.StatusName;
import henrotaym.env.exceptions.PlantIsDeadException;
import henrotaym.env.mappers.DiseaseMapper;
import henrotaym.env.mappers.OptionalMapper;
import henrotaym.env.repositories.DiseaseRepository;
import henrotaym.env.repositories.PlantRepository;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;
    private final PlantRepository plantRepository;
    private final DiseaseMapper diseaseMapper;
    private final OptionalMapper optionalMapper;

    public DiseaseService(
            DiseaseRepository diseaseRepository,
            DiseaseMapper diseaseMapper,
            OptionalMapper optionalMapper,
            PlantRepository plantRepository) {
        this.diseaseRepository = diseaseRepository;
        this.diseaseMapper = diseaseMapper;
        this.optionalMapper = optionalMapper;
        this.plantRepository = plantRepository;
    }

    // ✅ Créer une maladie
    public DiseaseResponse save(DiseaseRequest diseaseRequest) {
        Plant plant =
                plantRepository
                        .findById(diseaseRequest.getPlantId())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Plante introuvable avec l'ID : "
                                                        + diseaseRequest.getPlantId()));

        if (plant.getStatus() == StatusName.DEAD) {
            throw new PlantIsDeadException();
        }

        Disease diseaseToSave = diseaseMapper.toEntity(diseaseRequest);
        diseaseToSave.setPlant(plant);

        // Ajout de la maladie à la plante
        plant.getDiseases().add(diseaseToSave);
        plant.setStatus(StatusName.SICK);

        plantRepository.save(plant);
        Disease saved = diseaseRepository.save(diseaseToSave);

        return diseaseMapper.fromEntity(saved);
    }

    // ✅ Trouver une maladie
    public Optional<DiseaseResponse> findById(BigInteger id) {
        Optional<Disease> diseaseFound = diseaseRepository.findById(id);
        return optionalMapper.mapOptional(diseaseFound, diseaseMapper::fromEntity);
    }

    // ✅ Liste des maladies
    public List<DiseaseResponse> findAll() {
        List<Disease> listOfDiseases = diseaseRepository.findAll();
        List<DiseaseResponse> responseList = new ArrayList<>();

        for (Disease disease : listOfDiseases) {
            DiseaseResponse response = diseaseMapper.fromEntity(disease);
            responseList.add(response);
        }

        return responseList;
    }

    // ✅ Mise à jour
    public Optional<DiseaseResponse> update(BigInteger id, DiseaseRequest diseaseRequest) {
        return diseaseRepository
                .findById(id)
                .map(
                        existingDisease -> {
                            Plant plant = existingDisease.getPlant();

                            if (plant.getStatus() == StatusName.DEAD) {
                                throw new PlantIsDeadException();
                            }

                            existingDisease.setName(diseaseRequest.getName());
                            existingDisease.setType(diseaseRequest.getType());

                            Disease saved = diseaseRepository.save(existingDisease);
                            return diseaseMapper.fromEntity(saved);
                        });
    }

    // ✅ Suppression
    public void deleteById(BigInteger id) {
        diseaseRepository
                .findById(id)
                .ifPresent(
                        disease -> {
                            Plant plant = disease.getPlant();

                            if (plant.getStatus() == StatusName.DEAD) {
                                throw new PlantIsDeadException();
                            }

                            diseaseRepository.deleteById(id);

                            // Mise à jour du statut de la plante
                            List<Disease> remainingDiseases =
                                    diseaseRepository.findByPlantId(plant.getId());

                            if (remainingDiseases.isEmpty()
                                    && plant.getStatus() != StatusName.DEAD) {
                                plant.setStatus(StatusName.ALIVE);
                                plantRepository.save(plant);
                            }
                        });
    }
}
