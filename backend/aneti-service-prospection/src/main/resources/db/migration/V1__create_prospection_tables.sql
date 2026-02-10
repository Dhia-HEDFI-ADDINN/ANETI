CREATE TABLE IF NOT EXISTS actions_prospection (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conseiller_id UUID NOT NULL,
    entreprise_id UUID NOT NULL,
    type_action VARCHAR(100) NOT NULL,
    objectif VARCHAR(500),
    date_prevue DATE NOT NULL,
    date_realisation DATE,
    statut VARCHAR(30) NOT NULL DEFAULT 'PLANIFIEE',
    compte_rendu TEXT,
    resultats TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_prospection_conseiller ON actions_prospection(conseiller_id);
CREATE INDEX idx_prospection_entreprise ON actions_prospection(entreprise_id);
CREATE INDEX idx_prospection_statut ON actions_prospection(statut);
CREATE INDEX idx_prospection_date_prevue ON actions_prospection(date_prevue);
CREATE INDEX idx_prospection_type ON actions_prospection(type_action);
