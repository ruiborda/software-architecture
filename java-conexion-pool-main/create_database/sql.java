-- Archivo: create_database.sql
-- Descripción: Script para crear la base de datos

-- Conectar a PostgreSQL como usuario postgres o superusuario
-- psql -U postgres

-- Crear la base de datos
CREATE DATABASE mydatabase;

-- Comentario: Si quieres asignar un propietario específico, puedes usar:
        -- CREATE DATABASE mydatabase OWNER miusuario;

------------------------------------------------------------

        -- Archivo: create_tables.sql
-- Descripción: Script para crear las tablas necesarias
-- Para ejecutar: psql -U postgres -d mydatabase -f create_tables.sql

-- Crear la tabla de usuarios
CREATE TABLE IF NOT EXISTS users (
        id SERIAL PRIMARY KEY,
        username VARCHAR(100) NOT NULL UNIQUE,
email VARCHAR(100) NOT NULL
);

        -- Crear índices para búsquedas frecuentes
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);

-- (Opcional) Insertar algunos datos de prueba
INSERT INTO users (username, email) VALUES 
('admin', 'admin@example.com'),
        ('usuario1', 'usuario1@example.com'),
        ('usuario2', 'usuario2@example.com');

        -- Verificar los datos insertados
-- SELECT * FROM users;