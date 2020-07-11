USE mygim;
DROP TABLE IF EXISTS actividad;
DROP TABLE IF EXISTS  apuntar;
DROP TABLE IF EXISTS grupousuario;
DROP TABLE IF EXISTS organizar;
DROP TABLE IF EXISTS sala;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS ventas;

CREATE TABLE usuario(dni VARCHAR(10) not null, nombre VARCHAR(50) not null, apellido1 VARCHAR(50) not null, apellido2 VARCHAR(50) not null, nacimiento VARCHAR(10) not null,
 telefono VARCHAR(9) not null, password VARCHAR(64) not null, email VARCHAR(255) not null primary key) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE grupousuario(idgrupo INTEGER not null AUTO_INCREMENT primary key, email VARCHAR(255) not null, nombregrupo VARCHAR(32) not null) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE sala(nombresala VARCHAR(15) not null primary key, puestos INTEGER not null) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE actividad(idactividad INTEGER not null AUTO_INCREMENT primary key, nombre VARCHAR(15) not null , vacantes INTEGER not null, descripcion VARCHAR(255) not null,
 nombresala VARCHAR(15) not null, dia VARCHAR(10) not null, horacomienzo VARCHAR(5) not null, horafin VARCHAR(5) not null, precio INTEGER not null,email VARCHAR(255) not null) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE apuntar(idapuntar INTEGER not null AUTO_INCREMENT primary key, email VARCHAR(255) not null, idactividad INTEGER not null) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ventas(idcuentas INTEGER not null AUTO_INCREMENT primary key, nombreactividad VARCHAR(25), venta INTEGER not null) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO ventas( nombreactividad, venta) VALUES ('Baile2020-01-01', 1000);


INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '02443571M', 'Paula', 'Gil', 'Martin', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'paulagilmartin@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '74859625F', 'Marcos', 'Lopez', 'Ruiz', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'marcoslopezruiz@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '25364157X', 'Javier', 'Gomez', 'Alvarez', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'javiergomezalvarez@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '11122233P', 'Maria', 'Fernandez', 'Santos', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'mariafernandezsantos@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '00123546J', 'Nuria', 'Martin', 'Suarez', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'nuriamartinsuarez@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '00145869A', 'Paula', 'Zurdo', 'Rivera', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'paulazurdorivera@gmail.com', '1998-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '78932655J', 'Marta', 'Alonso', 'Arribas', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'martaalonsoarribas@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '23651025X', 'Hugo', 'Fuentes', 'Escudero', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'hugofuentesescudero@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '54896325V', 'Eva', 'Huertas', 'Rodriguez', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'evahuertasrodriguez@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '54896875S', 'Bruno', 'Salgado', 'Ortega', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'brunosalgadoortega@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '548989657N', 'Elena', 'Gomez', 'Fernandez', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'elenagomezfernandez@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '896989657F', 'Alicia', 'Martinez', 'Gonzalez', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'aliciamartinezgonzalez@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '87896557X', 'Jorge', 'Sanchez', 'Navares', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'jorgesancheznavares@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '00025354P', 'Angela', 'Sanchez', 'Palomino', '656124538', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'angelasanchezpalomino@gmail.com', '1990-10-10');

INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '12554836X', 'Luis', 'Martinez', 'Gomez', '656652148', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'luismartinezgomez@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '25635985R', 'Laura', 'Gutierrez', 'Lopez', '655851174', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'lauragurierrezlopez@gmail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '36256987V', 'Mario', 'Garcia', 'Serrano', '621523162', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'entrenador', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '78569325Z', 'Tester', 'Testero', 'Testerez', '648123546', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'admin@mail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '78569325Z', 'Tester', 'Testero', 'Testerez', '648123546', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'entrenador@mail.com', '1990-10-10');
INSERT INTO usuario( dni, nombre, apellido1, apellido2, telefono, password, email, nacimiento) VALUES ( '78569325Z', 'Tester', 'Testero', 'Testerez', '648123546', 'eJ5rO7DM0MdSCU4NSPCNrorDYgkz462pALy9XdSfFtw=', 'user@mail.com', '1990-10-10');




INSERT INTO actividad( nombre, dia, horacomienzo, horafin, nombresala, vacantes, descripcion,precio,email) VALUES ('Yoga', '02-10-2020','10:00','11:00', 'Relax', 25, 'Clase de yoga de una hora',80 ,'luismartinezgomez@gmail.com');
INSERT INTO actividad( nombre, dia, horacomienzo, horafin, nombresala, vacantes, descripcion,precio,email) VALUES ( 'Zumba', '06-10-2020','11:00','12:00', 'Baile', 89, 'Clase de zumba, fusion de movimientos de alta y baja intensidad',60,'mariogarciaserrano@gmail.com');
INSERT INTO actividad( nombre, dia, horacomienzo, horafin, nombresala, vacantes, descripcion,precio,email) VALUES ('Spinning', '02-10-2019','12:00','13:00', 'Máquina cardio', 90, 'Tipo de ejercicio aeróbico que se realiza con una bicicleta estática',30,'entrenador@mail.com');
INSERT INTO actividad( nombre, dia, horacomienzo, horafin, nombresala, vacantes, descripcion,precio,email) VALUES ( 'Waterpolo', '06-10-2020','13:00','14:00', 'Piscina', 23, 'Partido de waterpolo entre los clientes',40,'entrenador@mail.com');
INSERT INTO actividad( nombre, dia, horacomienzo, horafin, nombresala, vacantes, descripcion,precio,email) VALUES ( 'Gim. Ritmica', '06-10-2020','13:00','14:00', 'Relax', 23, 'Gimnasia rítmica',100,'entrenador@mail.com');
INSERT INTO actividad( nombre, dia, horacomienzo, horafin, nombresala, vacantes, descripcion,precio,email) VALUES ( 'Gim. Acuática', '06-10-2020','13:00','14:00', 'Piscina', 23, 'Gimnasia acuática ',50,'entrenador@mail.com');
INSERT INTO actividad( nombre, dia, horacomienzo, horafin, nombresala, vacantes, descripcion,precio,email) VALUES ( 'Curso natación', '05-10-2020','10:00','14:00', 'Piscina', 23, 'Lecciones básicas de natación',30,'entrenador@mail.com');



INSERT INTO sala( nombresala, puestos) VALUES ('Baile', 100);
INSERT INTO sala( nombresala, puestos) VALUES ('Máquina cardio', 90);
INSERT INTO sala( nombresala, puestos) VALUES ('Relax', 30);
INSERT INTO sala( nombresala, puestos) VALUES ('Piscina', 30);




INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (1, 'marcoslopezruiz@gmail.com', 1);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (2, 'paulagilmartin@gmail.com', 1);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (3, 'javiergomezalvarez@gmail.com', 1);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (4, 'nuriamartinsuarez@gmail.com', 1);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (5, 'paulazurdorivera@gmail.com', 1);

INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (6, 'evahuertasrodriguez@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (7, 'hugofuentesescudero@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (8, 'elenagomezfernandez@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (9, 'paulagilmartin@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (10, 'angelasanchezpalomino@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (11, 'marcoslopezruiz@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (12, 'evahuertasrodriguez@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (13, 'nuriamartinsuarez@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (14, 'hugofuentesescudero@gmail.com', 2);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (15, 'paulazurdorivera@gmail.com', 2);

INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (18, 'paulagilmartin@gmail.com', 4);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (19, 'javiergomezalvarez@gmail.com', 4);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (20, 'hugofuentesescudero@gmail.com', 4);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (21, 'evahuertasrodriguez@gmail.com', 4);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (22, 'paulazurdorivera@gmail.com', 4);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (23, 'aliciamartinezgonzalez@gmail.com', 4);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (24, 'aliciamartinezgonzalez@gmail.com', 5);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (25, 'aliciamartinezgonzalez@gmail.com', 6);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (26, 'paulazurdorivera@gmail.com', 5);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (27, 'paulazurdorivera@gmail.com', 6);

INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (30, 'paulagilmartin@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (31, 'marcoslopezruiz@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (32, 'javiergomezalvarez@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (33, 'mariafernandezsantos@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (34, 'nuriamartinsuarez@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (35, 'paulazurdorivera@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (36, 'martaalonsoarribas@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (37, 'hugofuentesescudero@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (38,'evahuertasrodriguez@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (39, 'brunosalgadoortega@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (40, 'elenagomezfernandez@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (41, 'aliciamartinezgonzalez@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (42, 'jorgesancheznavares@gmail.com', 7);
INSERT INTO apuntar(idapuntar, email, idactividad) VALUES (43, 'angelasanchezpalomino@gmail.com', 7);



INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'paulagilmartin@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'marcoslopezruiz@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'javiergomezalvarez@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'mariafernandezsantos@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'nuriamartinsuarez@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'paulazurdorivera@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'martaalonsoarribas@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'hugofuentesescudero@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'evahuertasrodriguez@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'brunosalgadoortega@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'elenagomezfernandez@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'aliciamartinezgonzalez@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'jorgesancheznavares@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'angelasanchezpalomino@gmail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'luismartinezgomez@gmail.com', 'entrenador');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'lauragurierrezlopez@gmail.com', 'entrenador');
INSERT INTO grupousuario(email, nombregrupo) VALUES  ( 'evahuertasrodriguez@gmail.com', 'admin');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( ''user@mail.com', 'cliente');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'entrenador@mail.com', 'entrenador');
INSERT INTO grupousuario( email, nombregrupo) VALUES ( 'admin@mail.com', 'admin');
