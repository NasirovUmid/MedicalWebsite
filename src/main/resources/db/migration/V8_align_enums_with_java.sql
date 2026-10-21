--liquibase formatted sql

--changeset admin:V8-1
ALTER TYPE services_type RENAME VALUE 'BRACER' TO 'BRACKET';

--changeset admin:V8-2
ALTER TYPE appointment_status ADD VALUE IF NOT EXISTS 'PENDING_PAYMENT';

--changeset admin:V8-3
ALTER TYPE file_type RENAME TO file_type_old;

CREATE TYPE file_type AS ENUM (
    'IMAGE',
    'ARCHIVE',
    'MODEL_3D',
    'DOCUMENT',
    'PDF'
    );

ALTER TABLE files
    ALTER COLUMN type TYPE file_type USING (
        CASE type::text
            WHEN 'JPEG' THEN 'IMAGE'
            WHEN 'PNG' THEN 'IMAGE'
            WHEN 'STL' THEN 'MODEL_3D'
            ELSE type::text
            END
        )::file_type;

DROP TYPE file_type_old;

--changeset admin:V8-4
ALTER TYPE file_purpose RENAME TO file_purpose_old;

CREATE TYPE file_purpose AS ENUM (
    'AVATAR',
    'GALLERY',
    'TREATMENT_PLAN',
    'ALIGNER',
    'BRACKET',
    'SCAN',
    'XRAY'
    );

ALTER TABLE files
    ALTER COLUMN purpose TYPE file_purpose USING (
        CASE purpose::text
            WHEN 'BRACER' THEN 'BRACKET'
            ELSE purpose::text
            END
        )::file_purpose;

DROP TYPE file_purpose_old;
