package tn.aneti.accompagnement.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ActionAccompagnementDTO {
    private UUID id;
    private String typeAction;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFinPrevue;
    private LocalDate dateRealisation;
    private String statut;
    private String resultat;
    private String commentaire;
    private Integer priorite;
}
