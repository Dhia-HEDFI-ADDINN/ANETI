CREATE TABLE IF NOT EXISTS tableaux_bord (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nom VARCHAR(300) NOT NULL,
    type VARCHAR(30) NOT NULL,
    requete TEXT,
    parametres TEXT,
    createur_id UUID NOT NULL,
    partage BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS indicateurs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tableau_bord_id UUID NOT NULL REFERENCES tableaux_bord(id),
    nom VARCHAR(300) NOT NULL,
    description TEXT,
    requete_sql TEXT,
    type_visualisation VARCHAR(50),
    ordre_affichage INTEGER,
    configuration TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS rapports_generes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tableau_bord_id UUID REFERENCES tableaux_bord(id),
    nom VARCHAR(300) NOT NULL,
    format VARCHAR(20) NOT NULL,
    parametres_execution TEXT,
    document_id UUID,
    date_generation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    genere_par UUID NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_tableau_type ON tableaux_bord(type);
CREATE INDEX idx_tableau_createur ON tableaux_bord(createur_id);
CREATE INDEX idx_tableau_partage ON tableaux_bord(partage);
CREATE INDEX idx_indicateur_tableau ON indicateurs(tableau_bord_id);
CREATE INDEX idx_rapport_tableau ON rapports_generes(tableau_bord_id);
CREATE INDEX idx_rapport_date ON rapports_generes(date_generation);
CREATE INDEX idx_rapport_genere_par ON rapports_generes(genere_par);
