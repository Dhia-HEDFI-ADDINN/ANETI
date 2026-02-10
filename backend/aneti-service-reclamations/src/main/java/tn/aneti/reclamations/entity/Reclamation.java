package tn.aneti.reclamations.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reclamations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reclamation extends BaseEntity {

    @Column(name = "usager_id", nullable = false)
    private UUID usagerId;

    @Column(name = "type_usager", nullable = false, length = 50)
    private String typeUsager;

    @Column(name = "sujet", nullable = false, length = 300)
    private String sujet;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "categorie", length = 100)
    private String categorie;

    @Enumerated(EnumType.STRING)
    @Column(name = "priorite", nullable = false, length = 20)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 20)
    private StatutReclamation statut;

    @Column(name = "traite_par")
    private UUID traitePar;

    @Column(name = "reponse", columnDefinition = "TEXT")
    private String reponse;

    @Column(name = "date_resolution")
    private LocalDateTime dateResolution;

    public enum Priorite {
        BASSE, MOYENNE, HAUTE, URGENTE
    }

    public enum StatutReclamation {
        OUVERTE, EN_COURS, RESOLUE, FERMEE, REJETEE
    }
}
