package tn.aneti.notification.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.aneti.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "notifications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification extends BaseEntity {
    @Column(name = "destinataire_id", nullable = false) private UUID destinataireId;
    @Column(name = "type_destinataire", length = 30) private String typeDestinataire;
    @Enumerated(EnumType.STRING) @Column(name = "canal", nullable = false) private Canal canal;
    @Column(name = "sujet", nullable = false, length = 300) private String sujet;
    @Column(name = "contenu", nullable = false, columnDefinition = "TEXT") private String contenu;
    @Column(name = "template_id", length = 100) private String templateId;
    @Enumerated(EnumType.STRING) @Column(name = "statut", nullable = false) private StatutNotification statut;
    @Column(name = "date_envoi") private LocalDateTime dateEnvoi;
    @Column(name = "date_lecture") private LocalDateTime dateLecture;
    @Column(name = "erreur", length = 1000) private String erreur;
    @Column(name = "reference_type", length = 50) private String referenceType;
    @Column(name = "reference_id") private UUID referenceId;

    public enum Canal { EMAIL, SMS, PUSH, INTERNE }
    public enum StatutNotification { EN_ATTENTE, ENVOYE, DELIVRE, LU, ERREUR }
}
