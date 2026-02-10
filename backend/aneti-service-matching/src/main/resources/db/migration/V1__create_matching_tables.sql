CREATE TABLE IF NOT EXISTS matching_results (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chercheur_emploi_id UUID NOT NULL,
    offre_emploi_id UUID NOT NULL,
    score_global DOUBLE PRECISION NOT NULL,
    detail_scores TEXT,
    statut VARCHAR(30) NOT NULL DEFAULT 'EN_ATTENTE',
    date_matching TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_matching_chercheur ON matching_results(chercheur_emploi_id);
CREATE INDEX idx_matching_offre ON matching_results(offre_emploi_id);
CREATE INDEX idx_matching_score ON matching_results(score_global DESC);
CREATE INDEX idx_matching_statut ON matching_results(statut);
CREATE INDEX idx_matching_date ON matching_results(date_matching);
CREATE UNIQUE INDEX idx_matching_unique_pair ON matching_results(chercheur_emploi_id, offre_emploi_id);
