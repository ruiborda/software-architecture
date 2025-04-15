CREATE TABLE positivos_covid
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_persona      BIGINT,
    fecha_corte     VARCHAR(10),
    departamento    VARCHAR(100),
    provincia       VARCHAR(100),
    distrito        VARCHAR(100),
    metododx        VARCHAR(50),
    edad            INTEGER,
    sexo            VARCHAR(20),
    fecha_resultado VARCHAR(10),
    ubigeo          VARCHAR(10)
);
