package tn.aneti.reporting.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.util.UUID;

@Entity
@Table(name = "tableaux_bord")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TableauBord extends BaseEntity {

    @Column(name = "nom", nullable = false, length = 300)
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 30)
    private TypeTableauBord type;

    @Column(name = "requete", columnDefinition = "TEXT")
    private String requete;

    @Column(name = "parametres", columnDefinition = "TEXT")
    private String parametres;

    @Column(name = "createur_id", nullable = false)
    private UUID createurId;

    @Column(name = "partage", nullable = false)
    private Boolean partage = false;

    public enum TypeTableauBord {
        OPERATIONNEL, STRATEGIQUE, ANALYTIQUE
    }
}
