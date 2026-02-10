package tn.aneti.inscription.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class EntrepriseDTO {
    private UUID id;

    @Size(max = 50)
    private String matriculeFiscal;

    @NotBlank(message = "La raison sociale est obligatoire")
    @Size(max = 300)
    private String raisonSociale;

    private String raisonSocialeAr;
    private String formeJuridique;
    private String secteurActivite;
    private String sousSecteur;
    private String tailleEntreprise;
    private LocalDate dateCreation;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format email invalide")
    private String email;

    private String telephone;
    private String fax;
    private String siteWeb;
    private String adresseSiege;
    private String gouvernorat;
    private String delegation;
    private String codePostal;
    private String nomContact;
    private String prenomContact;
    private String fonctionContact;
    private String emailContact;
    private String telephoneContact;
    private Integer effectifTotal;
    private String statutInscription;
    private UUID betiId;
    private UUID conseillerId;
    private LocalDate dateInscription;
    private String cnssNumero;
    private String rneNumero;
}
