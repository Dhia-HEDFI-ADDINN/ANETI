package tn.aneti.offres.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidatures")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Candidature extends BaseEntity {

    @Column(name = "offre_id", nullable = false)
    private UUID offreId;

    @Column(name = "chercheur_emploi_id", nullable = false)
    private UUID chercheurEmploiId;

    @Column(name = "lettre_motivation", columnDefinition = "TEXT")
    private String lettreMotivation;

    @Column(name = "cv_document_id")
    private UUID cvDocumentId;

    @Column(name = "score_matching")
    private Double scoreMatching;

    @Column(name = "date_candidature")
    private LocalDateTime dateCandidature;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutCandidature statut;

    @Column(name = "commentaire_conseiller", length = 1000)
    private String commentaireConseiller;

    @Column(name = "commentaire_entreprise", length = 1000)
    private String commentaireEntreprise;

    @Column(name = "date_entretien")
    private LocalDateTime dateEntretien;

    @Column(name = "resultat_entretien", length = 500)
    private String resultatEntretien;

    public enum StatutCandidature {
        SOUMISE, EN_EXAMEN, PRESELECTIONNE, ENTRETIEN_PLANIFIE, ACCEPTEE, REJETEE, RETIREE
    }
}
