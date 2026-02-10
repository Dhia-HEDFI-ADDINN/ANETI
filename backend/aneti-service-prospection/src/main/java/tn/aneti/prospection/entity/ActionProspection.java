package tn.aneti.prospection.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "actions_prospection")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActionProspection extends BaseEntity {

    @Column(name = "conseiller_id", nullable = false)
    private UUID conseillerId;

    @Column(name = "entreprise_id", nullable = false)
    private UUID entrepriseId;

    @Column(name = "type_action", nullable = false, length = 100)
    private String typeAction;

    @Column(name = "objectif", length = 500)
    private String objectif;

    @Column(name = "date_prevue", nullable = false)
    private LocalDate datePrevue;

    @Column(name = "date_realisation")
    private LocalDate dateRealisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 30)
    private StatutProspection statut;

    @Column(name = "compte_rendu", columnDefinition = "TEXT")
    private String compteRendu;

    @Column(name = "resultats", columnDefinition = "TEXT")
    private String resultats;

    public enum StatutProspection {
        PLANIFIEE, EN_COURS, REALISEE, ANNULEE
    }
}
