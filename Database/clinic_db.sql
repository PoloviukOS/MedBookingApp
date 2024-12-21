CREATE DATABASE clinic_db;
\c clinic_db;

CREATE TABLE doctors (
    id SERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    full_name VARCHAR(255) NOT NULL,
    specialization VARCHAR(50) NOT NULL CHECK (specialization IN ('TERAPEVT', 'LOR'))
);
CREATE TABLE patients (
    id SERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    phone_number VARCHAR(20)
);
CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    doctor_id INT NOT NULL REFERENCES doctors(id) ON DELETE CASCADE,
    patient_id INT REFERENCES patients(id) ON DELETE SET NULL,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
);

INSERT INTO doctors (full_name, specialization) VALUES ('John Smith', 'TERAPEVT'), ('Peter Johnson', 'LOR');
INSERT INTO patients (full_name, birth_date, phone_number) VALUES ('Mary Peterson', '1990-05-12', '+12345678901'), ('Alex Brown', '1985-07-20', '+12345678902'), ('Elena Davis', '1995-02-15', '+12345678903'), ('Victor Miller', '2000-10-30', '+12345678904');
INSERT INTO tickets (doctor_id, patient_id, date, start_time, end_time) VALUES (1, 1, '2024-12-20', '09:00', '09:30'), (1, 2, '2024-12-20', '09:30', '10:00'), (1, 3, '2024-12-20', '10:00', '10:30'), (2, 4, '2024-12-21', '11:00', '11:30'), (2, NULL, '2024-12-21', '11:30', '12:00'), (2, 2, '2024-12-21', '12:00', '12:30');
