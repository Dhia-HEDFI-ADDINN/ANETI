#!/bin/bash
set -e

# Create multiple databases for ANETI microservices
DATABASES="aneti_inscription aneti_profilage aneti_accompagnement aneti_offres aneti_pae aneti_rdv aneti_notification aneti_journalisation aneti_documents aneti_administration aneti_referentiel aneti_matching aneti_mandatement aneti_prospection aneti_evenements aneti_reclamations aneti_reporting aneti_keycloak"

for db in $DATABASES; do
    echo "Creating database: $db"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
        SELECT 'CREATE DATABASE $db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '$db')\gexec
        GRANT ALL PRIVILEGES ON DATABASE $db TO $POSTGRES_USER;
EOSQL
done

echo "All ANETI databases created successfully!"
