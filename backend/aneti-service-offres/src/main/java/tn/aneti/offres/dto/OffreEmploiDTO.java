package tn.aneti.offres.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class OffreEmploiDTO {
    private UUID id;
    private UUID entrepriseId;
    @NotBlank(message = "Le titre est obligatoire")
    private String titre;
    private String titreAr;
    @NotBlank(message = "La description est obligatoire")
    private String description;
    private String descriptionAr;
    private String typeContrat;
    private String secteurActivite;
    private String metier;
    private String codeRtmc;
    private String niveauEtudeRequis;
    private Integer experienceRequise;
    private String competencesRequises;
    private String languesRequises;
    private String gouvernorat;
    private String delegation;
    private String lieuTravail;
    private Double salaireMin;
    private Double salaireMax;
    @Min(1)
    private Integer nombrePostes;
    private LocalDate datePublication;
    private LocalDate dateExpiration;
    private String statut;
    private UUID betiId;
    private UUID conseillerId;
    private Integer nombreCandidatures;
}
