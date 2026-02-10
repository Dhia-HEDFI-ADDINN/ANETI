package tn.aneti.rdv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.aneti.common.dto.ApiResponse;
import tn.aneti.rdv.dto.RendezVousDTO;
import tn.aneti.rdv.service.RendezVousService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController @RequestMapping("/api/v1/rendez-vous") @RequiredArgsConstructor
@Tag(name = "Rendez-vous", description = "Gestion des rendez-vous")
public class RendezVousController {
    private final RendezVousService service;

    @PostMapping
    @Operation(summary = "Créer un rendez-vous")
    public ResponseEntity<ApiResponse<RendezVousDTO>> creer(@Valid @RequestBody RendezVousDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.creer(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RendezVousDTO>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @PostMapping("/{id}/statut")
    public ResponseEntity<ApiResponse<RendezVousDTO>> changerStatut(@PathVariable UUID id, @RequestParam String statut) {
        return ResponseEntity.ok(ApiResponse.success(service.changerStatut(id, statut)));
    }

    @PostMapping("/{id}/annuler")
    public ResponseEntity<ApiResponse<RendezVousDTO>> annuler(@PathVariable UUID id, @RequestParam String motif) {
        return ResponseEntity.ok(ApiResponse.success(service.annuler(id, motif)));
    }

    @GetMapping("/conseiller/{conseillerId}")
    public ResponseEntity<ApiResponse<Page<RendezVousDTO>>> getByConseiller(@PathVariable UUID conseillerId, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.getByConseiller(conseillerId, pageable)));
    }

    @GetMapping("/chercheur/{chercheurId}")
    public ResponseEntity<ApiResponse<Page<RendezVousDTO>>> getByChercheur(@PathVariable UUID chercheurId, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(service.getByChercheur(chercheurId, pageable)));
    }

    @GetMapping("/agenda/{conseillerId}")
    @Operation(summary = "Agenda d'un conseiller")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getAgenda(
            @PathVariable UUID conseillerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(ApiResponse.success(service.getAgenda(conseillerId, debut, fin)));
    }
}
