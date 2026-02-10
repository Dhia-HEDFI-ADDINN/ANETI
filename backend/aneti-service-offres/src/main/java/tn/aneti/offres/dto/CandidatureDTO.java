package tn.aneti.offres.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CandidatureDTO {
    private UUID id;
    private UUID offreId;
    private UUID chercheurEmploiId;
    private String lettreMotivation;
    private UUID cvDocumentId;
    private Double scoreMatching;
    private LocalDateTime dateCandidature;
    private String statut;
    private String commentaireConseiller;
    private String commentaireEntreprise;
    private LocalDateTime dateEntretien;
    private String resultatEntretien;
}
