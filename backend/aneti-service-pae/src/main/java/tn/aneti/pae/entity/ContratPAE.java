package tn.aneti.pae.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contrats_pae")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContratPAE extends BaseEntity {

    @Column(name = "chercheur_emploi_id", nullable = false)
    private UUID chercheurEmploiId;

    @Column(name = "entreprise_id", nullable = false)
    private UUID entrepriseId;

    @Column(name = "conseiller_id")
    private UUID conseillerId;

    @Column(name = "beti_id")
    private UUID betiId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_contrat", nullable = false)
    private TypeContratPAE typeContrat;

    @Column(name = "numero_contrat", unique = true, length = 50)
    private String numeroContrat;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "duree_mois")
    private Integer dureeMois;

    @Column(name = "montant_indemnite")
    private Double montantIndemnite;

    @Column(name = "poste_occupe", length = 200)
    private String posteOccupe;

    @Column(name = "specialite", length = 200)
    private String specialite;

    @Column(name = "lieu_stage", length = 300)
    private String lieuStage;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutContrat statut;

    @Column(name = "motif_rejet", length = 500)
    private String motifRejet;

    @Column(name = "date_validation")
    private LocalDate dateValidation;

    @Column(name = "date_resiliation")
    private LocalDate dateResiliation;

    @Column(name = "motif_resiliation", length = 500)
    private String motifResiliation;

    @Column(name = "renouvele")
    private Boolean renouvele;

    @Column(name = "contrat_parent_id")
    private UUID contratParentId;

    public enum TypeContratPAE {
        CIVP, KARAMA, SCV, CAIP, AUTRE
    }

    public enum StatutContrat {
        BROUILLON, SOUMIS, EN_COURS_VALIDATION, VALIDE, ACTIF, SUSPENDU, RENOUVELE, TERMINE, RESILIE, REJETE
    }
}
