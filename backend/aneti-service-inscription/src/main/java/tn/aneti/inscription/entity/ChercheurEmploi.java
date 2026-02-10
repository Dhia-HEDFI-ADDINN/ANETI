package tn.aneti.inscription.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "chercheurs_emploi")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ChercheurEmploi extends BaseEntity {

    @Column(name = "cin", unique = true, length = 20)
    private String cin;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @Column(name = "nom_ar", length = 100)
    private String nomAr;

    @Column(name = "prenom_ar", length = 100)
    private String prenomAr;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "lieu_naissance", length = 200)
    private String lieuNaissance;

    @Column(name = "sexe", length = 1)
    private String sexe;

    @Column(name = "situation_familiale", length = 50)
    private String situationFamiliale;

    @Column(name = "nationalite", length = 50)
    private String nationalite;

    @Column(name = "email", unique = true, length = 200)
    private String email;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "adresse", length = 500)
    private String adresse;

    @Column(name = "code_postal", length = 10)
    private String codePostal;

    @Column(name = "gouvernorat", length = 100)
    private String gouvernorat;

    @Column(name = "delegation", length = 100)
    private String delegation;

    @Column(name = "niveau_etude", length = 100)
    private String niveauEtude;

    @Column(name = "diplome", length = 200)
    private String diplome;

    @Column(name = "specialite", length = 200)
    private String specialite;

    @Column(name = "experience_annees")
    private Integer experienceAnnees;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_inscription", nullable = false)
    private StatutInscription statutInscription;

    @Column(name = "beti_id")
    private UUID betiId;

    @Column(name = "conseiller_id")
    private UUID conseillerId;

    @Column(name = "keycloak_user_id", length = 100)
    private String keycloakUserId;

    @Column(name = "date_inscription")
    private LocalDate dateInscription;

    @Column(name = "motif_rejet", length = 500)
    private String motifRejet;

    @Column(name = "handicap")
    private Boolean handicap;

    @Column(name = "type_handicap", length = 100)
    private String typeHandicap;

    public enum StatutInscription {
        BROUILLON, SOUMIS, EN_COURS_VALIDATION, VALIDE, REJETE, SUSPENDU, ARCHIVE
    }
}
