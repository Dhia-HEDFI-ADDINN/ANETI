package tn.aneti.accompagnement.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "suivis_accompagnement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SuiviAccompagnement extends BaseEntity {

    @Column(name = "chercheur_emploi_id", nullable = false)
    private UUID chercheurEmploiId;

    @Column(name = "conseiller_id", nullable = false)
    private UUID conseillerId;

    @Column(name = "plan_id")
    private UUID planId;

    @Column(name = "type_suivi", nullable = false, length = 50)
    private String typeSuivi;

    @Column(name = "canal", length = 30)
    private String canal;

    @Column(name = "date_suivi", nullable = false)
    private LocalDateTime dateSuivi;

    @Column(name = "compte_rendu", length = 3000)
    private String compteRendu;

    @Column(name = "actions_decidees", length = 2000)
    private String actionsDecidees;

    @Column(name = "prochain_rdv")
    private LocalDateTime prochainRdv;
}
