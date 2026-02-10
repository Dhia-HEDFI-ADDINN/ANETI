package tn.aneti.inscription.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "entreprises")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Entreprise extends BaseEntity {

    @Column(name = "matricule_fiscal", unique = true, length = 50)
    private String matriculeFiscal;

    @Column(name = "raison_sociale", nullable = false, length = 300)
    private String raisonSociale;

    @Column(name = "raison_sociale_ar", length = 300)
    private String raisonSocialeAr;

    @Column(name = "forme_juridique", length = 100)
    private String formeJuridique;

    @Column(name = "secteur_activite", length = 200)
    private String secteurActivite;

    @Column(name = "sous_secteur", length = 200)
    private String sousSecteur;

    @Column(name = "taille_entreprise", length = 50)
    private String tailleEntreprise;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "email", unique = true, length = 200)
    private String email;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "fax", length = 20)
    private String fax;

    @Column(name = "site_web", length = 300)
    private String siteWeb;

    @Column(name = "adresse_siege", length = 500)
    private String adresseSiege;

    @Column(name = "gouvernorat", length = 100)
    private String gouvernorat;

    @Column(name = "delegation", length = 100)
    private String delegation;

    @Column(name = "code_postal", length = 10)
    private String codePostal;

    @Column(name = "nom_contact", length = 200)
    private String nomContact;

    @Column(name = "prenom_contact", length = 200)
    private String prenomContact;

    @Column(name = "fonction_contact", length = 200)
    private String fonctionContact;

    @Column(name = "email_contact", length = 200)
    private String emailContact;

    @Column(name = "telephone_contact", length = 20)
    private String telephoneContact;

    @Column(name = "effectif_total")
    private Integer effectifTotal;

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

    @Column(name = "cnss_numero", length = 50)
    private String cnssNumero;

    @Column(name = "rne_numero", length = 50)
    private String rneNumero;

    public enum StatutInscription {
        BROUILLON, SOUMIS, EN_COURS_VALIDATION, VALIDE, REJETE, SUSPENDU, ARCHIVE
    }
}
