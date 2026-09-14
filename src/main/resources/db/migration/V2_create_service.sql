CREATE TYPE services_type as ENUM (
    'ALIGNER',
    'BRACER',
    'SCAN',
    'CONSULTATION'
    );

CREATE TYPE services_status as ENUM (
    'ACTIVE',
    'DEACTIVATED'
    );

CREATE TABLE services
(
    id       UUID PRIMARY KEY,
    type     services_type     NOT NULL,
    status   services_status   NOT NULL DEFAULT 'ACTIVE',
    price    DOUBLE PRECISION NOT NULL,
    duration INTEGER          NOT NULL
)