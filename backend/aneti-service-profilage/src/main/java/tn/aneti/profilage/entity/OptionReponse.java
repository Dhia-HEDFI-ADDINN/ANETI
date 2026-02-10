package tn.aneti.profilage.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;

@Entity
@Table(name = "options_reponse")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OptionReponse extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "libelle", nullable = false, length = 300)
    private String libelle;

    @Column(name = "libelle_ar", length = 300)
    private String libelleAr;

    @Column(name = "valeur", length = 100)
    private String valeur;

    @Column(name = "score")
    private Double score;

    @Column(name = "ordre")
    private Integer ordre;
}
