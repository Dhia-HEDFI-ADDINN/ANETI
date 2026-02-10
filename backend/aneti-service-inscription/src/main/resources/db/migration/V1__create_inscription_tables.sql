-- Flyway Migration V1: Create inscription tables
-- Module: Inscription (Chercheurs d'Emploi et Entreprises)

CREATE TABLE IF NOT EXISTS chercheurs_emploi (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cin VARCHAR(20) UNIQUE,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    nom_ar VARCHAR(100),
    prenom_ar VARCHAR(100),
    date_naissance DATE,
    lieu_naissance VARCHAR(200),
    sexe VARCHAR(1),
    situation_familiale VARCHAR(50),
    nationalite VARCHAR(50),
    email VARCHAR(200) UNIQUE,
    telephone VARCHAR(20),
    adresse VARCHAR(500),
    code_postal VARCHAR(10),
    gouvernorat VARCHAR(100),
    delegation VARCHAR(100),
    niveau_etude VARCHAR(100),
    diplome VARCHAR(200),
    specialite VARCHAR(200),
    experience_annees INTEGER,
    statut_inscription VARCHAR(30) NOT NULL DEFAULT 'BROUILLON',
    beti_id UUID,
    conseiller_id UUID,
    keycloak_user_id VARCHAR(100),
    date_inscription DATE,
    motif_rejet VARCHAR(500),
    handicap BOOLEAN DEFAULT FALSE,
    type_handicap VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS entreprises (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    matricule_fiscal VARCHAR(50) UNIQUE,
    raison_sociale VARCHAR(300) NOT NULL,
    raison_sociale_ar VARCHAR(300),
    forme_juridique VARCHAR(100),
    secteur_activite VARCHAR(200),
    sous_secteur VARCHAR(200),
    taille_entreprise VARCHAR(50),
    date_creation DATE,
    email VARCHAR(200) UNIQUE,
    telephone VARCHAR(20),
    fax VARCHAR(20),
    site_web VARCHAR(300),
    adresse_siege VARCHAR(500),
    gouvernorat VARCHAR(100),
    delegation VARCHAR(100),
    code_postal VARCHAR(10),
    nom_contact VARCHAR(200),
    prenom_contact VARCHAR(200),
    fonction_contact VARCHAR(200),
    email_contact VARCHAR(200),
    telephone_contact VARCHAR(20),
    effectif_total INTEGER,
    statut_inscription VARCHAR(30) NOT NULL DEFAULT 'BROUILLON',
    beti_id UUID,
    conseiller_id UUID,
    keycloak_user_id VARCHAR(100),
    date_inscription DATE,
    motif_rejet VARCHAR(500),
    cnss_numero VARCHAR(50),
    rne_numero VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Indexes for performance
CREATE INDEX idx_ce_cin ON chercheurs_emploi(cin);
CREATE INDEX idx_ce_email ON chercheurs_emploi(email);
CREATE INDEX idx_ce_statut ON chercheurs_emploi(statut_inscription);
CREATE INDEX idx_ce_gouvernorat ON chercheurs_emploi(gouvernorat);
CREATE INDEX idx_ce_beti ON chercheurs_emploi(beti_id);
CREATE INDEX idx_ce_conseiller ON chercheurs_emploi(conseiller_id);
CREATE INDEX idx_ce_nom_prenom ON chercheurs_emploi(nom, prenom);

CREATE INDEX idx_ent_matricule ON entreprises(matricule_fiscal);
CREATE INDEX idx_ent_email ON entreprises(email);
CREATE INDEX idx_ent_statut ON entreprises(statut_inscription);
CREATE INDEX idx_ent_gouvernorat ON entreprises(gouvernorat);
CREATE INDEX idx_ent_secteur ON entreprises(secteur_activite);
CREATE INDEX idx_ent_raison_sociale ON entreprises(raison_sociale);
