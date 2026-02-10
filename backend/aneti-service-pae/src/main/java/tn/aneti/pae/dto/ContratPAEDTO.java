package tn.aneti.pae.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ContratPAEDTO {
    private UUID id;
    @NotNull private UUID chercheurEmploiId;
    @NotNull private UUID entrepriseId;
    private UUID conseillerId;
    private UUID betiId;
    @NotNull private String typeContrat;
    private String numeroContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer dureeMois;
    private Double montantIndemnite;
    private String posteOccupe;
    private String specialite;
    private String lieuStage;
    private String statut;
    private String motifRejet;
    private LocalDate dateValidation;
    private LocalDate dateResiliation;
    private String motifResiliation;
    private Boolean renouvele;
    private UUID contratParentId;
}
