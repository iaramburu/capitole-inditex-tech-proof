CREATE OR REPLACE FUNCTION truncate_schema(_schema IN VARCHAR) RETURNS void AS $$
DECLARE
    tables CURSOR FOR
        SELECT table_name
        FROM information_schema.tables
        WHERE table_type = 'BASE TABLE' AND table_schema = _schema;
BEGIN
    FOR _table IN tables LOOP
        EXECUTE 'TRUNCATE TABLE ' || quote_ident(_schema) || '.' || quote_ident(_table.tablename) || ' RESTART IDENTITY CASCADE;';
    END LOOP;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION reset_schema_sequences(_schema IN VARCHAR) RETURNS void AS $$
DECLARE
    sequences CURSOR FOR
        SELECT sequence_name
        FROM information_schema.sequences
        WHERE sequence_schema = _schema;
BEGIN
    FOR _sequence IN sequences LOOP
        EXECUTE 'ALTER SEQUENCE ' || quote_ident(_schema) || '.' || quote_ident(_sequence.sequence_name) || ' RESTART WITH 1;';
    END LOOP;
END;
$$ LANGUAGE plpgsql;
