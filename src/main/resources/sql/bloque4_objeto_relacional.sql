-- ============================================
-- BLOQUE 4 - BASES DE DATOS OBJETO-RELACIONALES
-- PostgreSQL
-- ============================================

-- Eliminar estructuras si existen
DROP TABLE IF EXISTS personas_or;
DROP TYPE IF EXISTS direccion_tipo;

-- Tipo compuesto
CREATE TYPE direccion_tipo AS (
    calle VARCHAR(100),
    ciudad VARCHAR(100),
    codigo_postal VARCHAR(10)
);

-- Tabla objeto-relacional
CREATE TABLE personas_or (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion direccion_tipo,
    telefonos TEXT[]
);

-- Datos iniciales de prueba
INSERT INTO personas_or (
    nombre,
    direccion,
    telefonos
)
VALUES (
    'Laura',
    ROW(
        'Avenida de Cervantes',
        'Granada',
        '18008'
    ),
    ARRAY[
        '600111222',
        '600333444'
    ]
);