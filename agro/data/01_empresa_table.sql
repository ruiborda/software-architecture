CREATE TABLE empresa
(
    id                              uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    id_anonimo_emp                  varchar(255),
    ciiu                            int4,
    descciiu                        varchar(255),
    tamanio_emp                     varchar(255),
    fec_creacion                    varchar(255),
    departamento                    varchar(255),
    distrito                        varchar(255),
    valor_estimado_maximo_fob_dolar float8,
    valor_estimado_maximo_venta     float8,
    valor_estimado_minimo_fob_dolar float8,
    valor_estimado_minimo_venta     float8,
    exporta                         varchar(255),
    provincia                       varchar(255),
    sector                          varchar(255),
    ubigeo                          varchar(255),
    anio                            int4
);