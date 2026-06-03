CREATE DATABASE IF NOT EXISTS bd_rednorte;
USE bd_rednorte;

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rut VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol_id BIGINT NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS pacientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rut VARCHAR(20) NOT NULL UNIQUE,
    edad INT,
    sexo VARCHAR(20),
    fecha_nacimiento DATE,
    comuna VARCHAR(100),
    enfermedad_cronica VARCHAR(150),
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS centros_asistenciales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    comuna VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS especialidades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS listas_espera (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    especialidad_id BIGINT NOT NULL,
    centro_id BIGINT NOT NULL,
    motivo_atencion VARCHAR(255),
    prioridad VARCHAR(50),
    estado VARCHAR(50) DEFAULT 'EN_ESPERA',
    fecha_ingreso DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (especialidad_id) REFERENCES especialidades(id),
    FOREIGN KEY (centro_id) REFERENCES centros_asistenciales(id)
);

CREATE TABLE IF NOT EXISTS horas_medicas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT,
    especialidad_id BIGINT NOT NULL,
    centro_id BIGINT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    estado VARCHAR(50) DEFAULT 'DISPONIBLE',
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (especialidad_id) REFERENCES especialidades(id),
    FOREIGN KEY (centro_id) REFERENCES centros_asistenciales(id)
);

CREATE TABLE IF NOT EXISTS diagnosticos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);

CREATE TABLE IF NOT EXISTS ordenes_medicas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_emision DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);

INSERT INTO roles (nombre) VALUES ('PACIENTE'), ('MEDICO'), ('ADMIN');