package tn.aneti.journalisation.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "journal_entries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JournalEntry extends BaseEntity {
    @Column(name = "utilisateur_id") private UUID utilisateurId;
    @Column(name = "utilisateur_nom", length = 200) private String utilisateurNom;
    @Column(name = "action", nullable = false, length = 100) private String action;
    @Column(name = "module", nullable = false, length = 100) private String module;
    @Column(name = "entite_type", length = 100) private String entiteType;
    @Column(name = "entite_id") private UUID entiteId;
    @Column(name = "description", length = 2000) private String description;
    @Column(name = "donnees_avant", columnDefinition = "TEXT") private String donneesAvant;
    @Column(name = "donnees_apres", columnDefinition = "TEXT") private String donneesApres;
    @Column(name = "adresse_ip", length = 50) private String adresseIp;
    @Column(name = "user_agent", length = 500) private String userAgent;
    @Column(name = "date_action", nullable = false) private LocalDateTime dateAction;
    @Enumerated(EnumType.STRING) @Column(name = "niveau") private Niveau niveau;

    public enum Niveau { INFO, WARNING, ERROR, CRITICAL }
}
