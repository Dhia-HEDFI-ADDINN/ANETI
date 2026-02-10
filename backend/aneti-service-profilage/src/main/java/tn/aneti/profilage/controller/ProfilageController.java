package tn.aneti.profilage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.aneti.common.dto.ApiResponse;
import tn.aneti.profilage.dto.*;
import tn.aneti.profilage.service.SessionProfilageService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profilage")
@RequiredArgsConstructor
@Tag(name = "Profilage/Segmentation", description = "Gestion du profilage et de la segmentation des chercheurs d'emploi")
public class ProfilageController {

    private final SessionProfilageService sessionService;

    @PostMapping("/sessions")
    @Operation(summary = "Démarrer une session de profilage")
    public ResponseEntity<ApiResponse<SessionProfilageDTO>> demarrerSession(
            @RequestParam UUID chercheurEmploiId,
            @RequestParam UUID questionnaireId,
            @RequestParam(required = false) UUID conseillerId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Session démarrée", sessionService.demarrerSession(chercheurEmploiId, questionnaireId, conseillerId)));
    }

    @PostMapping("/sessions/{sessionId}/reponses")
    @Operation(summary = "Soumettre les réponses au questionnaire")
    public ResponseEntity<ApiResponse<SessionProfilageDTO>> soumettreReponses(
            @PathVariable UUID sessionId,
            @RequestBody List<ReponseDTO> reponses) {
        return ResponseEntity.ok(ApiResponse.success("Réponses soumises et segmentation effectuée",
                sessionService.soumettreReponses(sessionId, reponses)));
    }

    @PostMapping("/sessions/{sessionId}/valider")
    @Operation(summary = "Valider le segment proposé par le conseiller")
    public ResponseEntity<ApiResponse<SessionProfilageDTO>> validerSegment(
            @PathVariable UUID sessionId,
            @RequestParam String segment,
            @RequestParam(required = false) String commentaire) {
        return ResponseEntity.ok(ApiResponse.success("Segment validé", sessionService.validerSegment(sessionId, segment, commentaire)));
    }

    @GetMapping("/sessions/{id}")
    @Operation(summary = "Obtenir une session de profilage")
    public ResponseEntity<ApiResponse<SessionProfilageDTO>> getSession(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(sessionService.getById(id)));
    }

    @GetMapping("/sessions/chercheur/{chercheurId}")
    @Operation(summary = "Obtenir les sessions d'un chercheur d'emploi")
    public ResponseEntity<ApiResponse<List<SessionProfilageDTO>>> getByChercheur(@PathVariable UUID chercheurId) {
        return ResponseEntity.ok(ApiResponse.success(sessionService.getByChercheur(chercheurId)));
    }

    @GetMapping("/sessions/conseiller/{conseillerId}")
    @Operation(summary = "Obtenir les sessions d'un conseiller")
    public ResponseEntity<ApiResponse<Page<SessionProfilageDTO>>> getByConseiller(@PathVariable UUID conseillerId, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(sessionService.getByConseiller(conseillerId, pageable)));
    }
}
