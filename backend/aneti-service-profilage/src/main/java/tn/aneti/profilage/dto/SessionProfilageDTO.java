package tn.aneti.profilage.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SessionProfilageDTO {
    private UUID id;
    private UUID chercheurEmploiId;
    private UUID conseillerId;
    private UUID questionnaireId;
    private String statut;
    private Double scoreGlobal;
    private String segmentPropose;
    private String segmentValide;
    private String commentaireConseiller;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private LocalDateTime dateValidation;
    private List<ReponseDTO> reponses;
}
