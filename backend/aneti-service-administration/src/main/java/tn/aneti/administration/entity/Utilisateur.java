package tn.aneti.administration.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.util.UUID;

@Entity @Table(name = "utilisateurs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Utilisateur extends BaseEntity {
    @Column(name = "keycloak_id", unique = true, length = 100) private String keycloakId;
    @Column(name = "nom", nullable = false, length = 100) private String nom;
    @Column(name = "prenom", nullable = false, length = 100) private String prenom;
    @Column(name = "email", unique = true, length = 200) private String email;
    @Column(name = "telephone", length = 20) private String telephone;
    @Enumerated(EnumType.STRING) @Column(name = "type_utilisateur", nullable = false) private TypeUtilisateur typeUtilisateur;
    @Column(name = "beti_id") private UUID betiId;
    @Column(name = "direction_id") private UUID directionId;
    @Column(name = "fonction", length = 200) private String fonction;
    @Column(name = "matricule", length = 50) private String matricule;

    public enum TypeUtilisateur { CONSEILLER_REFERENT, CONSEILLER_ANIMATEUR, CONSEILLER_CENTRAL, CHEF_BETI, ADMINISTRATEUR, SUPERVISEUR }
}
