package tn.aneti.evenements.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "evenements")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Evenement extends BaseEntity {

    @Column(name = "titre", nullable = false, length = 300)
    private String titre;

    @Column(name = "titre_ar", length = 300)
    private String titreAr;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_evenement", nullable = false, length = 30)
    private TypeEvenement typeEvenement;

    @Column(name = "date_debut", nullable = false)
    private LocalDateTime dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDateTime dateFin;

    @Column(name = "lieu", length = 500)
    private String lieu;

    @Column(name = "capacite_max")
    private Integer capaciteMax;

    @Column(name = "nombre_inscrits")
    private Integer nombreInscrits;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 30)
    private StatutEvenement statut;

    @Column(name = "organisateur_id")
    private UUID organisateurId;

    public enum TypeEvenement {
        FORUM, ATELIER, JOURNEE_METIER, CONFERENCE, SALON
    }

    public enum StatutEvenement {
        PLANIFIE, OUVERT, EN_COURS, TERMINE, ANNULE
    }
}
