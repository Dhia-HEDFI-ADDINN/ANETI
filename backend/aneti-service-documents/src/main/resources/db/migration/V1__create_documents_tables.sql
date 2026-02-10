CREATE TABLE IF NOT EXISTS documents (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    proprietaire_id UUID NOT NULL,
    type_proprietaire VARCHAR(30) NOT NULL,
    nom_fichier VARCHAR(300) NOT NULL,
    nom_original VARCHAR(300) NOT NULL,
    type_document VARCHAR(50) NOT NULL,
    content_type VARCHAR(100),
    taille BIGINT,
    chemin_stockage VARCHAR(500),
    checksum VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);
CREATE INDEX idx_doc_prop ON documents(proprietaire_id);
CREATE INDEX idx_doc_type ON documents(type_document);
