
CREATE TABLE refresh_token(
    id UUID PRIMARY KEY ,
    user_id UUID NOT NULL ,
    refresh_token VARCHAR NOT NULL ,
    expiry_date TIMESTAMP NOT NULL
)