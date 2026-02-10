package tn.aneti.administration.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;

@Entity @Table(name = "betis")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Beti extends BaseEntity {
    @Column(name = "code", unique = true, nullable = false, length = 20) private String code;
    @Column(name = "nom", nullable = false, length = 200) private String nom;
    @Column(name = "nom_ar", length = 200) private String nomAr;
    @Column(name = "gouvernorat", nullable = false, length = 100) private String gouvernorat;
    @Column(name = "delegation", length = 100) private String delegation;
    @Column(name = "adresse", length = 500) private String adresse;
    @Column(name = "telephone", length = 20) private String telephone;
    @Column(name = "email", length = 200) private String email;
    @Column(name = "type_bureau", length = 50) private String typeBureau;
    @Column(name = "region", length = 100) private String region;
}
