CREATE TYPE service_type as ENUM (
    'ALIGNER',
    'BRACER',
    'SCAN',
    'CONSULTATION'
    );

CREATE TYPE service_status as ENUM (
    'ACTIVE',
    'DEACTIVATED'
    );

CREATE TABLE services
(
    id       UUID PRIMARY KEY,
    type     service_type     NOT NULL,
    status   service_status   NOT NULL DEFAULT 'ACTIVE',
    price    DOUBLE PRECISION NOT NULL,
    duration INTEGER          NOT NULL
)