-- ============================================
-- BLOQUE 4 - BASES DE DATOS OBJETO-RELACIONALES
-- PostgreSQL
-- ============================================


-- ============================================
-- 4.1 - TIPOS COMPUESTOS Y ARRAYS
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


-- ============================================
-- 4.10 - JSONB
-- ============================================

-- Eliminar tabla si ya existe
DROP TABLE IF EXISTS perfiles_json;


-- Tabla con una columna JSONB
CREATE TABLE perfiles_json (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    datos JSONB
);


-- Documento JSONB inicial
INSERT INTO perfiles_json (
    nombre,
    datos
)
VALUES (
    'Laura',
    '{
        "edad": 25,
        "ciclo": "DAM",
        "direccion": {
            "ciudad": "Granada",
            "cp": "18008"
        },
        "lenguajes": [
            "Java",
            "SQL",
            "Python"
        ]
    }'
);


-- ============================================
-- 4.15 - INDICE GIN PARA JSONB
-- ============================================

CREATE INDEX idx_perfiles_json_datos_gin
ON perfiles_json
USING GIN (datos);