package tn.aneti.profilage.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sessions_profilage")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SessionProfilage extends BaseEntity {

    @Column(name = "chercheur_emploi_id", nullable = false)
    private UUID chercheurEmploiId;

    @Column(name = "conseiller_id")
    private UUID conseillerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutSession statut;

    @Column(name = "score_global")
    private Double scoreGlobal;

    @Enumerated(EnumType.STRING)
    @Column(name = "segment_propose")
    private Segment segmentPropose;

    @Enumerated(EnumType.STRING)
    @Column(name = "segment_valide")
    private Segment segmentValide;

    @Column(name = "commentaire_conseiller", length = 2000)
    private String commentaireConseiller;

    @Column(name = "date_debut")
    private LocalDateTime dateDebut;

    @Column(name = "date_fin")
    private LocalDateTime dateFin;

    @Column(name = "date_validation")
    private LocalDateTime dateValidation;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Reponse> reponses = new ArrayList<>();

    public enum StatutSession {
        EN_COURS, SOUMIS, ANALYSE, VALIDE, ARCHIVE
    }

    public enum Segment {
        AUTONOME, GUIDE, RENFORCE, GLOBAL, CREATION_ENTREPRISE
    }
}
