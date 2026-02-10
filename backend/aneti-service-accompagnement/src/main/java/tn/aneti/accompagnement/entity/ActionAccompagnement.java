package tn.aneti.accompagnement.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDate;

@Entity
@Table(name = "actions_accompagnement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActionAccompagnement extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private PlanAccompagnement plan;

    @Column(name = "type_action", nullable = false, length = 50)
    private String typeAction;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin_prevue")
    private LocalDate dateFinPrevue;

    @Column(name = "date_realisation")
    private LocalDate dateRealisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutAction statut;

    @Column(name = "resultat", length = 1000)
    private String resultat;

    @Column(name = "commentaire", length = 1000)
    private String commentaire;

    @Column(name = "priorite")
    private Integer priorite;

    public enum StatutAction {
        PLANIFIEE, EN_COURS, REALISEE, ANNULEE, REPORTEE
    }
}
