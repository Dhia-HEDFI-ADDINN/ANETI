package tn.aneti.accompagnement.controller;

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
import tn.aneti.accompagnement.dto.*;
import tn.aneti.accompagnement.service.PlanAccompagnementService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accompagnement")
@RequiredArgsConstructor
@Tag(name = "Accompagnement CE", description = "Gestion de l'accompagnement des chercheurs d'emploi")
public class AccompagnementController {

    private final PlanAccompagnementService service;

    @PostMapping("/plans")
    @Operation(summary = "Créer un plan d'accompagnement")
    public ResponseEntity<ApiResponse<PlanAccompagnementDTO>> creer(@Valid @RequestBody PlanAccompagnementDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Plan créé", service.creer(dto)));
    }

    @GetMapping("/plans/{id}")
    @Operation(summary = "Obtenir un plan d'accompagnement")
    public ResponseEntity<ApiResponse<PlanAccompagnementDTO>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @GetMapping("/plans/chercheur/{chercheurId}")
    @Operation(summary = "Plans d'un chercheur d'emploi")
    public ResponseEntity<ApiResponse<List<PlanAccompagnementDTO>>> getByChercheur(@PathVariable UUID chercheurId) {
        return ResponseEntity.ok(ApiResponse.success(service.getByChercheur(chercheurId)));
    }

    @GetMapping("/plans/conseiller/{conseillerId}")
    @Operation(summary = "Plans d'un conseiller")
    public ResponseEntity<ApiResponse<Page<PlanAccompagnementDTO>>> getByConseiller(@PathVariable UUID conseillerId, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.getByConseiller(conseillerId, pageable)));
    }

    @PostMapping("/plans/{id}/valider")
    @Operation(summary = "Valider un plan")
    public ResponseEntity<ApiResponse<PlanAccompagnementDTO>> valider(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.valider(id)));
    }

    @PostMapping("/plans/{id}/demarrer")
    @Operation(summary = "Démarrer un plan")
    public ResponseEntity<ApiResponse<PlanAccompagnementDTO>> demarrer(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.demarrer(id)));
    }

    @PostMapping("/plans/{id}/terminer")
    @Operation(summary = "Terminer un plan")
    public ResponseEntity<ApiResponse<PlanAccompagnementDTO>> terminer(@PathVariable UUID id, @RequestParam(required = false) String bilan) {
        return ResponseEntity.ok(ApiResponse.success(service.terminer(id, bilan)));
    }
}
