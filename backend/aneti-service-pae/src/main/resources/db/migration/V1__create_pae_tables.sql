CREATE TABLE IF NOT EXISTS contrats_pae (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chercheur_emploi_id UUID NOT NULL,
    entreprise_id UUID NOT NULL,
    conseiller_id UUID,
    beti_id UUID,
    type_contrat VARCHAR(20) NOT NULL,
    numero_contrat VARCHAR(50) UNIQUE,
    date_debut DATE,
    date_fin DATE,
    duree_mois INTEGER,
    montant_indemnite DOUBLE PRECISION,
    poste_occupe VARCHAR(200),
    specialite VARCHAR(200),
    lieu_stage VARCHAR(300),
    statut VARCHAR(30) NOT NULL DEFAULT 'BROUILLON',
    motif_rejet VARCHAR(500),
    date_validation DATE,
    date_resiliation DATE,
    motif_resiliation VARCHAR(500),
    renouvele BOOLEAN DEFAULT FALSE,
    contrat_parent_id UUID,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_pae_ce ON contrats_pae(chercheur_emploi_id);
CREATE INDEX idx_pae_ent ON contrats_pae(entreprise_id);
CREATE INDEX idx_pae_type ON contrats_pae(type_contrat);
CREATE INDEX idx_pae_statut ON contrats_pae(statut);
CREATE INDEX idx_pae_numero ON contrats_pae(numero_contrat);
CREATE INDEX idx_pae_beti ON contrats_pae(beti_id);
