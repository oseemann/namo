--
-- Namo PostgreSQL database schema.
--

BEGIN;

CREATE SCHEMA namo;

CREATE TABLE namo.realm (
    realm_id TEXT PRIMARY KEY,
    secret_crypt TEXT NOT NULL
);

CREATE TABLE namo.domain (
    name TEXT PRIMARY KEY
);

CREATE TABLE namo.subdomain (
    subdomain_id SERIAL PRIMARY KEY,
    domain TEXT NOT NULL REFERENCES namo.domain (name),
    name TEXT NOT NULL,
    realm_id TEXT NOT NULL REFERENCES namo.realm (realm_id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
    last_update TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
    UNIQUE(domain, name)
);

CREATE OR REPLACE FUNCTION namo.create_realm()
RETURNS TABLE(realmId text, realmSecret text) AS $$
DECLARE
    secret TEXT;
    stored_id TEXT;
BEGIN
    SELECT upper(encode(gen_random_bytes(24), 'hex')) INTO secret;
    INSERT INTO namo.realm (realm_id, secret_crypt)
        VALUES (upper(encode(gen_random_bytes(8),  'hex')),
                crypt(secret, gen_salt('bf')))
        RETURNING realm_id INTO stored_id;
    RETURN QUERY SELECT stored_id, secret;
END;
$$ LANGUAGE plpgsql;

COMMIT;

