package tn.aneti.inscription.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ChercheurEmploiDTO {
    private UUID id;

    @Size(max = 20)
    private String cin;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 100)
    private String prenom;

    private String nomAr;
    private String prenomAr;

    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String sexe;
    private String situationFamiliale;
    private String nationalite;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format email invalide")
    private String email;

    @Pattern(regexp = "^(\\+216)?[0-9]{8}$", message = "Format téléphone invalide")
    private String telephone;

    private String adresse;
    private String codePostal;
    private String gouvernorat;
    private String delegation;
    private String niveauEtude;
    private String diplome;
    private String specialite;
    private Integer experienceAnnees;
    private String statutInscription;
    private UUID betiId;
    private UUID conseillerId;
    private LocalDate dateInscription;
    private Boolean handicap;
    private String typeHandicap;
}
