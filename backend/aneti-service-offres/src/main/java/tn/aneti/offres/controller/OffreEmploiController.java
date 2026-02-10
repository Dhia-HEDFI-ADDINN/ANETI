package tn.aneti.offres.controller;

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
import tn.aneti.offres.dto.*;
import tn.aneti.offres.service.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/offres")
@RequiredArgsConstructor
@Tag(name = "Offres d'Emploi", description = "Gestion des offres d'emploi et des candidatures")
public class OffreEmploiController {

    private final OffreEmploiService offreService;
    private final CandidatureService candidatureService;

    @PostMapping
    @Operation(summary = "Créer une offre d'emploi")
    public ResponseEntity<ApiResponse<OffreEmploiDTO>> creer(@Valid @RequestBody OffreEmploiDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(offreService.creer(dto)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une offre")
    public ResponseEntity<ApiResponse<OffreEmploiDTO>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(offreService.getById(id)));
    }

    @PostMapping("/{id}/publier")
    @Operation(summary = "Publier une offre")
    public ResponseEntity<ApiResponse<OffreEmploiDTO>> publier(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(offreService.publier(id)));
    }

    @PostMapping("/{id}/suspendre")
    @Operation(summary = "Suspendre une offre")
    public ResponseEntity<ApiResponse<OffreEmploiDTO>> suspendre(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(offreService.suspendre(id)));
    }

    @PostMapping("/{id}/pourvoir")
    @Operation(summary = "Marquer une offre comme pourvue")
    public ResponseEntity<ApiResponse<OffreEmploiDTO>> pourvoir(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(offreService.pourvoir(id)));
    }

    @GetMapping
    @Operation(summary = "Rechercher des offres publiées")
    public ResponseEntity<ApiResponse<Page<OffreEmploiDTO>>> rechercher(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String secteur,
            @RequestParam(required = false) String gouvernorat,
            @RequestParam(required = false) String typeContrat,
            Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(offreService.rechercherPubliques(titre, secteur, gouvernorat, typeContrat, pageable)));
    }

    @GetMapping("/entreprise/{entrepriseId}")
    @Operation(summary = "Offres d'une entreprise")
    public ResponseEntity<ApiResponse<Page<OffreEmploiDTO>>> getByEntreprise(@PathVariable UUID entrepriseId, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(offreService.getByEntreprise(entrepriseId, pageable)));
    }

    @PostMapping("/{offreId}/candidatures")
    @Operation(summary = "Postuler à une offre")
    public ResponseEntity<ApiResponse<CandidatureDTO>> postuler(@PathVariable UUID offreId, @Valid @RequestBody CandidatureDTO dto) {
        dto.setOffreId(offreId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(candidatureService.postuler(dto)));
    }

    @GetMapping("/{offreId}/candidatures")
    @Operation(summary = "Candidatures d'une offre")
    public ResponseEntity<ApiResponse<Page<CandidatureDTO>>> getCandidatures(@PathVariable UUID offreId, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(candidatureService.getByOffre(offreId, pageable)));
    }
}
