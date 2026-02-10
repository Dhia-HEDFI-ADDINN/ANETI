package tn.aneti.accompagnement.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "plans_accompagnement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlanAccompagnement extends BaseEntity {

    @Column(name = "chercheur_emploi_id", nullable = false)
    private UUID chercheurEmploiId;

    @Column(name = "conseiller_id", nullable = false)
    private UUID conseillerId;

    @Column(name = "segment", length = 30)
    private String segment;

    @Column(name = "objectif_principal", length = 500)
    private String objectifPrincipal;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin_prevue")
    private LocalDate dateFinPrevue;

    @Column(name = "date_fin_effective")
    private LocalDate dateFinEffective;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutPlan statut;

    @Column(name = "bilan", length = 2000)
    private String bilan;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dateDebut ASC")
    @Builder.Default
    private List<ActionAccompagnement> actions = new ArrayList<>();

    public enum StatutPlan {
        BROUILLON, VALIDE, EN_COURS, SUSPENDU, TERMINE, ABANDONNE
    }
}
