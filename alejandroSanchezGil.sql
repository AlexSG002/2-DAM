------------------------------------------------------------
-- EJERCICIO DEl 21 febrero 2025 - Resolver errores
------------------------------------------------------------

/*============================================================
   EJERCICIO 1: MODELO DE OBJETOS CON INFORME (TIPO_VUELO1)
============================================================*/

/* 
  1. Crear el tipo TIPO_INFORME para almacenar:
     - CLASE: Nombre de la clase (ej. 'PRIMERA', 'TURISTA', 'BUSINESS')
     - PASAJEROS: N?mero de pasajeros en esa clase
     - TRIPULANTES: N?mero de tripulantes asignados al vuelo
     - SUMAPVP: Suma del PVP de los pasajes en esa clase
*/
CREATE OR REPLACE TYPE TIPO_INFORME AS OBJECT (
  clase       VARCHAR2(10),
  pasajeros   NUMBER(4),
  tripulantes NUMBER(2),
  sumapvp     FLOAT
);

------------------------------------------------------------
/* 
  2. Crear un VARRAY de 3 elementos de tipo TIPO_INFORME, llamado VARRAY_INFORME.
     Se utilizar? para almacenar los datos correspondientes a las clases 'PRIMERA', 'TURISTA' y 'BUSINESS'.
*/
CREATE OR REPLACE TYPE VARRAY_INFORME AS VARRAY(3) OF TIPO_INFORME;

------------------------------------------------------------
/* 
  3. Crear el tipo TIPO_VUELO1.
     Este tipo tiene los mismos atributos que la tabla VUELO, m?s un atributo "informe" de tipo VARRAY_INFORME.
     Se incluyen los siguientes m?todos MEMBER que operan sobre el atributo informe (sin consultar tablas):
       - numero_pasajeros: Suma el n?mero de pasajeros de todas las clases.
       - pvp_total: Suma el PVP de todas las clases.
       - pasajeros_clase: Devuelve el n?mero de pasajeros para una clase dada.
       - numero_tripulantes: Devuelve el n?mero de tripulantes asignados (se asume el mismo valor en todas las clases).
*/
CREATE OR REPLACE TYPE TIPO_VUELO1 AS OBJECT (
  identificador     VARCHAR2(10),
  aeropuerto_origen VARCHAR2(10),
  aeropuerto_destino VARCHAR2(10),
  tipo_vuelo        VARCHAR2(15),
  fecha_vuelo       DATE,
  informe           VARRAY_INFORME,
  MEMBER FUNCTION numero_pasajeros RETURN NUMBER,
  MEMBER FUNCTION pvp_total RETURN FLOAT,
  MEMBER FUNCTION pasajeros_clase(p_clase VARCHAR2) RETURN NUMBER,
  MEMBER FUNCTION numero_tripulantes RETURN NUMBER
);


------------------------------------------------------------
/* 
  Cuerpo del tipo TIPO_VUELO1: Implementaci?n de los m?todos MEMBER.
*/
CREATE OR REPLACE TYPE BODY TIPO_VUELO1 IS
  MEMBER FUNCTION numero_pasajeros RETURN NUMBER IS
    total NUMBER := 0;
  BEGIN
    IF informe IS NOT NULL THEN
      FOR i IN 1..informe.COUNT LOOP
        total := total + informe(i).pasajeros;
      END LOOP;
    END IF;
    RETURN total;
  END;

  MEMBER FUNCTION pvp_total RETURN FLOAT IS
    total FLOAT := 0;
  BEGIN
    IF informe IS NOT NULL THEN
      FOR i IN 1..informe.COUNT LOOP
        total := total + informe(i).sumapvp;
      END LOOP;
    END IF;
    RETURN total;
  END;

  MEMBER FUNCTION pasajeros_clase(p_clase VARCHAR2) RETURN NUMBER IS
  BEGIN
    IF informe IS NOT NULL THEN
      FOR i IN 1..informe.COUNT LOOP
        IF UPPER(informe(i).clase) = UPPER(p_clase) THEN
          RETURN informe(i).pasajeros;
        END IF;
      END LOOP;
    END IF;
    RETURN 0;
  END;

  MEMBER FUNCTION numero_tripulantes RETURN NUMBER IS
  BEGIN
    IF informe IS NOT NULL AND informe.COUNT > 0 THEN
      RETURN informe(1).tripulantes;
    ELSE
      RETURN 0;
    END IF;
  END;
END;

------------------------------------------------------------
/* 
  4. Crear la tabla de objetos TABLA_VUELO1 a partir del tipo TIPO_VUELO1.
     La columna "identificador" se define como clave primaria.
*/
DROP TABLE TABLA_VUELO1 CASCADE CONSTRAINTS;
CREATE TABLE TABLA_VUELO1 OF TIPO_VUELO1;
ALTER TABLE TABLA_VUELO1 ADD CONSTRAINT pk_tabla_vuelo1 PRIMARY KEY (identificador);

------------------------------------------------------------

/* 
  5. Insertar datos en TABLA_VUELO1 a partir de las tablas relacionales.
  
  Se recorre cada vuelo (tabla VUELO) y se construye un informe (de tipo VARRAY_INFORME)
  para las clases 'PRIMERA', 'TURISTA' y 'BUSINESS'. Se extraen los datos de la tabla PASAJE
  (número de pasajes y suma de PVP por clase) y se cuenta el total de tripulantes utilizando
  la tabla TRIPULANTE.
*/
ALTER TABLE TRIPULANTE ADD identificador VARCHAR2(10);
DECLARE
  v_informe VARRAY_INFORME := VARRAY_INFORME(
    TIPO_INFORME('PRIMERA', 0, 0, 0),
    TIPO_INFORME('TURISTA', 0, 0, 0),
    TIPO_INFORME('BUSINESS', 0, 0, 0)
  );
  v_tripulantes NUMBER;
BEGIN
  FOR r IN (SELECT identificador, aeropuerto_origen, aeropuerto_destino, tipo_vuelo, fecha_vuelo
              FROM VUELO) LOOP

    -- Reinicializar el informe para el vuelo actual
    v_informe(1) := TIPO_INFORME('PRIMERA', 0, 0, 0);
    v_informe(2) := TIPO_INFORME('TURISTA', 0, 0, 0);
    v_informe(3) := TIPO_INFORME('BUSINESS', 0, 0, 0);

    -- Obtener el número total de tripulantes para este vuelo
    SELECT COUNT(*) INTO v_tripulantes
    FROM TRIPULANTE
    WHERE identificador = r.identificador;

    -- Recoger datos de la tabla PASAJE (número de pasajes y suma de PVP por clase)
    FOR p IN (SELECT clase, COUNT(*) AS cnt, NVL(SUM(pvp), 0) AS total_pvp
                FROM PASAJE
               WHERE identificador = r.identificador
               GROUP BY clase) LOOP
      IF UPPER(p.clase) = 'PRIMERA' THEN
        v_informe(1) := TIPO_INFORME('PRIMERA', p.cnt, v_tripulantes, p.total_pvp);
      ELSIF UPPER(p.clase) = 'TURISTA' THEN
        v_informe(2) := TIPO_INFORME('TURISTA', p.cnt, v_tripulantes, p.total_pvp);
      ELSIF UPPER(p.clase) = 'BUSINESS' THEN
        v_informe(3) := TIPO_INFORME('BUSINESS', p.cnt, v_tripulantes, p.total_pvp);
      END IF;
    END LOOP;

    -- Insertar el objeto TIPO_VUELO1 en la tabla TABLA_VUELO1
    INSERT INTO TABLA_VUELO1 (identificador, aeropuerto_origen, aeropuerto_destino, tipo_vuelo, fecha_vuelo, informe)
    VALUES (
      r.identificador,
      r.aeropuerto_origen,
      r.aeropuerto_destino,
      r.tipo_vuelo,
      r.fecha_vuelo,
      v_informe
    );

  END LOOP;
  COMMIT;
END;



------------------------------------------------------------
/* 
  6.a. Consulta: Mostrar por cada vuelo el n?mero total de pasajeros y la suma total del PVP.
*/
SELECT t.identificador,
       t.numero_pasajeros() AS total_pasajeros,
       t.pvp_total() AS total_pvp
FROM TABLA_VUELO1 t;

------------------------------------------------------------
/* 
  6.b. Consulta: Mostrar por cada vuelo el n?mero de pasajeros en la clase 'BUSINESS'.
*/
SELECT t.identificador,
       t.pasajeros_clase('BUSINESS') AS pasajeros_business
FROM TABLA_VUELO1 t;

------------------------------------------------------------
/*============================================================
   EJERCICIO 2: MODELO DE OBJETOS CON TABLA ANIDADA (TIPO_VUELO2)
============================================================*/

/*-----------------------------------------------------------
  Paso 7.a: Crear el tipo TIPO_PASAJERO para representar a un pasajero.
-----------------------------------------------------------*/
CREATE OR REPLACE TYPE TIPO_PASAJERO AS OBJECT (
    cod       NUMBER,
    nombre    VARCHAR2(30),
    tlf       VARCHAR2(10),
    direccion VARCHAR2(40),
    pais      VARCHAR2(15)
);
/

------------------------------------------------------------
/* 
  Paso 7.a.1: Crear una colección (tabla) de objetos TIPO_PASAJERO.
*/
CREATE OR REPLACE TYPE TABLA_PASAJERO AS TABLE OF TIPO_PASAJERO;
/

------------------------------------------------------------
/*-----------------------------------------------------------
  Paso 7.b: Crear el tipo TIPO_PASAJE2 para representar un pasaje.
           Este tipo NO incluye la columna IDENTIFICADOR y 
           en lugar de PASAJERO_COD se utiliza una REFERENCIA a TIPO_PASAJERO.
-----------------------------------------------------------*/
CREATE OR REPLACE TYPE TIPO_PASAJE2 AS OBJECT (
    pasajero   REF TIPO_PASAJERO,  -- Referencia a un objeto TIPO_PASAJERO
    numasiento NUMBER,
    clase      VARCHAR2(10),
    pvp        FLOAT
);
/

-- Crear una tabla anidada de objetos TIPO_PASAJE2
CREATE OR REPLACE TYPE TABLA_PASAJE2 AS TABLE OF TIPO_PASAJE2;
/

------------------------------------------------------------
/*-----------------------------------------------------------
  Paso 7.c: Crear el tipo TIPO_VUELO2.
           Este tipo tiene los mismos atributos que la tabla VUELO y
           una tabla anidada de pasajes (TABLA_PASAJE2).
           Adem?s, se incluyen dos m?todos MEMBER:
             - getPasajeroPorAsiento: Devuelve el pasajero que ocupa un asiento concreto.
             - getPasajerosPorClase: Devuelve una colecci?n de pasajeros que viajan en una clase dada.
-----------------------------------------------------------*/
CREATE OR REPLACE TYPE TIPO_VUELO2 AS OBJECT (
    identificador     VARCHAR2(10),
    aeropuerto_origen VARCHAR2(10),
    aeropuerto_destino VARCHAR2(10),
    tipo_vuelo        VARCHAR2(15),
    fecha_vuelo       DATE,
    pasajes           TABLA_PASAJE2,  -- Tabla anidada de pasajes
    -- Declaración de métodos
    MEMBER FUNCTION getPasajeroporAsientos(pAsiento NUMBER) RETURN TIPO_PASAJERO,
    MEMBER FUNCTION getPasajerosPorClase(pClase VARCHAR2) RETURN TABLA_PASAJERO
);
/

------------------------------------------------------------
/* 
  Cuerpo del tipo TIPO_VUELO2: Implementaci?n de los m?todos MEMBER.
*/
CREATE OR REPLACE TYPE BODY TIPO_VUELO2 IS

    -- Devuelve el pasajero que ocupa el asiento indicado.
    MEMBER FUNCTION getPasajeroporAsientos(pAsiento NUMBER) RETURN TIPO_PASAJERO IS
        vPasajero TIPO_PASAJERO;
        vRef      REF TIPO_PASAJERO;
    BEGIN
        IF pasajes IS NOT NULL THEN
            FOR i IN 1..pasajes.COUNT LOOP
                IF pasajes(i).numasiento = pAsiento THEN
                    vRef := pasajes(i).pasajero;
                    SELECT DEREF(vRef) INTO vPasajero FROM dual;
                    RETURN vPasajero;
                END IF;
            END LOOP;
        END IF;
        RETURN NULL;
    END;

    -- Devuelve una colección de pasajeros que viajan en la clase indicada.
    MEMBER FUNCTION getPasajerosPorClase(pClase VARCHAR2) RETURN TABLA_PASAJERO IS
        vLista TABLA_PASAJERO := TABLA_PASAJERO();
        vPas   TIPO_PASAJERO;
        vRef   REF TIPO_PASAJERO;
    BEGIN
        IF pasajes IS NOT NULL THEN
            FOR i IN 1..pasajes.COUNT LOOP
                IF UPPER(pasajes(i).clase) = UPPER(pClase) THEN
                    vRef := pasajes(i).pasajero;
                    SELECT DEREF(vRef) INTO vPas FROM dual;
                    vLista.EXTEND;
                    vLista(vLista.COUNT) := vPas;
                END IF;
            END LOOP;
        END IF;
        RETURN vLista;
    END;

END;
/

------------------------------------------------------------
/* 
  Paso 10: Crear la tabla de objetos TABLA_VUELO2 a partir del tipo TIPO_VUELO2.
  La tabla anidada "pasajes" se almacena f?sicamente en NESTED_PASAJES.
*/
CREATE TABLE TABLA_VUELO2 OF TIPO_VUELO2 (
    PRIMARY KEY (identificador)
)
NESTED TABLE pasajes STORE AS NESTED_PASAJES;

------------------------------------------------------------
/* 
  Paso 10.b: Crear la tabla de objetos OBJ_PASAJERO a partir del tipo TIPO_PASAJERO.
*/
CREATE TABLE OBJ_PASAJERO OF TIPO_PASAJERO (
    PRIMARY KEY (cod)
);

------------------------------------------------------------
/* 
  Insertar datos en OBJ_PASAJERO a partir de la tabla relacional PASAJERO.
*/
INSERT INTO OBJ_PASAJERO
SELECT TIPO_PASAJERO(cod, nombre, tlf, direccion, pais)
FROM PASAJERO;


------------------------------------------------------------
/* 
  Paso 10.c: Poblar TABLA_VUELO2 a partir de las tablas relacionales VUELO y PASAJE.
  Se utiliza una subconsulta para recopilar (COLLECT) los pasajes asociados a cada vuelo.
*/
INSERT INTO TABLA_VUELO2
SELECT TIPO_VUELO2(
    v.identificador,
    v.aeropuerto_origen,
    v.aeropuerto_destino,
    v.tipo_vuelo,
    v.fecha_vuelo,
    (SELECT CAST(COLLECT(
              TIPO_PASAJE2(
                  (SELECT REF(p) FROM OBJ_PASAJERO p WHERE p.cod = pas.PASAJERO_COD),
                  pas.numasiento,
                  pas.clase,
                  pas.pvp
              )
            ) AS TABLA_PASAJE2)
     FROM PASAJE pas
     WHERE pas.identificador = v.identificador)
)
FROM VUELO v;
COMMIT;

------------------------------------------------------------
/* 
  Paso 11: Consulta para mostrar el identificador de vuelo y el nombre del pasajero que
  ocupa el asiento 10. Si no hay pasajero se muestra 'NO HAY'.
*/
SELECT v.identificador,
       CASE
           WHEN v.getPasajeroPorAsientos(10) IS NOT NULL THEN v.getPasajeroPorAsientos(10).nombre
           ELSE 'NO HAY'
       END AS pasajero
FROM TABLA_VUELO2 v;

------------------------------------------------------------
/* 
  Paso 12: Procedimiento almacenado para listar tripulantes del vuelo.
  NOTA: Dado que no existe una tabla de relaci?n entre VUELO y TRIPULANTE, se simula
  listando todos los tripulantes y mostrando su fecha de validez del t?tulo.
  (Ajusta este procedimiento si cuentas con una relaci?n real.)
*/
CREATE OR REPLACE PROCEDURE ListadoTripulantes(pIdentificador VARCHAR2) IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('IDENTIFICADOR: ' || pIdentificador || ' ****** TRIPULANTE CON VALIDEZ DE TITULO:');
    FOR r IN (
        SELECT t.nombre, t.validez_tit
        FROM TRIPULANTE t
        ORDER BY t.nombre
    ) LOOP
        IF r.validez_tit >= SYSDATE THEN
            DBMS_OUTPUT.PUT_LINE('    ' || r.nombre || ' - ' || TO_CHAR(r.validez_tit, 'DD/MM/YYYY'));
        END IF;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('IDENTIFICADOR: ' || pIdentificador || ' ****** TRIPULANTE CON TITULO CADUCADO:');
    FOR r IN (
        SELECT t.nombre, t.validez_tit
        FROM TRIPULANTE t
        ORDER BY t.nombre
    ) LOOP
        IF r.validez_tit < SYSDATE THEN
            DBMS_OUTPUT.PUT_LINE('    ' || r.nombre || ' - ' || TO_CHAR(r.validez_tit, 'DD/MM/YYYY'));
        END IF;
    END LOOP;  -- Se corrigió aquí
END;
/

------------------------------------------------------------
/* 
  Paso 13: Procedimiento almacenado para listar pasajeros en un vuelo y en una clase dada.
  Se obtiene el objeto vuelo de TABLA_VUELO2 y se invoca el m?todo getPasajerosPorClase.
*/
CREATE OR REPLACE PROCEDURE Listado(pIdentificador VARCHAR2, pClase VARCHAR2) IS
  vVuelo    TIPO_VUELO2;
  vPasajeros TABLA_PASAJERO;
BEGIN
  -- Se obtiene el objeto vuelo a partir del identificador.
  SELECT VALUE(v)
    INTO vVuelo
  FROM TABLA_VUELO2 v
  WHERE v.identificador = pIdentificador;
  
  DBMS_OUTPUT.PUT_LINE('IDENTIFICADOR: ' || pIdentificador || ' ****** CLASE: ' || pClase);
  
  vPasajeros := vVuelo.getPasajerosPorClase(pClase);
  
  IF vPasajeros IS NULL OR vPasajeros.COUNT = 0 THEN
    DBMS_OUTPUT.PUT_LINE('    NO HAY PERSONAS');
  ELSE
    FOR i IN 1..Pasajeros.COUNT LOOP
      DBMS_OUTPUT.PUT_LINE('    ' || vPasajeros(i).nombre);
    END LOOP;
  END IF;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Vuelo no encontrado.');
END;

------------------------------------------------------------
/* 
  Bloque PL/SQL de prueba para ejecutar los procedimientos.
*/
SET SERVEROUTPUT ON;
BEGIN
  -- Prueba del procedimiento para listar tripulantes.
  ListadoTripulantes('IBE-762');
  
  -- Prueba del procedimiento para listar pasajeros por vuelo y clase.
  Listado('IBE-762', 'TURISTA');
  Listado('BRU-1234', 'BUSINESS');
END;