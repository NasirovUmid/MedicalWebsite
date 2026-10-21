--liquibase formatted sql

-- Test (dev) ma'lumotlari. Faqat lokal testlash uchun.
-- Prod'da ishlamasligi uchun: spring.liquibase.contexts=prod (yoki "dev"dan boshqa context) bering.
--
-- Loginlar:
--   ADMIN   : admin@medclinic.test        / Admin12345!
--   DOCTOR  : doctor1..3@medclinic.test   / Test12345!
--   PATIENT : patient1..5@medclinic.test  / Test12345!
--
-- Eslatma: seed V8_align_enums_with_java.sql'dan keyin ishlaydi; enum qiymatlari Java enumlari bilan mos.

--changeset seed:1 context:dev
INSERT INTO users (id, full_name, email, password, phone_number, avatar_id, birthdate, role, status, deleted_at, created_at)
VALUES
    ('00000000-0000-0000-0000-000000000001', 'Admin Adminov',     'admin@medclinic.test',    '$2a$10$v3tu.O/cT8DEDyffxfT.YecJIUFCKL0ebtEZEoYITlAAAsbYErvke', '+998900000001', NULL, '1990-01-15', 'ADMIN',   'ACTIVE',      NULL, now()),
    ('00000000-0000-0000-0000-000000000011', 'Umid Karimov',      'doctor1@medclinic.test',  '$2a$10$f2VXsVktYeEo9xNg.8O/quswYWRU0cfOtQHGb0noGb1feEtm9Vw/2', '+998900000011', NULL, '1985-03-20', 'DOCTOR',  'ACTIVE',      NULL, now()),
    ('00000000-0000-0000-0000-000000000012', 'Dilnoza Rahimova',  'doctor2@medclinic.test',  '$2a$10$f2VXsVktYeEo9xNg.8O/quswYWRU0cfOtQHGb0noGb1feEtm9Vw/2', '+998900000012', NULL, '1988-07-11', 'DOCTOR',  'ACTIVE',      NULL, now()),
    ('00000000-0000-0000-0000-000000000013', 'Bekzod Tursunov',   'doctor3@medclinic.test',  '$2a$10$f2VXsVktYeEo9xNg.8O/quswYWRU0cfOtQHGb0noGb1feEtm9Vw/2', '+998900000013', NULL, '1979-11-02', 'DOCTOR',  'DEACTIVATED', NULL, now()),
    ('00000000-0000-0000-0000-000000000021', 'Aziz Nazarov',      'patient1@medclinic.test', '$2a$10$f2VXsVktYeEo9xNg.8O/quswYWRU0cfOtQHGb0noGb1feEtm9Vw/2', '+998900000021', NULL, '2001-05-09', 'PATIENT', 'ACTIVE',      NULL, now()),
    ('00000000-0000-0000-0000-000000000022', 'Malika Yusupova',   'patient2@medclinic.test', '$2a$10$f2VXsVktYeEo9xNg.8O/quswYWRU0cfOtQHGb0noGb1feEtm9Vw/2', '+998900000022', NULL, '1997-09-23', 'PATIENT', 'ACTIVE',      NULL, now()),
    ('00000000-0000-0000-0000-000000000023', 'Jasur Aliyev',      'patient3@medclinic.test', '$2a$10$f2VXsVktYeEo9xNg.8O/quswYWRU0cfOtQHGb0noGb1feEtm9Vw/2', '+998900000023', NULL, '2010-02-14', 'PATIENT', 'ACTIVE',      NULL, now()),
    ('00000000-0000-0000-0000-000000000024', 'Nodira Saidova',    'patient4@medclinic.test', '$2a$10$f2VXsVktYeEo9xNg.8O/quswYWRU0cfOtQHGb0noGb1feEtm9Vw/2', '+998900000024', NULL, '1993-12-30', 'PATIENT', 'ACTIVE',      NULL, now()),
    ('00000000-0000-0000-0000-000000000025', 'Sherzod Qodirov',   'patient5@medclinic.test', '$2a$10$f2VXsVktYeEo9xNg.8O/quswYWRU0cfOtQHGb0noGb1feEtm9Vw/2', '+998900000025', NULL, '1980-06-06', 'PATIENT', 'DEACTIVATED', now() - INTERVAL '10 days', now());

INSERT INTO services (id, type, status, price, duration)
VALUES
    ('00000000-0000-0000-0001-000000000001', 'CONSULTATION', 'ACTIVE',      150000,  30),
    ('00000000-0000-0000-0001-000000000002', 'ALIGNER',      'ACTIVE',      5000000, 60),
    ('00000000-0000-0000-0001-000000000003', 'ALIGNER',      'ACTIVE',      3500000, 45),
    ('00000000-0000-0000-0001-000000000004', 'CONSULTATION', 'DEACTIVATED', 100000,  20);

INSERT INTO appointments (id, doctor_id, patient_id, service_id, appointment_date, status, created_at)
VALUES
    ('00000000-0000-0000-0002-000000000001', '00000000-0000-0000-0000-000000000011', '00000000-0000-0000-0000-000000000021', '00000000-0000-0000-0001-000000000001', date_trunc('hour', now()) - INTERVAL '20 days', 'COMPLETED', now() - INTERVAL '25 days'),
    ('00000000-0000-0000-0002-000000000002', '00000000-0000-0000-0000-000000000011', '00000000-0000-0000-0000-000000000021', '00000000-0000-0000-0001-000000000002', date_trunc('hour', now()) - INTERVAL '5 days',  'COMPLETED', now() - INTERVAL '10 days'),
    ('00000000-0000-0000-0002-000000000003', '00000000-0000-0000-0000-000000000012', '00000000-0000-0000-0000-000000000022', '00000000-0000-0000-0001-000000000001', date_trunc('hour', now()) - INTERVAL '3 days',  'MISSED',    now() - INTERVAL '7 days'),
    ('00000000-0000-0000-0002-000000000004', '00000000-0000-0000-0000-000000000012', '00000000-0000-0000-0000-000000000023', '00000000-0000-0000-0001-000000000003', date_trunc('hour', now()) - INTERVAL '1 day',   'CANCELLED', now() - INTERVAL '4 days'),
    ('00000000-0000-0000-0002-000000000005', '00000000-0000-0000-0000-000000000011', '00000000-0000-0000-0000-000000000022', '00000000-0000-0000-0001-000000000002', date_trunc('hour', now()) + INTERVAL '2 days',  'SCHEDULED', now() - INTERVAL '1 day'),
    ('00000000-0000-0000-0002-000000000006', '00000000-0000-0000-0000-000000000012', '00000000-0000-0000-0000-000000000024', '00000000-0000-0000-0001-000000000001', date_trunc('hour', now()) + INTERVAL '3 days',  'SCHEDULED', now()),
    ('00000000-0000-0000-0002-000000000007', '00000000-0000-0000-0000-000000000011', '00000000-0000-0000-0000-000000000023', '00000000-0000-0000-0001-000000000001', date_trunc('hour', now()) + INTERVAL '7 days',  'SCHEDULED', now()),
    ('00000000-0000-0000-0002-000000000008', '00000000-0000-0000-0000-000000000012', '00000000-0000-0000-0000-000000000021', '00000000-0000-0000-0001-000000000003', date_trunc('hour', now()) - INTERVAL '40 days', 'COMPLETED', now() - INTERVAL '45 days');

INSERT INTO medical_records (id, appointment_id, diagnosis, treatment, comment, created_at)
VALUES
    ('00000000-0000-0000-0003-000000000001', '00000000-0000-0000-0002-000000000001', 'Tishlar qiyshiqligi (malokklyuziya II sinf)', 'Aligner terapiyasi tavsiya etildi', 'Birinchi konsultatsiya, skan qilish kerak', now() - INTERVAL '20 days'),
    ('00000000-0000-0000-0003-000000000002', '00000000-0000-0000-0002-000000000002', 'Malokklyuziya II sinf',                       'Aligner 1-bosqich berildi',          '2 haftadan keyin nazorat',               now() - INTERVAL '5 days'),
    ('00000000-0000-0000-0003-000000000003', '00000000-0000-0000-0002-000000000008', 'Tish toshi',                                  'Professional gigiyena',              NULL,                                     now() - INTERVAL '40 days');

INSERT INTO files (id, appointment_id, user_id, file_name, storage_path, type, purpose, size, uploaded_by, uploaded_at)
VALUES
    ('00000000-0000-0000-0004-000000000001', '00000000-0000-0000-0002-000000000001', '00000000-0000-0000-0000-000000000021', 'xray_patient1.pdf',          'seed/xray_patient1.pdf',          'PDF', 'XRAY',           245760,  '00000000-0000-0000-0000-000000000011', now() - INTERVAL '20 days'),
    ('00000000-0000-0000-0004-000000000002', '00000000-0000-0000-0002-000000000002', '00000000-0000-0000-0000-000000000021', 'treatment_plan_patient1.pdf', 'seed/treatment_plan_patient1.pdf', 'PDF', 'TREATMENT_PLAN', 102400,  '00000000-0000-0000-0000-000000000011', now() - INTERVAL '5 days'),
    ('00000000-0000-0000-0004-000000000003', '00000000-0000-0000-0002-000000000008', '00000000-0000-0000-0000-000000000021', 'scan_patient1.pdf',          'seed/scan_patient1.pdf',          'PDF', 'SCAN',           1048576, '00000000-0000-0000-0000-000000000012', now() - INTERVAL '40 days'),
    ('00000000-0000-0000-0004-000000000004', NULL,                                   '00000000-0000-0000-0000-000000000022', 'aligner_patient2.pdf',       'seed/aligner_patient2.pdf',       'PDF', 'ALIGNER',        512000,  '00000000-0000-0000-0000-000000000012', now() - INTERVAL '2 days');

INSERT INTO file_transfer (id, file_id, sender_id, receiver_id, status, sent_at, downloaded_at, expires_at)
VALUES
    ('00000000-0000-0000-0005-000000000001', '00000000-0000-0000-0004-000000000002', '00000000-0000-0000-0000-000000000011', '00000000-0000-0000-0000-000000000021', 'DOWNLOADED', now() - INTERVAL '5 days', now() - INTERVAL '4 days', now() + INTERVAL '25 days'),
    ('00000000-0000-0000-0005-000000000002', '00000000-0000-0000-0004-000000000004', '00000000-0000-0000-0000-000000000012', '00000000-0000-0000-0000-000000000022', 'UPLOADED',   now() - INTERVAL '2 days', NULL,                      now() + INTERVAL '5 days');

INSERT INTO form_043 (id, user_id, card_number, observation_start_date, observation_end_date, full_name, birth_date, address, study_or_workplace,
                      main_diagnosis, concomitant_disease, allergic_history, complaints, history_of_present_illness, personal_history,
                      external_examination, oral_cavity_and_teeth, condition_of_oral_mucosa_periodontium, radiological_and_other_examinations,
                      date, complaints_objectively, diagnosis_or_treatment)
VALUES
    ('00000000-0000-0000-0006-000000000001', '00000000-0000-0000-0000-000000000021', 'KT-0001', now() - INTERVAL '20 days', now() + INTERVAL '160 days',
     'Aziz Nazarov', '2001-05-09', 'Toshkent sh., Chilonzor t., 5-kvartal, 12-uy', 'TATU talabasi',
     'Malokklyuziya II sinf', 'Yo''q', 'Penitsillinga allergiya', 'Tishlar qiyshiqligi, chaynashda noqulaylik', '3 yildan beri kuzatiladi', 'Chekmaydi',
     'Yuz simmetrik', 'Yuqori jag''da tishlar zich joylashgan', 'Shilliq qavat sog''lom', 'OPTG: patologiya aniqlanmadi',
     now() - INTERVAL '20 days', 'Tishlar qiyshiq, prikus buzilgan', 'Aligner terapiyasi, 6 oy'),
    ('00000000-0000-0000-0006-000000000002', '00000000-0000-0000-0000-000000000022', 'KT-0002', now() - INTERVAL '3 days', now() + INTERVAL '90 days',
     'Malika Yusupova', '1997-09-23', 'Toshkent sh., Yunusobod t., 19-kvartal, 7-uy', 'Bank xodimi',
     'Tish toshi', 'Gastrit', 'Yo''q', 'Milkdan qon ketishi', '1 oydan beri', 'Ko''p kofe ichadi',
     'O''zgarishsiz', 'Pastki kesuvchi tishlarda tish toshi', 'Gingivit belgilari', 'Rentgen talab qilinmadi',
     now() - INTERVAL '3 days', 'Milk qizargan, tish toshi bor', 'Professional gigiyena, chayish');

INSERT INTO schedules (id, doctor_id, day_of_week, start_time, end_time, active)
VALUES
    ('00000000-0000-0000-0007-000000000001', '00000000-0000-0000-0000-000000000011', 'MONDAY',    '09:00', '18:00', TRUE),
    ('00000000-0000-0000-0007-000000000002', '00000000-0000-0000-0000-000000000011', 'TUESDAY',   '09:00', '18:00', TRUE),
    ('00000000-0000-0000-0007-000000000003', '00000000-0000-0000-0000-000000000011', 'WEDNESDAY', '09:00', '18:00', TRUE),
    ('00000000-0000-0000-0007-000000000004', '00000000-0000-0000-0000-000000000011', 'THURSDAY',  '09:00', '18:00', TRUE),
    ('00000000-0000-0000-0007-000000000005', '00000000-0000-0000-0000-000000000011', 'FRIDAY',    '09:00', '18:00', TRUE),
    ('00000000-0000-0000-0007-000000000006', '00000000-0000-0000-0000-000000000011', 'SATURDAY',  '10:00', '14:00', FALSE),
    ('00000000-0000-0000-0007-000000000007', '00000000-0000-0000-0000-000000000012', 'MONDAY',    '13:00', '20:00', TRUE),
    ('00000000-0000-0000-0007-000000000008', '00000000-0000-0000-0000-000000000012', 'WEDNESDAY', '13:00', '20:00', TRUE),
    ('00000000-0000-0000-0007-000000000009', '00000000-0000-0000-0000-000000000012', 'FRIDAY',    '13:00', '20:00', TRUE),
    ('00000000-0000-0000-0007-000000000010', '00000000-0000-0000-0000-000000000012', 'SATURDAY',  '10:00', '15:00', TRUE);

INSERT INTO schedules_exceptions (id, doctor_id, exception_date, start_time, end_time, is_day_off)
VALUES
    ('00000000-0000-0000-0008-000000000001', '00000000-0000-0000-0000-000000000011', current_date + 10, NULL,    NULL,    TRUE),
    ('00000000-0000-0000-0008-000000000002', '00000000-0000-0000-0000-000000000011', current_date + 4,  '12:00', '16:00', FALSE),
    ('00000000-0000-0000-0008-000000000003', '00000000-0000-0000-0000-000000000012', current_date + 6,  NULL,    NULL,    TRUE);
