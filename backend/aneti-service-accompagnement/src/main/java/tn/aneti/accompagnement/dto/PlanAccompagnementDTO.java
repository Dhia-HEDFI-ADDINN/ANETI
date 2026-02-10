package tn.aneti.accompagnement.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PlanAccompagnementDTO {
    private UUID id;
    private UUID chercheurEmploiId;
    private UUID conseillerId;
    private String segment;
    private String objectifPrincipal;
    private LocalDate dateDebut;
    private LocalDate dateFinPrevue;
    private LocalDate dateFinEffective;
    private String statut;
    private String bilan;
    private List<ActionAccompagnementDTO> actions;
}
