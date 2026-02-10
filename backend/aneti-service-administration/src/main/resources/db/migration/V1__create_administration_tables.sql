CREATE TABLE IF NOT EXISTS utilisateurs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    keycloak_id VARCHAR(100) UNIQUE,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(200) UNIQUE,
    telephone VARCHAR(20),
    type_utilisateur VARCHAR(30) NOT NULL,
    beti_id UUID,
    direction_id UUID,
    fonction VARCHAR(200),
    matricule VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS betis (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(20) UNIQUE NOT NULL,
    nom VARCHAR(200) NOT NULL,
    nom_ar VARCHAR(200),
    gouvernorat VARCHAR(100) NOT NULL,
    delegation VARCHAR(100),
    adresse VARCHAR(500),
    telephone VARCHAR(20),
    email VARCHAR(200),
    type_bureau VARCHAR(50),
    region VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_user_keycloak ON utilisateurs(keycloak_id);
CREATE INDEX idx_user_email ON utilisateurs(email);
CREATE INDEX idx_user_type ON utilisateurs(type_utilisateur);
CREATE INDEX idx_user_beti ON utilisateurs(beti_id);
CREATE INDEX idx_beti_code ON betis(code);
CREATE INDEX idx_beti_gouv ON betis(gouvernorat);
