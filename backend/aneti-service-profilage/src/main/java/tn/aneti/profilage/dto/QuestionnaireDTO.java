package tn.aneti.profilage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QuestionnaireDTO {
    private UUID id;
    @NotBlank(message = "Le titre est obligatoire")
    private String titre;
    private String titreAr;
    private String description;
    private Integer versionNum;
    private String statut;
    private List<QuestionDTO> questions;
}
