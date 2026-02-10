CREATE TABLE IF NOT EXISTS rendez_vous (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conseiller_id UUID NOT NULL,
    chercheur_emploi_id UUID,
    entreprise_id UUID,
    titre VARCHAR(300) NOT NULL,
    description VARCHAR(1000),
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    type_rdv VARCHAR(30) NOT NULL,
    canal VARCHAR(20),
    lieu VARCHAR(300),
    lien_visio VARCHAR(500),
    statut VARCHAR(20) NOT NULL DEFAULT 'PLANIFIE',
    compte_rendu VARCHAR(3000),
    motif_annulation VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);
CREATE INDEX idx_rdv_conseiller ON rendez_vous(conseiller_id);
CREATE INDEX idx_rdv_ce ON rendez_vous(chercheur_emploi_id);
CREATE INDEX idx_rdv_date ON rendez_vous(date_debut);
CREATE INDEX idx_rdv_statut ON rendez_vous(statut);
