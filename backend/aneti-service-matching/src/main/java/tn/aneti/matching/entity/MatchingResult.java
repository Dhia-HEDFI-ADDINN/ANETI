package tn.aneti.matching.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "matching_results")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MatchingResult extends BaseEntity {

    @Column(name = "chercheur_emploi_id", nullable = false)
    private UUID chercheurEmploiId;

    @Column(name = "offre_emploi_id", nullable = false)
    private UUID offreEmploiId;

    @Column(name = "score_global", nullable = false)
    private Double scoreGlobal;

    @Column(name = "detail_scores", columnDefinition = "TEXT")
    private String detailScores;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 30)
    private StatutMatching statut;

    @Column(name = "date_matching", nullable = false)
    private LocalDateTime dateMatching;

    public enum StatutMatching {
        EN_ATTENTE, CONSULTE, ACCEPTE, REJETE
    }
}
