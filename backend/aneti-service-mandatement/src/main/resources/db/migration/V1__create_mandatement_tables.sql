CREATE TABLE IF NOT EXISTS mandatements (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chercheur_emploi_id UUID NOT NULL,
    contrat_pae_id UUID NOT NULL,
    type_mandatement VARCHAR(100) NOT NULL,
    montant DOUBLE PRECISION NOT NULL,
    periodicite VARCHAR(50),
    date_mandatement DATE NOT NULL,
    statut_paiement VARCHAR(30) NOT NULL DEFAULT 'EN_ATTENTE',
    numero_bordereau VARCHAR(100),
    observations VARCHAR(1000),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_mandatement_chercheur ON mandatements(chercheur_emploi_id);
CREATE INDEX idx_mandatement_contrat ON mandatements(contrat_pae_id);
CREATE INDEX idx_mandatement_statut ON mandatements(statut_paiement);
CREATE INDEX idx_mandatement_date ON mandatements(date_mandatement);
CREATE INDEX idx_mandatement_bordereau ON mandatements(numero_bordereau);
CREATE INDEX idx_mandatement_type ON mandatements(type_mandatement);
