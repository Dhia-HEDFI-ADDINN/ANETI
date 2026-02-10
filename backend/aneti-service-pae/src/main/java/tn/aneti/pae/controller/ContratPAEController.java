package tn.aneti.pae.controller;

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
import tn.aneti.pae.dto.ContratPAEDTO;
import tn.aneti.pae.service.ContratPAEService;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pae")
@RequiredArgsConstructor
@Tag(name = "PAE/CIVP", description = "Gestion des Programmes Actifs pour l'Emploi")
public class ContratPAEController {

    private final ContratPAEService service;

    @PostMapping("/contrats")
    @Operation(summary = "Créer un contrat PAE")
    public ResponseEntity<ApiResponse<ContratPAEDTO>> creer(@Valid @RequestBody ContratPAEDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.creer(dto)));
    }

    @GetMapping("/contrats/{id}")
    @Operation(summary = "Obtenir un contrat")
    public ResponseEntity<ApiResponse<ContratPAEDTO>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @PostMapping("/contrats/{id}/soumettre")
    @Operation(summary = "Soumettre un contrat")
    public ResponseEntity<ApiResponse<ContratPAEDTO>> soumettre(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.soumettre(id)));
    }

    @PostMapping("/contrats/{id}/valider")
    @Operation(summary = "Valider un contrat")
    public ResponseEntity<ApiResponse<ContratPAEDTO>> valider(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.valider(id)));
    }

    @PostMapping("/contrats/{id}/activer")
    @Operation(summary = "Activer un contrat")
    public ResponseEntity<ApiResponse<ContratPAEDTO>> activer(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.activer(id)));
    }

    @PostMapping("/contrats/{id}/resilier")
    @Operation(summary = "Résilier un contrat")
    public ResponseEntity<ApiResponse<ContratPAEDTO>> resilier(@PathVariable UUID id, @RequestParam String motif) {
        return ResponseEntity.ok(ApiResponse.success(service.resilier(id, motif)));
    }

    @PostMapping("/contrats/{id}/renouveler")
    @Operation(summary = "Renouveler un contrat")
    public ResponseEntity<ApiResponse<ContratPAEDTO>> renouveler(@PathVariable UUID id, @Valid @RequestBody ContratPAEDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(service.renouveler(id, dto)));
    }

    @GetMapping("/contrats/chercheur/{chercheurId}")
    public ResponseEntity<ApiResponse<Page<ContratPAEDTO>>> getByChercheur(@PathVariable UUID chercheurId, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.getByChercheur(chercheurId, pageable)));
    }

    @GetMapping("/contrats/entreprise/{entrepriseId}")
    public ResponseEntity<ApiResponse<Page<ContratPAEDTO>>> getByEntreprise(@PathVariable UUID entrepriseId, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.getByEntreprise(entrepriseId, pageable)));
    }

    @GetMapping("/contrats/type/{type}")
    public ResponseEntity<ApiResponse<Page<ContratPAEDTO>>> getByType(@PathVariable String type, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.getByType(type, pageable)));
    }
}
