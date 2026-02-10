package tn.aneti.profilage.dto;

import lombok.*;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class OptionReponseDTO {
    private UUID id;
    private String libelle;
    private String libelleAr;
    private String valeur;
    private Double score;
    private Integer ordre;
}
