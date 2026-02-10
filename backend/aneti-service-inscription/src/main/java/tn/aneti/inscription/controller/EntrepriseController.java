package tn.aneti.inscription.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.aneti.common.dto.ApiResponse;
import tn.aneti.inscription.dto.EntrepriseDTO;
import tn.aneti.inscription.entity.Entreprise;
import tn.aneti.inscription.service.EntrepriseService;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inscriptions/entreprises")
@RequiredArgsConstructor
@Tag(name = "Inscription Entreprise", description = "Gestion des inscriptions des entreprises")
public class EntrepriseController {

    private final EntrepriseService service;

    @PostMapping
    @Operation(summary = "Créer une inscription entreprise")
    public ResponseEntity<ApiResponse<EntrepriseDTO>> creer(@Valid @RequestBody EntrepriseDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Inscription créée avec succès", service.creer(dto)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une entreprise par ID")
    public ResponseEntity<ApiResponse<EntrepriseDTO>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une inscription entreprise")
    public ResponseEntity<ApiResponse<EntrepriseDTO>> mettreAJour(@PathVariable UUID id,
                                                                     @Valid @RequestBody EntrepriseDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(service.mettreAJour(id, dto)));
    }

    @PostMapping("/{id}/soumettre")
    @Operation(summary = "Soumettre une inscription pour validation")
    public ResponseEntity<ApiResponse<EntrepriseDTO>> soumettre(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.soumettre(id)));
    }

    @PostMapping("/{id}/valider")
    @Operation(summary = "Valider une inscription entreprise")
    public ResponseEntity<ApiResponse<EntrepriseDTO>> valider(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.valider(id)));
    }

    @PostMapping("/{id}/rejeter")
    @Operation(summary = "Rejeter une inscription entreprise")
    public ResponseEntity<ApiResponse<EntrepriseDTO>> rejeter(@PathVariable UUID id, @RequestParam String motif) {
        return ResponseEntity.ok(ApiResponse.success(service.rejeter(id, motif)));
    }

    @GetMapping
    @Operation(summary = "Rechercher des entreprises")
    public ResponseEntity<ApiResponse<Page<EntrepriseDTO>>> rechercher(
            @RequestParam(required = false) String raisonSociale,
            @RequestParam(required = false) String secteur,
            @RequestParam(required = false) String gouvernorat,
            @RequestParam(required = false) Entreprise.StatutInscription statut,
            Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.rechercher(raisonSociale, secteur, gouvernorat, statut, pageable)));
    }
}
