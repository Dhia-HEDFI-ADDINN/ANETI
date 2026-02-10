CREATE TABLE IF NOT EXISTS plans_accompagnement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chercheur_emploi_id UUID NOT NULL,
    conseiller_id UUID NOT NULL,
    segment VARCHAR(30),
    objectif_principal VARCHAR(500),
    date_debut DATE,
    date_fin_prevue DATE,
    date_fin_effective DATE,
    statut VARCHAR(20) NOT NULL DEFAULT 'BROUILLON',
    bilan VARCHAR(2000),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS actions_accompagnement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    plan_id UUID NOT NULL REFERENCES plans_accompagnement(id) ON DELETE CASCADE,
    type_action VARCHAR(50) NOT NULL,
    description VARCHAR(1000),
    date_debut DATE,
    date_fin_prevue DATE,
    date_realisation DATE,
    statut VARCHAR(20) NOT NULL DEFAULT 'PLANIFIEE',
    resultat VARCHAR(1000),
    commentaire VARCHAR(1000),
    priorite INTEGER,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS suivis_accompagnement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chercheur_emploi_id UUID NOT NULL,
    conseiller_id UUID NOT NULL,
    plan_id UUID,
    type_suivi VARCHAR(50) NOT NULL,
    canal VARCHAR(30),
    date_suivi TIMESTAMP NOT NULL,
    compte_rendu VARCHAR(3000),
    actions_decidees VARCHAR(2000),
    prochain_rdv TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX idx_plan_ce ON plans_accompagnement(chercheur_emploi_id);
CREATE INDEX idx_plan_conseiller ON plans_accompagnement(conseiller_id);
CREATE INDEX idx_plan_statut ON plans_accompagnement(statut);
CREATE INDEX idx_action_plan ON actions_accompagnement(plan_id);
CREATE INDEX idx_suivi_ce ON suivis_accompagnement(chercheur_emploi_id);
CREATE INDEX idx_suivi_conseiller ON suivis_accompagnement(conseiller_id);
