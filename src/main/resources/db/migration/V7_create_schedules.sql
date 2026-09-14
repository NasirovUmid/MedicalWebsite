CREATE TYPE day_of_week AS ENUM (
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
    );

CREATE TABLE schedules
(
    id          UUID PRIMARY KEY,
    doctor_id   UUID        NOT NULL,
    day_of_week day_of_week NOT NULL,
    start_time  TIME        NOT NULL,
    end_time    TIME        NOT NULL,
    active      BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE schedules_exceptions
(
    id             UUID PRIMARY KEY,
    doctor_id      UUID    NOT NULL,
    exception_date DATE    NOT NULL,
    start_time     TIME    NULL,
    end_time       TIME    NULL,
    is_day_off     BOOLEAN NOT NULL DEFAULT FALSE
)