# MedicalWebsite (Umid Medclinic)

Backend plus a simple static frontend for a dental clinic. Patients book appointments. Doctors manage schedules, medical records, Form 043 (outpatient dental card) and files such as X-rays, scans and treatment plans.

## Stack

- Java 21, Spring Boot 4.1 (webmvc, security, data-jpa, validation, jooq, liquibase)
- PostgreSQL 16, schema managed by Liquibase (`src/main/resources/db`)
- JWT (jjwt 0.12), BCrypt password hashing
- MapStruct + Lombok
- Frontend: `src/main/resources/static/*.html|js|css`, served by Spring

## Running locally

Requirements: JDK 21 and a local PostgreSQL on `localhost:5432`.

1. Create the database (once):
   ```
   psql -U postgres -h localhost -c "CREATE DATABASE medicalwebsite_db"
   ```
2. Start the app (PowerShell):
   ```
   $env:JAVA_HOME="C:\Program Files\Microsoft\jdk-21.0.12.101-hotspot"
   $env:DB_PASSWORD="<your local postgres password>"
   .\mvnw.cmd spring-boot:run "-Djooq.codegen.skip=true"
   ```
3. Open http://localhost:8080. Liquibase creates the tables and test data on first startup.

### Gotchas

- **DB password:** `application.yml` reads `${DB_PASSWORD:java}`. Without the env var it falls back to `java`.
- **jOOQ codegen** (`pom.xml`) connects to the DB at build time with a hardcoded `postgres`/`java` login. The generated classes (`com.example.generated`) are not used anywhere in the code, so always build and run with `-Djooq.codegen.skip=true`.
- **`storageLocation`** (`application.yml`) is the folder for uploaded files. It currently points to a path on another machine, so set it to a local folder before testing uploads.
- **Server errors often come back as 403.** Exceptions are forwarded to `/error`, and `/error` is not permitted in `SecurityConfiguration`. Check the server log for the real cause.

## Test data (dev seed)

`db/seed/dev_seed_data.sql` (`context:dev`) fills every table with test data except `refresh_token`. IDs are fixed (`00000000-0000-0000-XXXX-...`).

| Role | Email | Password |
|---|---|---|
| ADMIN | admin@medclinic.test | Admin12345! |
| DOCTOR | doctor1..3@medclinic.test (doctor3 is DEACTIVATED) | Test12345! |
| PATIENT | patient1..5@medclinic.test (patient5 is DEACTIVATED) | Test12345! |

If no Liquibase context is set, the seed runs in **every environment**. In production, set `spring.liquibase.contexts=prod`.
The `storage_path` values in the seeded files are placeholders; those files do not exist on disk.

## Code structure (`com.pm.medicalwebsite`)

A request passes through these layers: `controller` → `usecase` → `datasource` → `dao` → `repository` (Spring Data JPA) → `entity`.

- `dto/requestdtos`, `dto/responsedtos`, `dto/filters`: record DTOs
- `mapper`: MapStruct mappers
- `specifications`: JPA Specifications for filtering
- `enums`: domain enums. **They must match the PostgreSQL ENUM types in the DB.**
- `security`: JWT filter, `UserDetailsService`, `SecurityConfiguration` (roles and access rules)
- `exceptions`: `GlobalExceptionHandler`, `ApiProblem`

Login: `POST /auth/login {email, password}` returns `{accessToken, refreshToken, userId}`. Send `Authorization: Bearer <accessToken>` on later requests.

Main endpoints: `/auth/*`, `/users`, `/services`, `/appointments` (+ `/medical-records`), `/form043`, `/schedules`, `/files`. Access rules live in `SecurityConfiguration`: DELETE is ADMIN only, and most GET and POST endpoints are open to ADMIN and DOCTOR.

## DB / migration rules

- Migrations are `db/migration/V*.sql` files, listed in `db/changelog/db.changelog-master.yaml`.
- **Do not edit migrations that have already been applied (V1..V8)**, or Liquibase fails with a checksum error. For a change, add a new `V9_...sql` file and include it in the master changelog.
- Entity fields mapped to PostgreSQL ENUM columns need `@Enumerated(EnumType.STRING)` + `@JdbcTypeCode(SqlTypes.NAMED_ENUM)`.
- IDs are UUIDs (`GenerationType.UUID`).

Change history: [docs/changes-2026-10-21.md](docs/changes-2026-10-21.md)
