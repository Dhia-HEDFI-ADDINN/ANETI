CREATE TABLE IF NOT EXISTS journal_entries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    utilisateur_id UUID,
    utilisateur_nom VARCHAR(200),
    action VARCHAR(100) NOT NULL,
    module VARCHAR(100) NOT NULL,
    entite_type VARCHAR(100),
    entite_id UUID,
    description VARCHAR(2000),
    donnees_avant TEXT,
    donnees_apres TEXT,
    adresse_ip VARCHAR(50),
    user_agent VARCHAR(500),
    date_action TIMESTAMP NOT NULL,
    niveau VARCHAR(20) DEFAULT 'INFO',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);
CREATE INDEX idx_journal_user ON journal_entries(utilisateur_id);
CREATE INDEX idx_journal_module ON journal_entries(module);
CREATE INDEX idx_journal_action ON journal_entries(action);
CREATE INDEX idx_journal_date ON journal_entries(date_action);
CREATE INDEX idx_journal_entite ON journal_entries(entite_type, entite_id);
