package tn.aneti.profilage.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.util.UUID;

@Entity
@Table(name = "reponses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reponse extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionProfilage session;

    @Column(name = "question_id", nullable = false)
    private UUID questionId;

    @Column(name = "option_id")
    private UUID optionId;

    @Column(name = "valeur_texte", length = 2000)
    private String valeurTexte;

    @Column(name = "valeur_numerique")
    private Double valeurNumerique;

    @Column(name = "score")
    private Double score;
}
