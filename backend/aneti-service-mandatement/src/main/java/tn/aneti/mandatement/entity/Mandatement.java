package tn.aneti.mandatement.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "mandatements")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Mandatement extends BaseEntity {

    @Column(name = "chercheur_emploi_id", nullable = false)
    private UUID chercheurEmploiId;

    @Column(name = "contrat_pae_id", nullable = false)
    private UUID contratPaeId;

    @Column(name = "type_mandatement", nullable = false, length = 100)
    private String typeMandatement;

    @Column(name = "montant", nullable = false)
    private Double montant;

    @Column(name = "periodicite", length = 50)
    private String periodicite;

    @Column(name = "date_mandatement", nullable = false)
    private LocalDate dateMandatement;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_paiement", nullable = false, length = 30)
    private StatutPaiement statutPaiement;

    @Column(name = "numero_bordereau", length = 100)
    private String numeroBordereau;

    @Column(name = "observations", length = 1000)
    private String observations;

    public enum StatutPaiement {
        EN_ATTENTE, VALIDE, PAYE, REJETE
    }
}
