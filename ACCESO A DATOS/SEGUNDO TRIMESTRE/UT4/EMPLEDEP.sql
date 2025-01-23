CREATE OR REPLACE TYPE T_DEP AS OBJECT (
    DEPT_NO   NUMBER(38),
    DNOMBRE   VARCHAR2(15),
    LOC       VARCHAR2(15)
);
/
DROP TABLE DEPART CASCADE CONSTRAINTS;
CREATE TABLE DEPART (
    DEPT_NO NUMBER PRIMARY KEY,
    DEPT_OBJ T_DEP
);
INSERT INTO DEPART (DEPT_NO, DEPT_OBJ)
SELECT DEPT_NO, T_DEP(DEPT_NO, DNOMBRE, LOC)
FROM DEPARTAMENTOS;


CREATE TABLE EMPLE (
    EMP_NO     NUMBER(38) PRIMARY KEY,
    APELLIDO   VARCHAR2(10),
    OFICIO     VARCHAR2(10),
    DIR        NUMBER(38),
    FECHA_ALT  DATE,
    SALARIO    FLOAT,
    COMISION   FLOAT,
    DEPT_NO    NUMBER(38),
    CONSTRAINT FK_EMPLE_DEPART FOREIGN KEY (DEPT_NO) 
        REFERENCES DEPART (DEPT_NO)
);


INSERT INTO EMPLE (EMP_NO, APELLIDO, OFICIO, DIR, FECHA_ALT, SALARIO, COMISION, DEPT_NO)
SELECT 
    e.EMP_NO,
    e.APELLIDO,
    e.OFICIO,
    e.DIR,
    e.FECHA_ALT,
    e.SALARIO,
    e.COMISION,
    e.DEPT_NO
FROM 
    EMPLEADOS e;


INSERT INTO EMPLE (EMP_NO, APELLIDO, OFICIO, DIR, FECHA_ALT, SALARIO, COMISION, DEPT_NO)
VALUES (
    100, 
    'Nue emple', 
    'Oficio',        -- Reemplaza 'Oficio' con el oficio correspondiente
    NULL,            -- Reemplaza NULL con el director correspondiente si es necesario
    SYSDATE,         -- Fecha de alta actual
    3000,            -- Reemplaza 3000 con el salario correspondiente
    NULL,            -- Reemplaza NULL con la comisión correspondiente si es necesario
    10
);




