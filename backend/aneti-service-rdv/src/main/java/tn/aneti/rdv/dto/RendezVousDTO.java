package tn.aneti.rdv.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RendezVousDTO {
    private UUID id;
    @NotNull private UUID conseillerId;
    private UUID chercheurEmploiId;
    private UUID entrepriseId;
    @NotBlank private String titre;
    private String description;
    @NotNull @Future private LocalDateTime dateDebut;
    @NotNull @Future private LocalDateTime dateFin;
    @NotNull private String typeRdv;
    private String canal;
    private String lieu;
    private String lienVisio;
    private String statut;
    private String compteRendu;
    private String motifAnnulation;
}
