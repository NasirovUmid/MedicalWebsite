
CREATE TYPE user_role as ENUM(
    'PATIENT',
    'DOCTOR',
    'ADMIN'
    );

CREATE TYPE user_status as ENUM(
    'ACTIVE',
    'DEACTIVATED'
    );

CREATE TABLE users(
    id pg_catalog.uuid PRIMARY KEY ,
    full_name VARCHAR NOT NULL ,
    email VARCHAR NOT NULL UNIQUE ,
    password VARCHAR NOT NULL ,
    phone_number VARCHAR NOT NULL UNIQUE ,
    avatar_id pg_catalog.uuid NULL ,
    birthdate TIMESTAMP NOT NULL ,
    role user_role NOT NULL,
    status user_status NOT NULL,
    deleted_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT now()
)

