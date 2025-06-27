package henrotaym.env.controllers;

import henrotaym.env.dto.request.ActionRequest;
import henrotaym.env.dto.response.ActionResponse;
import henrotaym.env.services.ActionService;

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
import java.util.Optional;

@RestController
@RequestMapping("/actions")
public class ActionController {
    private ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    /*
    POST	/actions	Crée une nouvelle action et l'associe à une plante.
    GET	/actions/{id}	Récupère une action par son ID.
    PUT	/actions/{id}	Met à jour une action.
    DELETE	/actions/{id}	Supprime une action.
    */

    @PostMapping("")
    public ResponseEntity<ActionResponse> saveNewAction(@Valid @RequestBody ActionRequest entity) {
        return ResponseEntity.ok(actionService.save(entity));
    }

    @GetMapping("/{id}")
    public Optional<ActionResponse> getActionById(@RequestParam BigInteger param) {
        return actionService.findById(param);
    }

    @PutMapping("/{id}")
    public Optional<ActionResponse> updateAction(
            @PathVariable BigInteger id, @Valid @RequestBody ActionRequest entity) {
        // TODO: process PUT request
        return actionService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void deleteAction(@PathVariable BigInteger id) {
        actionService.deleteById(id);
    }
}
