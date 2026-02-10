package tn.aneti.profilage.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionnaires")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Questionnaire extends BaseEntity {

    @Column(name = "titre", nullable = false, length = 300)
    private String titre;

    @Column(name = "titre_ar", length = 300)
    private String titreAr;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "version_num")
    private Integer versionNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutQuestionnaire statut;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordre ASC")
    @Builder.Default
    private List<Question> questions = new ArrayList<>();

    public enum StatutQuestionnaire {
        BROUILLON, VALIDE, ARCHIVE
    }
}
