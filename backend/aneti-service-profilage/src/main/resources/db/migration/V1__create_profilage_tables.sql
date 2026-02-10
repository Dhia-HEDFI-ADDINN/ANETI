-- Flyway Migration V1: Create profilage/segmentation tables

CREATE TABLE IF NOT EXISTS questionnaires (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titre VARCHAR(300) NOT NULL,
    titre_ar VARCHAR(300),
    description VARCHAR(1000),
    version_num INTEGER DEFAULT 1,
    statut VARCHAR(20) NOT NULL DEFAULT 'BROUILLON',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS questions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    questionnaire_id UUID NOT NULL REFERENCES questionnaires(id) ON DELETE CASCADE,
    libelle VARCHAR(500) NOT NULL,
    libelle_ar VARCHAR(500),
    type_question VARCHAR(30) NOT NULL,
    obligatoire BOOLEAN DEFAULT FALSE,
    ordre INTEGER,
    ponderation DOUBLE PRECISION,
    question_parent_id UUID,
    condition_affichage VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS options_reponse (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    question_id UUID NOT NULL REFERENCES questions(id) ON DELETE CASCADE,
    libelle VARCHAR(300) NOT NULL,
    libelle_ar VARCHAR(300),
    valeur VARCHAR(100),
    score DOUBLE PRECISION,
    ordre INTEGER,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS sessions_profilage (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chercheur_emploi_id UUID NOT NULL,
    conseiller_id UUID,
    questionnaire_id UUID NOT NULL REFERENCES questionnaires(id),
    statut VARCHAR(20) NOT NULL DEFAULT 'EN_COURS',
    score_global DOUBLE PRECISION,
    segment_propose VARCHAR(30),
    segment_valide VARCHAR(30),
    commentaire_conseiller VARCHAR(2000),
    date_debut TIMESTAMP,
    date_fin TIMESTAMP,
    date_validation TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS reponses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    session_id UUID NOT NULL REFERENCES sessions_profilage(id) ON DELETE CASCADE,
    question_id UUID NOT NULL,
    option_id UUID,
    valeur_texte VARCHAR(2000),
    valeur_numerique DOUBLE PRECISION,
    score DOUBLE PRECISION,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_sp_chercheur ON sessions_profilage(chercheur_emploi_id);
CREATE INDEX idx_sp_conseiller ON sessions_profilage(conseiller_id);
CREATE INDEX idx_sp_statut ON sessions_profilage(statut);
CREATE INDEX idx_sp_segment ON sessions_profilage(segment_valide);
CREATE INDEX idx_reponse_session ON reponses(session_id);
CREATE INDEX idx_question_questionnaire ON questions(questionnaire_id);
