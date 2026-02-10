package tn.aneti.referentiel.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;

@Entity @Table(name = "referentiels")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Referentiel extends BaseEntity {
    @Column(name = "type", nullable = false, length = 100) private String type;
    @Column(name = "code", nullable = false, length = 50) private String code;
    @Column(name = "libelle_fr", nullable = false, length = 300) private String libelleFr;
    @Column(name = "libelle_ar", length = 300) private String libelleAr;
    @Column(name = "description", length = 1000) private String description;
    @Column(name = "parent_code", length = 50) private String parentCode;
    @Column(name = "ordre") private Integer ordre;
}
