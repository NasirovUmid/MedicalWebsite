# MedicalWebsite (Umid Medclinic)

Stomatologiya klinikasi uchun backend + oddiy statik frontend. Bemorlar qabulga yoziladi, doktorlar jadval, tibbiy yozuvlar, 043-shakl (ambulator karta) va fayllarni (rentgen, skan, davolash rejasi) yuritadi.

## Stack

- Java 21, Spring Boot 4.1 (webmvc, security, data-jpa, validation, jooq, liquibase)
- PostgreSQL 16, sxema Liquibase orqali (`src/main/resources/db`)
- JWT (jjwt 0.12), BCrypt parollar
- MapStruct + Lombok
- Frontend: `src/main/resources/static/*.html|js|css` (Spring o'zi beradi)

## Ishga tushirish

Talablar: JDK 21, lokal PostgreSQL (`localhost:5432`).

1. Baza yaratish (bir marta):
   ```
   psql -U postgres -h localhost -c "CREATE DATABASE medicalwebsite_db"
   ```
2. Ilovani ishga tushirish (PowerShell):
   ```
   $env:JAVA_HOME="C:\Program Files\Microsoft\jdk-21.0.12.101-hotspot"
   $env:DB_PASSWORD="<lokal postgres parolingiz>"
   .\mvnw.cmd spring-boot:run "-Djooq.codegen.skip=true"
   ```
3. Ilova: http://localhost:8080. Jadvallar va test ma'lumotlari birinchi ishga tushishda Liquibase orqali yaratiladi.

### Muhim nuanslar

- **DB paroli** `application.yml`da `${DB_PASSWORD:java}`. Env o'zgaruvchi berilmasa `java` ishlatiladi.
- **jOOQ codegen** (`pom.xml`) build vaqtida DB'ga ulanadi va unda `postgres`/`java` qattiq yozilgan. Generatsiya qilingan klasslar (`com.example.generated`) kodda ishlatilmaydi, shuning uchun build va run doim `-Djooq.codegen.skip=true` bilan qilinadi.
- **`storageLocation`** (`application.yml`) fayllar saqlanadigan papka. Hozir boshqa kompyuterdagi yo'lni ko'rsatadi, fayl yuklashdan oldin o'zingizga moslang.
- Server xatolari ko'pincha **403** bo'lib qaytadi: exception `/error`ga forward qilinadi, `/error` esa `SecurityConfiguration`da ochilmagan. Haqiqiy sababni server logidan qidiring.

## Test ma'lumotlari (dev seed)

`db/seed/dev_seed_data.sql` (`context:dev`) barcha jadvallarga test ma'lumot qo'shadi (`refresh_token`dan tashqari). ID'lar qat'iy (`00000000-0000-0000-XXXX-...`).

| Rol | Email | Parol |
|---|---|---|
| ADMIN | admin@medclinic.test | Admin12345! |
| DOCTOR | doctor1..3@medclinic.test (doctor3 DEACTIVATED) | Test12345! |
| PATIENT | patient1..5@medclinic.test (patient5 DEACTIVATED) | Test12345! |

Liquibase'da context berilmasa seed **har qanday muhitda** ishlaydi. Prod'da `spring.liquibase.contexts=prod` qo'yish shart.
Seed fayllaridagi `storage_path` qiymatlari o'ylab topilgan, diskda bunday fayllar yo'q.

## Kod tuzilishi (`com.pm.medicalwebsite`)

So'rov qatlamlar bo'ylab shunday o'tadi: `controller` → `usecase` → `datasource` → `dao` → `repository` (Spring Data JPA) → `entity`.

- `dto/requestdtos`, `dto/responsedtos`, `dto/filters`: record DTO'lar
- `mapper`: MapStruct mapperlari
- `specifications`: filtrlash uchun JPA Specification
- `enums`: domen enumlari. **DB'dagi PostgreSQL ENUM tiplari bilan bir xil bo'lishi kerak.**
- `security`: JWT filter, `UserDetailsService`, `SecurityConfiguration` (rollar va ruxsatlar)
- `exceptions`: `GlobalExceptionHandler`, `ApiProblem`

Login: `POST /auth/login {email, password}` → `{accessToken, refreshToken, userId}`. Keyingi so'rovlarda `Authorization: Bearer <accessToken>` header'i yuboriladi.

Asosiy endpointlar: `/auth/*`, `/users`, `/services`, `/appointments` (+ `/medical-records`), `/form043`, `/schedules`, `/files`. Ruxsatlar `SecurityConfiguration`da belgilangan: DELETE faqat ADMIN'ga, ko'p GET va POST'lar ADMIN va DOCTOR'ga ochiq.

## DB / migratsiya qoidalari

- Migratsiyalar `db/migration/V*.sql` fayllarida, ro'yxati esa `db/changelog/db.changelog-master.yaml`da.
- **Qo'llangan migratsiya fayllarini (V1..V8) tahrirlamang**, aks holda Liquibase checksum xatosi beradi. O'zgarish kerak bo'lsa, yangi `V9_...sql` faylini yarating va master changelog'ga qo'shing.
- Entity'larda PostgreSQL ENUM ustunlari uchun `@Enumerated(EnumType.STRING)` + `@JdbcTypeCode(SqlTypes.NAMED_ENUM)` shart.
- ID'lar UUID (`GenerationType.UUID`).

O'zgarishlar tarixi: [docs/changes-2026-10-21.md](docs/changes-2026-10-21.md)
