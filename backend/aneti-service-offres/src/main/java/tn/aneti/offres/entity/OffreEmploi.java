package tn.aneti.offres.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "offres_emploi")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OffreEmploi extends BaseEntity {

    @Column(name = "entreprise_id", nullable = false)
    private UUID entrepriseId;

    @Column(name = "titre", nullable = false, length = 300)
    private String titre;

    @Column(name = "titre_ar", length = 300)
    private String titreAr;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "description_ar", columnDefinition = "TEXT")
    private String descriptionAr;

    @Column(name = "type_contrat", length = 50)
    private String typeContrat;

    @Column(name = "secteur_activite", length = 200)
    private String secteurActivite;

    @Column(name = "metier", length = 200)
    private String metier;

    @Column(name = "code_rtmc", length = 50)
    private String codeRtmc;

    @Column(name = "niveau_etude_requis", length = 100)
    private String niveauEtudeRequis;

    @Column(name = "experience_requise")
    private Integer experienceRequise;

    @Column(name = "competences_requises", length = 1000)
    private String competencesRequises;

    @Column(name = "langues_requises", length = 200)
    private String languesRequises;

    @Column(name = "gouvernorat", length = 100)
    private String gouvernorat;

    @Column(name = "delegation", length = 100)
    private String delegation;

    @Column(name = "lieu_travail", length = 300)
    private String lieuTravail;

    @Column(name = "salaire_min")
    private Double salaireMin;

    @Column(name = "salaire_max")
    private Double salaireMax;

    @Column(name = "nombre_postes")
    private Integer nombrePostes;

    @Column(name = "date_publication")
    private LocalDate datePublication;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutOffre statut;

    @Column(name = "beti_id")
    private UUID betiId;

    @Column(name = "conseiller_id")
    private UUID conseillerId;

    @Column(name = "motif_rejet", length = 500)
    private String motifRejet;

    @Column(name = "nombre_candidatures")
    private Integer nombreCandidatures;

    public enum StatutOffre {
        BROUILLON, SOUMISE, PUBLIEE, SUSPENDUE, POURVUE, EXPIREE, REJETEE, ARCHIVEE
    }
}
