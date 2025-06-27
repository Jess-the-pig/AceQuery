package henrotaym.env.controllers;

import henrotaym.env.dto.request.PlantRequest;
import henrotaym.env.dto.response.PlantResponse;
import henrotaym.env.services.PlantService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plants")
public class PlantController {
    private PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    /*
         * POST	/plants	Crée une nouvelle plante.
    GET	/plants	Récupère la liste de toutes les plantes.
    GET	/plants/{id}	Récupère une plante par son ID.
    PUT	/plants/{id}	Met à jour une plante.
    DELETE	/plants/{id}	Supprime une plante.
         */

    @PostMapping("")
    public ResponseEntity<PlantResponse> addPlant(@RequestBody PlantRequest plant) {
        // TODO: process POST request
        return ResponseEntity.ok(plantService.add(plant));
    }

    @GetMapping("")
    public List<PlantResponse> getPlants() {
        return plantService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PlantResponse> getPlantById(@RequestParam BigInteger param) {
        return plantService.findById(param);
    }

    @PutMapping("/{id}")
    public Optional<PlantResponse> updatePlant(
            @PathVariable BigInteger id, @Valid @RequestBody PlantRequest entity) {
        // TODO: process PUT request
        return plantService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public Boolean deletePlant(@PathVariable BigInteger id) {
        Boolean deletedid = plantService.delete(id);
        return deletedid;
    }
}
