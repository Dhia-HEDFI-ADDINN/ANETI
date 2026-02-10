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
import tn.aneti.inscription.dto.ChercheurEmploiDTO;
import tn.aneti.inscription.entity.ChercheurEmploi;
import tn.aneti.inscription.service.ChercheurEmploiService;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inscriptions/chercheurs-emploi")
@RequiredArgsConstructor
@Tag(name = "Inscription Chercheur d'Emploi", description = "Gestion des inscriptions des chercheurs d'emploi")
public class ChercheurEmploiController {

    private final ChercheurEmploiService service;

    @PostMapping
    @Operation(summary = "Créer une inscription chercheur d'emploi")
    public ResponseEntity<ApiResponse<ChercheurEmploiDTO>> creer(@Valid @RequestBody ChercheurEmploiDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Inscription créée avec succès", service.creer(dto)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un chercheur d'emploi par ID")
    public ResponseEntity<ApiResponse<ChercheurEmploiDTO>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @GetMapping("/cin/{cin}")
    @Operation(summary = "Obtenir un chercheur d'emploi par CIN")
    public ResponseEntity<ApiResponse<ChercheurEmploiDTO>> getByCin(@PathVariable String cin) {
        return ResponseEntity.ok(ApiResponse.success(service.getByCin(cin)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une inscription")
    public ResponseEntity<ApiResponse<ChercheurEmploiDTO>> mettreAJour(@PathVariable UUID id,
                                                                         @Valid @RequestBody ChercheurEmploiDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Inscription mise à jour", service.mettreAJour(id, dto)));
    }

    @PostMapping("/{id}/soumettre")
    @Operation(summary = "Soumettre une inscription pour validation")
    public ResponseEntity<ApiResponse<ChercheurEmploiDTO>> soumettre(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success("Inscription soumise pour validation", service.soumettre(id)));
    }

    @PostMapping("/{id}/valider")
    @Operation(summary = "Valider une inscription")
    public ResponseEntity<ApiResponse<ChercheurEmploiDTO>> valider(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success("Inscription validée", service.valider(id)));
    }

    @PostMapping("/{id}/rejeter")
    @Operation(summary = "Rejeter une inscription")
    public ResponseEntity<ApiResponse<ChercheurEmploiDTO>> rejeter(@PathVariable UUID id,
                                                                      @RequestParam String motif) {
        return ResponseEntity.ok(ApiResponse.success("Inscription rejetée", service.rejeter(id, motif)));
    }

    @PostMapping("/{id}/affecter-conseiller")
    @Operation(summary = "Affecter un conseiller à un chercheur d'emploi")
    public ResponseEntity<ApiResponse<ChercheurEmploiDTO>> affecterConseiller(@PathVariable UUID id,
                                                                                @RequestParam UUID conseillerId) {
        return ResponseEntity.ok(ApiResponse.success(service.affecterConseiller(id, conseillerId)));
    }

    @GetMapping
    @Operation(summary = "Rechercher des chercheurs d'emploi")
    public ResponseEntity<ApiResponse<Page<ChercheurEmploiDTO>>> rechercher(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String gouvernorat,
            @RequestParam(required = false) ChercheurEmploi.StatutInscription statut,
            Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.rechercher(nom, prenom, gouvernorat, statut, pageable)));
    }

    @GetMapping("/conseiller/{conseillerId}")
    @Operation(summary = "Obtenir les chercheurs d'emploi d'un conseiller")
    public ResponseEntity<ApiResponse<Page<ChercheurEmploiDTO>>> getByConseiller(@PathVariable UUID conseillerId,
                                                                                    Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.getByConseiller(conseillerId, pageable)));
    }

    @GetMapping("/beti/{betiId}")
    @Operation(summary = "Obtenir les chercheurs d'emploi d'un BETI")
    public ResponseEntity<ApiResponse<Page<ChercheurEmploiDTO>>> getByBeti(@PathVariable UUID betiId,
                                                                              Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.getByBeti(betiId, pageable)));
    }
}
