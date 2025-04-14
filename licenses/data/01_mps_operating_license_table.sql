CREATE TABLE mps_operating_license
(
    id                uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    cod_licencia      integer,
    monto             numeric(10, 2),
    area              bigint,
    f_licencia        bigint,
    estado            varchar(20),
    num_contribuyente varchar(20),
    num_licencia      varchar(20),
    ubigeo            varchar(20),
    departamento      varchar(100),
    distrito          varchar(100),
    provincia         varchar(100),
    fecha_corte       varchar(255),
    giro              text
);
