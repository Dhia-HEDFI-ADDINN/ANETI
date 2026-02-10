CREATE TABLE IF NOT EXISTS offres_emploi (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    entreprise_id UUID NOT NULL,
    titre VARCHAR(300) NOT NULL,
    titre_ar VARCHAR(300),
    description TEXT NOT NULL,
    description_ar TEXT,
    type_contrat VARCHAR(50),
    secteur_activite VARCHAR(200),
    metier VARCHAR(200),
    code_rtmc VARCHAR(50),
    niveau_etude_requis VARCHAR(100),
    experience_requise INTEGER,
    competences_requises VARCHAR(1000),
    langues_requises VARCHAR(200),
    gouvernorat VARCHAR(100),
    delegation VARCHAR(100),
    lieu_travail VARCHAR(300),
    salaire_min DOUBLE PRECISION,
    salaire_max DOUBLE PRECISION,
    nombre_postes INTEGER,
    date_publication DATE,
    date_expiration DATE,
    statut VARCHAR(20) NOT NULL DEFAULT 'BROUILLON',
    beti_id UUID,
    conseiller_id UUID,
    motif_rejet VARCHAR(500),
    nombre_candidatures INTEGER DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS candidatures (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    offre_id UUID NOT NULL,
    chercheur_emploi_id UUID NOT NULL,
    lettre_motivation TEXT,
    cv_document_id UUID,
    score_matching DOUBLE PRECISION,
    date_candidature TIMESTAMP,
    statut VARCHAR(30) NOT NULL DEFAULT 'SOUMISE',
    commentaire_conseiller VARCHAR(1000),
    commentaire_entreprise VARCHAR(1000),
    date_entretien TIMESTAMP,
    resultat_entretien VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    UNIQUE(offre_id, chercheur_emploi_id)
);

CREATE INDEX idx_offre_entreprise ON offres_emploi(entreprise_id);
CREATE INDEX idx_offre_statut ON offres_emploi(statut);
CREATE INDEX idx_offre_secteur ON offres_emploi(secteur_activite);
CREATE INDEX idx_offre_gouvernorat ON offres_emploi(gouvernorat);
CREATE INDEX idx_offre_titre ON offres_emploi(titre);
CREATE INDEX idx_cand_offre ON candidatures(offre_id);
CREATE INDEX idx_cand_ce ON candidatures(chercheur_emploi_id);
CREATE INDEX idx_cand_statut ON candidatures(statut);
