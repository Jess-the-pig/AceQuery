package henrotaym.env.controllers;

import henrotaym.env.dto.request.DiseaseRequest;
import henrotaym.env.dto.response.DiseaseResponse;
import henrotaym.env.services.DiseaseService;

import jakarta.validation.Valid;

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
@RequestMapping("/disease")
public class DiseaseController {

    private DiseaseService diseaseService;

    /*
        POST	/diseases	Crée une nouvelle maladie.
    GET	/diseases	Récupère la liste de toutes les maladies.
    GET	/diseases/{id}	Récupère une maladie par son ID.
    PUT	/diseases/{id}	Met à jour une maladie.
    DELETE	/diseases/{id}	Supprime une maladie.
    */

    @PostMapping("")
    public void saveDisease(@Valid @RequestBody DiseaseRequest entity) {
        diseaseService.save(entity);
    }

    @GetMapping("")
    public List<DiseaseResponse> getAllDisease() {
        return diseaseService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DiseaseResponse> getDiseaseById(@RequestParam BigInteger param) {
        return diseaseService.findById(param);
    }

    @PutMapping("/{id}")
    public Optional<DiseaseResponse> putMethodName(
            @PathVariable BigInteger id, @Valid @RequestBody DiseaseRequest entity) {
        // TODO: process PUT request

        return diseaseService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void deleteDisease(@PathVariable BigInteger id) {
        diseaseService.deleteById(id);
    }
}
