package tn.aneti.profilage.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Question extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    @Column(name = "libelle", nullable = false, length = 500)
    private String libelle;

    @Column(name = "libelle_ar", length = 500)
    private String libelleAr;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_question", nullable = false)
    private TypeQuestion typeQuestion;

    @Column(name = "obligatoire")
    private boolean obligatoire;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "ponderation")
    private Double ponderation;

    @Column(name = "question_parent_id")
    private java.util.UUID questionParentId;

    @Column(name = "condition_affichage", length = 500)
    private String conditionAffichage;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordre ASC")
    @Builder.Default
    private List<OptionReponse> options = new ArrayList<>();

    public enum TypeQuestion {
        CHOIX_UNIQUE, CHOIX_MULTIPLE, TEXTE_LIBRE, NUMERIQUE, DATE, DICHOTOMIQUE, ECHELLE, CONDITIONNEL
    }
}
