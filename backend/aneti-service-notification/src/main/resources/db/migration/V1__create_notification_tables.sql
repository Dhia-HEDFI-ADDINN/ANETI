CREATE TABLE IF NOT EXISTS notifications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    destinataire_id UUID NOT NULL,
    type_destinataire VARCHAR(30),
    canal VARCHAR(20) NOT NULL,
    sujet VARCHAR(300) NOT NULL,
    contenu TEXT NOT NULL,
    template_id VARCHAR(100),
    statut VARCHAR(20) NOT NULL DEFAULT 'EN_ATTENTE',
    date_envoi TIMESTAMP,
    date_lecture TIMESTAMP,
    erreur VARCHAR(1000),
    reference_type VARCHAR(50),
    reference_id UUID,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);
CREATE INDEX idx_notif_dest ON notifications(destinataire_id);
CREATE INDEX idx_notif_canal ON notifications(canal);
CREATE INDEX idx_notif_statut ON notifications(statut);
