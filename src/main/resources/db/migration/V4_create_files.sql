
CREATE TYPE file_type as ENUM(
    'PDF',
    'JPEG',
    'PNG',
    'STL'
    );

CREATE TYPE file_purpose as ENUM(
    'BRACKET',
    'AVATAR',
    'BRACER',
    'GALLERY',
    'TREATMENT_PLAN',
    'ALIGNER',
    'SCAN',
    'XRAY'
    );

CREATE TYPE file_transfer_status as ENUM(
    'DOWNLOADED',
    'UPLOADED'
    );

CREATE TABLE files(
    id UUID PRIMARY KEY ,
    appointment_id UUID NULL ,
    user_id UUID NULL ,
    file_name VARCHAR NOT NULL ,
    storage_path VARCHAR NOT NULL ,
    type file_type NOT NULL ,
    purpose file_purpose NOT NULL ,
    size BIGINT NOT NULL ,
    uploaded_by  UUID NULL ,
    uploaded_at TIMESTAMP NOT NULL
);

CREATE TABLE file_transfer(
    id UUID  NOT NULL PRIMARY KEY ,
    file_id UUID NOT NULL,
    sender_id UUID NOT NULL ,
    receiver_id UUID NOT NULL ,
    status file_transfer_status NOT NULL ,
    sent_at TIMESTAMP NOT NULL ,
    downloaded_at TIMESTAMP NULL ,
    expires_at TIMESTAMP NOT NULL
)