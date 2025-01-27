
drop table partidos cascade constraints;
drop table estadisticas cascade constraints;
drop table jugadores cascade constraints;
drop table equipos cascade constraints;
purge recyclebin;

CREATE TABLE equipos (
  Nombre varchar2(20) NOT NULL,
  Ciudad varchar2(20) DEFAULT NULL,
  Conferencia varchar2(4) DEFAULT NULL,
  Division varchar2(9) DEFAULT NULL,
  PRIMARY KEY (Nombre)
);

INSERT INTO equipos VALUES ('Rockets','Houston','West','SouthWest');


CREATE TABLE jugadores (
  codigo int NOT NULL,
  Nombre varchar2(30) DEFAULT NULL,
  Procedencia varchar2(20) DEFAULT NULL,
  Altura varchar2(4) DEFAULT NULL, -- en pies, 1 pie 0,3048 metros
  Peso int DEFAULT NULL, -- en libras 1 libra 453.59 gramos
  Posicion varchar2(12) DEFAULT NULL,
  Nombre_equipo varchar2(20) DEFAULT NULL,
  PRIMARY KEY (codigo),
  FOREIGN KEY (Nombre_equipo) References equipos(Nombre)
);

INSERT INTO jugadores VALUES (340,'Rafer ALston','Fresno State','6,2',175,'BASE','Rockets');
INSERT INTO jugadores VALUES (341,'Shane Battier','Duke','6,8',220,'ESCOLTA','Rockets');
INSERT INTO jugadores VALUES (342,'Aaron Brooks','Oregon','6,0',161,'BASE','Rockets');
INSERT INTO jugadores VALUES (343,'Steve Francis','Maryland','6,3',210,'BASE','Rockets');
INSERT INTO jugadores VALUES (344,'Mike Harris','Rice','6,6',240,'ESCOLTA','Rockets');
INSERT INTO jugadores VALUES (345,'Chuck Hayes','Kentucky','6,6',238,'ESCOLTA','Rockets');
INSERT INTO jugadores VALUES (346,'Luther Head','Illinois','6,3',185,'BASE','Rockets');
INSERT INTO jugadores VALUES (347,'Bobby Jackson','Minnesota','6,1',185,'BASE','Rockets');
INSERT INTO jugadores VALUES (348,'Carl Landry','Purdue','6,9',248,'ESCOLTA','Rockets');
INSERT INTO jugadores VALUES (349,'Tracy McGrady','Mount Zion Christian','6,8',223,'BASE','Rockets');
INSERT INTO jugadores VALUES (350,'Dikembre Mutombo','Georgetown','7,2',260,'PIVOTE','Rockets');
INSERT INTO jugadores VALUES (351,'Steve NOvak','Marquette','6,10',220,'ESCOLTA','Rockets');
INSERT INTO jugadores VALUES (352,'Luis Scola','Argentina','6,9',245,'ALERO','Rockets');
INSERT INTO jugadores VALUES (353,'Loren Woods','Arizona','7,2',260,'PIVOTE','Rockets');
INSERT INTO jugadores VALUES (354,'Yao Ming','China','7,6',310,'PIVOTE','Rockets');

CREATE TABLE estadisticas (
  temporada varchar2(5) NOT NULL ,
  jugador int NOT NULL ,
  Puntos_por_partido number(5,2) DEFAULT NULL,
  Asistencias_por_partido number(5,2) DEFAULT NULL,
  Tapones_por_partido number(5,2) DEFAULT NULL,
  Rebotes_por_partido number(5,2) DEFAULT NULL,
  PRIMARY KEY (temporada,jugador),
  FOREIGN KEY (jugador) REFERENCES Jugadores(Codigo)
);


INSERT INTO estadisticas VALUES ('03/04',341,8.5,1.3,0.7,3.8);
INSERT INTO estadisticas VALUES ('03/04',347,13.8,2.1,0.2,3.5);
INSERT INTO estadisticas VALUES ('03/04',340,10.2,4.5,0.2,2.8);
INSERT INTO estadisticas VALUES ('03/04',343,16.6,6.2,0.4,5.5);
INSERT INTO estadisticas VALUES ('03/04',349,28,5.5,0.6,6);
INSERT INTO estadisticas VALUES ('03/04',350,5.6,0.4,1.9,6.7);
INSERT INTO estadisticas VALUES ('03/04',353,3.2,0.3,0.5,3.5);
INSERT INTO estadisticas VALUES ('03/04',354,17.5,1.5,1.9,9);


CREATE TABLE partidos (
  codigo int NOT NULL,
  equipo_local varchar2(20) DEFAULT NULL,
  equipo_visitante varchar2(20) DEFAULT NULL,
  puntos_local number(5) DEFAULT NULL,
  puntos_visitante number(5) DEFAULT NULL,
  temporada varchar2(5) DEFAULT NULL,
  PRIMARY KEY (codigo),
  FOREIGN KEY (equipo_local) REFERENCES equipos(nombre),
  FOREIGN KEY (equipo_visitante) REFERENCES equipos(nombre)
);


INSERT INTO partidos VALUES (9484,'Rockets','Raptors',71,99,'03/04');
INSERT INTO partidos VALUES (9485,'Rockets','Lakers',132,115,'03/04');
INSERT INTO partidos VALUES (9486,'Rockets','Grizzlies',153,126,'03/04');
INSERT INTO partidos VALUES (9487,'Rockets','Clippers',134,104,'03/04');
INSERT INTO partidos VALUES (9488,'Rockets','Knicks',124,100,'03/04');
INSERT INTO partidos VALUES (9489,'Rockets','Timberwolves',142,100,'03/04');
INSERT INTO partidos VALUES (9490,'Rockets','Celtics',101,86,'03/04');
INSERT INTO partidos VALUES (9491,'Rockets','76ers',128,115,'03/04');
INSERT INTO partidos VALUES (9492,'Rockets','Nets',51,56,'03/04');
INSERT INTO partidos VALUES (9493,'Rockets','Pistons',132,66,'03/04');
INSERT INTO partidos VALUES (9494,'Rockets','Cavaliers',126,77,'03/04');
INSERT INTO partidos VALUES (9495,'Rockets','Pacers',120,84,'03/04');
INSERT INTO partidos VALUES (9496,'Rockets','Bulls',155,98,'03/04');
INSERT INTO partidos VALUES (9497,'Rockets','Bucks',91,97,'03/04');
INSERT INTO partidos VALUES (9498,'Rockets','Magic',142,84,'03/04');
INSERT INTO partidos VALUES (9499,'Rockets','Wizards',99,90,'03/04');
INSERT INTO partidos VALUES (9500,'Rockets','Hawks',139,78,'03/04');
INSERT INTO partidos VALUES (9501,'Rockets','Bobcats',81,66,'03/04');
INSERT INTO partidos VALUES (9502,'Rockets','Heat',146,60,'03/04');
INSERT INTO partidos VALUES (9503,'Rockets','Jazz',154,142,'03/04');
INSERT INTO partidos VALUES (9504,'Rockets','Nuggets',145,62,'03/04');
INSERT INTO partidos VALUES (9505,'Rockets','Trail Blazers',67,129,'03/04');
INSERT INTO partidos VALUES (9506,'Rockets','Supersonics',56,87,'03/04');
INSERT INTO partidos VALUES (9507,'Rockets','Warriors',90,120,'03/04');
INSERT INTO partidos VALUES (9508,'Rockets','Kings',153,56,'03/04');
INSERT INTO partidos VALUES (9509,'Rockets','Hornets',86,121,'03/04');
INSERT INTO partidos VALUES (9510,'Rockets','Spurs',92,108,'03/04');
INSERT INTO partidos VALUES (9511,'Rockets','Mavericks',134,155,'03/04');
INSERT INTO partidos VALUES (9512,'Rockets','Suns',145,61,'03/04');
INSERT INTO partidos VALUES (10354,'Rockets','Raptors',141,143,'03/04');
INSERT INTO partidos VALUES (10355,'Rockets','Lakers',82,99,'03/04');
INSERT INTO partidos VALUES (10356,'Rockets','Grizzlies',123,92,'03/04');
INSERT INTO partidos VALUES (10357,'Rockets','Clippers',140,125,'03/04');
INSERT INTO partidos VALUES (10358,'Rockets','Knicks',130,65,'03/04');
INSERT INTO partidos VALUES (10359,'Rockets','Timberwolves',157,77,'03/04');
INSERT INTO partidos VALUES (10360,'Rockets','Celtics',78,76,'03/04');
INSERT INTO partidos VALUES (10361,'Rockets','76ers',150,154,'03/04');
INSERT INTO partidos VALUES (10362,'Rockets','Nets',140,131,'03/04');
INSERT INTO partidos VALUES (10363,'Rockets','Pistons',56,148,'03/04');
INSERT INTO partidos VALUES (10364,'Rockets','Cavaliers',73,64,'03/04');
INSERT INTO partidos VALUES (10365,'Rockets','Pacers',102,61,'03/04');
INSERT INTO partidos VALUES (10366,'Rockets','Bulls',100,61,'03/04');
INSERT INTO partidos VALUES (10367,'Rockets','Bucks',125,89,'03/04');
INSERT INTO partidos VALUES (10368,'Rockets','Magic',73,109,'03/04');
INSERT INTO partidos VALUES (10369,'Rockets','Wizards',111,96,'03/04');
INSERT INTO partidos VALUES (10370,'Rockets','Hawks',79,142,'03/04');
INSERT INTO partidos VALUES (10371,'Rockets','Bobcats',102,92,'03/04');
INSERT INTO partidos VALUES (10372,'Rockets','Heat',98,93,'03/04');
INSERT INTO partidos VALUES (10373,'Rockets','Jazz',145,83,'03/04');
INSERT INTO partidos VALUES (10374,'Rockets','Nuggets',117,86,'03/04');
INSERT INTO partidos VALUES (10375,'Rockets','Trail Blazers',146,60,'03/04');
INSERT INTO partidos VALUES (10376,'Rockets','Supersonics',61,113,'03/04');
INSERT INTO partidos VALUES (10377,'Rockets','Warriors',81,124,'03/04');
INSERT INTO partidos VALUES (10378,'Rockets','Kings',69,62,'03/04');
INSERT INTO partidos VALUES (10379,'Rockets','Hornets',132,93,'03/04');
INSERT INTO partidos VALUES (10380,'Rockets','Spurs',151,97,'03/04');
INSERT INTO partidos VALUES (10381,'Rockets','Mavericks',80,114,'03/04');
INSERT INTO partidos VALUES (10382,'Rockets','Suns',88,95,'03/04');

