CREATE TYPE appointment_status as ENUM (
    'SCHEDULED',
    'COMPLETED',
    'MISSED',
    'CANCELLED'
    );

CREATE TABLE appointments
(
    id               pg_catalog.uuid PRIMARY KEY,
    doctor_id        pg_catalog.uuid    NOT NULL,
    patient_id       pg_catalog.uuid    NOT NULL,
    service_id       pg_catalog.uuid    NOT NULL,
    appointment_date TIMESTAMP          NOT NULL,
    status           appointment_status NOT NULL,
    created_at       TIMESTAMP          NOT NULL DEFAULT now()
);

CREATE TABLE medical_records
(
    id             pg_catalog.uuid PRIMARY KEY,
    appointment_id pg_catalog.uuid NOT NULL,
    diagnosis      VARCHAR,
    treatment      VARCHAR,
    comment        VARCHAR,
    created_at     TIMESTAMP       NOT NULL DEFAULT now()
)