package tn.aneti.rdv.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "rendez_vous")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RendezVous extends BaseEntity {

    @Column(name = "conseiller_id", nullable = false) private UUID conseillerId;
    @Column(name = "chercheur_emploi_id") private UUID chercheurEmploiId;
    @Column(name = "entreprise_id") private UUID entrepriseId;
    @Column(name = "titre", nullable = false, length = 300) private String titre;
    @Column(name = "description", length = 1000) private String description;
    @Column(name = "date_debut", nullable = false) private LocalDateTime dateDebut;
    @Column(name = "date_fin", nullable = false) private LocalDateTime dateFin;
    @Enumerated(EnumType.STRING) @Column(name = "type_rdv", nullable = false) private TypeRdv typeRdv;
    @Enumerated(EnumType.STRING) @Column(name = "canal") private Canal canal;
    @Column(name = "lieu", length = 300) private String lieu;
    @Column(name = "lien_visio", length = 500) private String lienVisio;
    @Enumerated(EnumType.STRING) @Column(name = "statut", nullable = false) private StatutRdv statut;
    @Column(name = "compte_rendu", length = 3000) private String compteRendu;
    @Column(name = "motif_annulation", length = 500) private String motifAnnulation;

    public enum TypeRdv { INDIVIDUEL, COLLECTIF, ATELIER, VISITE_ENTREPRISE, ENTRETIEN_EMBAUCHE }
    public enum Canal { PRESENTIEL, VISIO, TELEPHONE }
    public enum StatutRdv { PLANIFIE, CONFIRME, EN_COURS, REALISE, ANNULE, REPORTE, ABSENT }
}
