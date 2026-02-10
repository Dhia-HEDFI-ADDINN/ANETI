CREATE TABLE IF NOT EXISTS evenements (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titre VARCHAR(300) NOT NULL,
    titre_ar VARCHAR(300),
    description TEXT,
    type_evenement VARCHAR(30) NOT NULL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    lieu VARCHAR(500),
    capacite_max INTEGER,
    nombre_inscrits INTEGER DEFAULT 0,
    statut VARCHAR(30) NOT NULL DEFAULT 'PLANIFIE',
    organisateur_id UUID,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS inscriptions_evenement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    evenement_id UUID NOT NULL REFERENCES evenements(id),
    participant_id UUID NOT NULL,
    type_participant VARCHAR(50),
    date_inscription TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    statut VARCHAR(30) NOT NULL DEFAULT 'INSCRIT',
    presence_confirmee BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    UNIQUE(evenement_id, participant_id)
);

CREATE INDEX idx_evenement_type ON evenements(type_evenement);
CREATE INDEX idx_evenement_statut ON evenements(statut);
CREATE INDEX idx_evenement_date_debut ON evenements(date_debut);
CREATE INDEX idx_evenement_organisateur ON evenements(organisateur_id);
CREATE INDEX idx_inscription_evt_evenement ON inscriptions_evenement(evenement_id);
CREATE INDEX idx_inscription_evt_participant ON inscriptions_evenement(participant_id);
