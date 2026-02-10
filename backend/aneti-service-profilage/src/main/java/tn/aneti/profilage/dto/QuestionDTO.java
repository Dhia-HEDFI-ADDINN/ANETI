package tn.aneti.profilage.dto;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QuestionDTO {
    private UUID id;
    private String libelle;
    private String libelleAr;
    private String typeQuestion;
    private boolean obligatoire;
    private Integer ordre;
    private Double ponderation;
    private UUID questionParentId;
    private String conditionAffichage;
    private List<OptionReponseDTO> options;
}
