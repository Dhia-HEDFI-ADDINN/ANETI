CREATE TABLE IF NOT EXISTS referentiels (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(100) NOT NULL,
    code VARCHAR(50) NOT NULL,
    libelle_fr VARCHAR(300) NOT NULL,
    libelle_ar VARCHAR(300),
    description VARCHAR(1000),
    parent_code VARCHAR(50),
    ordre INTEGER,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    UNIQUE(type, code)
);
CREATE INDEX idx_ref_type ON referentiels(type);
CREATE INDEX idx_ref_code ON referentiels(code);
CREATE INDEX idx_ref_parent ON referentiels(parent_code);

-- Seed data: Gouvernorats
INSERT INTO referentiels (id, type, code, libelle_fr, libelle_ar, ordre) VALUES
(gen_random_uuid(), 'GOUVERNORAT', 'TUN', 'Tunis', 'تونس', 1),
(gen_random_uuid(), 'GOUVERNORAT', 'ARI', 'Ariana', 'أريانة', 2),
(gen_random_uuid(), 'GOUVERNORAT', 'BNA', 'Ben Arous', 'بن عروس', 3),
(gen_random_uuid(), 'GOUVERNORAT', 'MAN', 'Manouba', 'منوبة', 4),
(gen_random_uuid(), 'GOUVERNORAT', 'NAB', 'Nabeul', 'نابل', 5),
(gen_random_uuid(), 'GOUVERNORAT', 'ZAG', 'Zaghouan', 'زغوان', 6),
(gen_random_uuid(), 'GOUVERNORAT', 'BIZ', 'Bizerte', 'بنزرت', 7),
(gen_random_uuid(), 'GOUVERNORAT', 'BEJ', 'Béja', 'باجة', 8),
(gen_random_uuid(), 'GOUVERNORAT', 'JEN', 'Jendouba', 'جندوبة', 9),
(gen_random_uuid(), 'GOUVERNORAT', 'KEF', 'Le Kef', 'الكاف', 10),
(gen_random_uuid(), 'GOUVERNORAT', 'SIL', 'Siliana', 'سليانة', 11),
(gen_random_uuid(), 'GOUVERNORAT', 'SOU', 'Sousse', 'سوسة', 12),
(gen_random_uuid(), 'GOUVERNORAT', 'MON', 'Monastir', 'المنستير', 13),
(gen_random_uuid(), 'GOUVERNORAT', 'MAH', 'Mahdia', 'المهدية', 14),
(gen_random_uuid(), 'GOUVERNORAT', 'SFA', 'Sfax', 'صفاقس', 15),
(gen_random_uuid(), 'GOUVERNORAT', 'KAI', 'Kairouan', 'القيروان', 16),
(gen_random_uuid(), 'GOUVERNORAT', 'KAS', 'Kasserine', 'القصرين', 17),
(gen_random_uuid(), 'GOUVERNORAT', 'SBZ', 'Sidi Bouzid', 'سيدي بوزيد', 18),
(gen_random_uuid(), 'GOUVERNORAT', 'GAB', 'Gabès', 'قابس', 19),
(gen_random_uuid(), 'GOUVERNORAT', 'MED', 'Médenine', 'مدنين', 20),
(gen_random_uuid(), 'GOUVERNORAT', 'TAT', 'Tataouine', 'تطاوين', 21),
(gen_random_uuid(), 'GOUVERNORAT', 'GFS', 'Gafsa', 'قفصة', 22),
(gen_random_uuid(), 'GOUVERNORAT', 'TOZ', 'Tozeur', 'توزر', 23),
(gen_random_uuid(), 'GOUVERNORAT', 'KEB', 'Kébili', 'قبلي', 24);

-- Seed data: Niveaux d'étude
INSERT INTO referentiels (id, type, code, libelle_fr, libelle_ar, ordre) VALUES
(gen_random_uuid(), 'NIVEAU_ETUDE', 'SANS', 'Sans diplôme', 'بدون شهادة', 1),
(gen_random_uuid(), 'NIVEAU_ETUDE', 'PRIM', 'Primaire', 'ابتدائي', 2),
(gen_random_uuid(), 'NIVEAU_ETUDE', 'SEC', 'Secondaire', 'ثانوي', 3),
(gen_random_uuid(), 'NIVEAU_ETUDE', 'BAC', 'Baccalauréat', 'بكالوريا', 4),
(gen_random_uuid(), 'NIVEAU_ETUDE', 'BTP', 'BTP/BTS', 'إجازة مهنية', 5),
(gen_random_uuid(), 'NIVEAU_ETUDE', 'LIC', 'Licence', 'إجازة', 6),
(gen_random_uuid(), 'NIVEAU_ETUDE', 'MAST', 'Master/Ingénieur', 'ماجستير/مهندس', 7),
(gen_random_uuid(), 'NIVEAU_ETUDE', 'DOCT', 'Doctorat', 'دكتوراه', 8);

-- Seed data: Types de contrat
INSERT INTO referentiels (id, type, code, libelle_fr, libelle_ar, ordre) VALUES
(gen_random_uuid(), 'TYPE_CONTRAT', 'CDI', 'CDI', 'عقد دائم', 1),
(gen_random_uuid(), 'TYPE_CONTRAT', 'CDD', 'CDD', 'عقد محدد المدة', 2),
(gen_random_uuid(), 'TYPE_CONTRAT', 'STAGE', 'Stage', 'تربص', 3),
(gen_random_uuid(), 'TYPE_CONTRAT', 'INTERIM', 'Intérim', 'عمل مؤقت', 4),
(gen_random_uuid(), 'TYPE_CONTRAT', 'FREELANCE', 'Freelance', 'عمل حر', 5);
