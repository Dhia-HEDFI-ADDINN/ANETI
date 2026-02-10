CREATE TABLE IF NOT EXISTS reclamations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usager_id UUID NOT NULL,
    type_usager VARCHAR(50) NOT NULL,
    sujet VARCHAR(300) NOT NULL,
    description TEXT NOT NULL,
    categorie VARCHAR(100),
    priorite VARCHAR(20) NOT NULL DEFAULT 'MOYENNE',
    statut VARCHAR(20) NOT NULL DEFAULT 'OUVERTE',
    traite_par UUID,
    reponse TEXT,
    date_resolution TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS pieces_jointes_reclamation (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reclamation_id UUID NOT NULL REFERENCES reclamations(id),
    document_id UUID NOT NULL,
    type_piece VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_reclamation_usager ON reclamations(usager_id);
CREATE INDEX idx_reclamation_statut ON reclamations(statut);
CREATE INDEX idx_reclamation_priorite ON reclamations(priorite);
CREATE INDEX idx_reclamation_categorie ON reclamations(categorie);
CREATE INDEX idx_reclamation_traite_par ON reclamations(traite_par);
CREATE INDEX idx_reclamation_date ON reclamations(created_at);
CREATE INDEX idx_pj_reclamation ON pieces_jointes_reclamation(reclamation_id);
