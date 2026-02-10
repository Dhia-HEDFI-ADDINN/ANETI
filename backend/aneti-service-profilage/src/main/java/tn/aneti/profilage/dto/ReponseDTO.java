package tn.aneti.profilage.dto;

import lombok.*;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ReponseDTO {
    private UUID id;
    private UUID questionId;
    private UUID optionId;
    private String valeurTexte;
    private Double valeurNumerique;
    private Double score;
}
