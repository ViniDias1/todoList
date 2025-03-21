
DO $$ 
DECLARE 
    r RECORD;
BEGIN
    SET session_replication_role = 'replica';

    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
        EXECUTE 'TRUNCATE TABLE ' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;

    FOR r IN (
        SELECT pg_sequences.schemaname, pg_sequences.sequencename 
        FROM pg_sequences 
        WHERE schemaname = 'public'
    ) LOOP
        EXECUTE 'ALTER SEQUENCE ' || quote_ident(r.schemaname) || '.' || quote_ident(r.sequencename) || ' RESTART WITH 1';
    END LOOP;

    SET session_replication_role = 'origin';
END $$;
