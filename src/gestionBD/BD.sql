
CREATE database verduleria;
USE verduleria ;

-- -----------------------------------------------------
-- Table Clientes
-- -----------------------------------------------------
CREATE TABLE Clientes (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `rut_cliente` varchar(10) NOT NULL,
  `nombre_cliente` VARCHAR(45) NOT NULL,
  `correo_cliente` VARCHAR(45) NOT NULL,
  `telefono_cliente` varchar(9) NOT NULL,
  `direccion_cliente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`));


-- -----------------------------------------------------
-- Table Promociones
-- -----------------------------------------------------
CREATE TABLE Promociones (
  `codigo_promocion` INT NOT NULL,
  `nombre_promocion` VARCHAR(45) NOT NULL,
  `porcentaje_promocion` FLOAT NOT NULL,
  PRIMARY KEY (`codigo_promocion`));


-- -----------------------------------------------------
-- Productos
-- -----------------------------------------------------
CREATE TABLE Productos(
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `sku_producto` varchar(8) NOT NULL,
  `codigobarra_producto` VARCHAR(5) NOT NULL, /*Code-39*/
  `nombre_producto` VARCHAR(45) NOT NULL,
  `descripcion_producto` VARCHAR(200) NOT NULL,
  `precio_neto` FLOAT NOT NULL,
  `precio_iva` FLOAT NOT NULL,
  `codigo_promocion` INT NOT NULL,
  PRIMARY KEY (`id_producto`),
    FOREIGN KEY (`codigo_promocion`)REFERENCES Promociones (`codigo_promocion`));


-- -----------------------------------------------------
-- Table Tipos_Sucursal
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Tipos_Sucursal (
  `id_tipo` INT NOT NULL AUTO_INCREMENT,
  `nombre_tipo` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_tipo`));


-- -----------------------------------------------------
-- Table Sucursales
-- -----------------------------------------------------
CREATE TABLE Sucursales (
  `codigo_sucursal` INT NOT NULL AUTO_INCREMENT,
  `nombre_sucursal` VARCHAR(45) NOT NULL,
  `direccion_sucursal` VARCHAR(45) NOT NULL,
  `id_tipo` INT NOT NULL,
  PRIMARY KEY (`codigo_sucursal`),
    FOREIGN KEY (`id_tipo`) REFERENCES Tipos_Sucursal (`id_tipo`));


-- -----------------------------------------------------
-- Table Vendedores
-- -----------------------------------------------------
CREATE TABLE Vendedores (
  `codigo_vendedor` INT NOT NULL,
  `nombre_vendedor` VARCHAR(45) NOT NULL,
  `codigo_sucursal` INT NOT NULL,
  PRIMARY KEY (`codigo_vendedor`),
    FOREIGN KEY (`codigo_sucursal`) REFERENCES Sucursales (`codigo_sucursal`));


-- -----------------------------------------------------
-- Table Ventas
-- -----------------------------------------------------
CREATE TABLE Ventas (
  `codigo_venta` INT NOT NULL AUTO_INCREMENT,
  `codigo_vendedor` INT NOT NULL,
  `id_cliente` INT NOT NULL,
  `fecha_venta` DATE NOT NULL,
  `hora_venta` TIME NOT NULL,
  PRIMARY KEY (`codigo_venta`),
    FOREIGN KEY (`codigo_vendedor`) REFERENCES Vendedores (`codigo_vendedor`),
    FOREIGN KEY (`id_cliente`)REFERENCES Clientes (`id_cliente`));


-- -----------------------------------------------------
-- Table Descuentos
-- -----------------------------------------------------
CREATE TABLE Descuentos (
  `codigo_descuento` INT NOT NULL AUTO_INCREMENT,
  `descuento` FLOAT NOT NULL,
  PRIMARY KEY (`codigo_descuento`));


-- -----------------------------------------------------
-- Table Detalle_Venta
-- -----------------------------------------------------
CREATE TABLE Detalle_Venta (
  `codigo_detalle_venta` INT NOT NULL AUTO_INCREMENT,
  `id_producto` INT NOT NULL,
  `codigo_venta` INT NOT NULL,
  `codigo_descuento` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `total` INT NOT NULL,
  PRIMARY KEY (`codigo_detalle_venta`),
    FOREIGN KEY (`id_producto`) REFERENCES Productos (`id_producto`),
    FOREIGN KEY (`codigo_venta`) REFERENCES Ventas (`codigo_venta`),
    FOREIGN KEY (`codigo_descuento`) REFERENCES Descuentos (`codigo_descuento`));


-- -----------------------------------------------------
-- Table Historico_Productos
-- -----------------------------------------------------
CREATE TABLE Historico_Productos (
  `codigo_historico_p` INT NOT NULL AUTO_INCREMENT,
  `id_producto` int NOT NULL,
  `precio_producto_h` INT NOT NULL,
  `fecha_h` DATE NOT NULL,
  PRIMARY KEY (`codigo_historico_p`),
    FOREIGN KEY (`id_producto`) REFERENCES Productos (`id_producto`));

/********************************ALGUNOS INSERT*********************************************/

-- -----------------------------------------------------
-- Insert Tipo Sucursal
-- -----------------------------------------------------
INSERT INTO tipos_sucursal(`nombre_tipo`)
VALUES('Fisica'),('Online');

-- -----------------------------------------------------
-- Insert Sucursales
-- -----------------------------------------------------
INSERT INTO sucursales (`nombre_sucursal`,`direccion_sucursal`,`id_tipo`)
VALUES('Verduleria Apoquindo','Apoquindo 1025',1),('Verduleria Bio-Bio','Carrera 123',1),
('Verduleria Concepcion','Rosas 458',1),('Verduleria Online','Nueva Providencia 1001',2);

-- -----------------------------------------------------
-- Insert Vendedores
-- -----------------------------------------------------
INSERT INTO vendedores(`codigo_vendedor`,`nombre_vendedor`,`codigo_sucursal`)
VALUES('1','Pedro Gatica',1),('2','Kimberly Soazo',1),('3','Joaquin Valenzuela',2),
('4','Pricilla Guzman',2),('5','Juan Perez',3),('6','Pablo Rojas',3);

-- -----------------------------------------------------
-- Insert Clientes
-- -----------------------------------------------------
INSERT INTO clientes(`rut_cliente`,`nombre_cliente`,`correo_cliente`,`telefono_cliente`,`direccion_cliente`)
VALUES('11111111-1','Simon Guzman','sguzman@gmail.com','123456789','Santiago 1260'),('22222222-2','Jazmin Fuenzalida','jfuenzalida@gmail.com','234124567','Maipu 2260'),
('33333333-3','Mario Gonzales','mgonzales@gmail.com','89785136','Apoquindo 1260'),('44444444-4','Franco Lemus','flemus@gmail.com','345212345','Estacion Central 260'),
('55555555-5','Maria Rojas','mrojas@gmail.com','589785219','Santiago 160'),('66666666-6','Claudia Riveros','criveros@gmail.com','894568469','Las Condes 1280');

-- -----------------------------------------------------
-- Insert Descuentos
-- -----------------------------------------------------
INSERT INTO descuentos(`descuento`)
VALUES(0),(5),(10),(15),(20),(25),(30),(35),(40),(45),(50),(55),(60),(65),(70),(75),(80),(85),(90),(95),(100);

-- -----------------------------------------------------
-- Insert Promociones
-- -----------------------------------------------------
INSERT INTO promociones(`codigo_promocion`,`nombre_promocion`,`porcentaje_promocion`)
VALUES(1,'Sin Promocion',0),(2,'Promocion 2x1',50);

-- -----------------------------------------------------
-- Insert Ventas
-- -----------------------------------------------------
INSERT INTO ventas(`codigo_vendedor`,`id_cliente`,`fecha_venta`,`hora_venta`)
VALUES('1',1,'0000-00-00','05:05:00'),('1',2,'2000-01-01','05:05:00'),('1',1,'2000-01-03','05:05:00'),('1',1,'2000-02-01','05:05:00'),
('1',1,'2000-01-02','05:05:00'),('1',1,'2000-01-11','05:05:00'),('1',1,'2000-01-11','05:05:00'),('1',1,'2000-01-08','05:05:00');

select * from ventas;
-- -----------------------------------------------------
-- Insert Productos
-- -----------------------------------------------------
INSERT INTO productos(`Id_producto`,`sku_producto`,`codigobarra_producto`,`nombre_producto`,`descripcion_producto`,`precio_neto`,`precio_iva`,`codigo_promocion`)
VALUES(2,'VAPO02MC','02-MC','Melon Calameno','Melon Calameno',1500,200,2)(2,'VAPO01MT','01-MT','Melon Tuna','Melon tuna',1500,200,2),('VAPO02PV','02-PV','Platano Verde','Platano Verde',1500,200,2),
('VAPO03MR','03-MR','Manzana ROja','Manzana Roja',1500,200,1),('VAPO04MV','04-MV','Manzana Verde','Manzana Verde',1500,200,1),
('VAPO05LE','05-LE','Lechuga Escarola','Lechuga Escarola',1500,200,1);

select * from productos
-- -----------------------------------------------------
-- Insert Detalle Venta
-- -----------------------------------------------------


INSERT INTO detalle_venta(`id_producto`,`codigo_venta`,`codigo_descuento`,`cantidad`,`total`)
VALUES(1,2,1,1,1800),(1,3,1,1,1800),(1,4,1,1,1800),(2,5,1,1,1800),(3,6,1,1,1800),(3,7,1,1,1800),
(3,8,1,1,1800),(4,9,1,1,1800);




select SUM(detalle_venta.cantidad), detalle_venta.codigo_detalle_venta, productos.nombre_producto, 
descuentos.descuento, detalle_venta.cantidad,  detalle_venta.total,
vendedores.nombre_vendedor,  clientes.nombre_cliente,
ventas.fecha_venta, ventas.hora_venta, sucursales.nombre_sucursal
FROM detalle_venta
JOIN ventas ON detalle_venta.codigo_venta = ventas.codigo_venta
JOIN Clientes ON ventas.id_cliente = Clientes.id_cliente
JOIN Vendedores ON ventas.codigo_vendedor = Vendedores.codigo_vendedor
join sucursales ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal
JOIN Descuentos ON detalle_venta.codigo_descuento = Descuentos.codigo_descuento
JOIN Productos ON detalle_venta.id_producto = Productos.id_producto
where ventas.fecha_venta  BETWEEN  '2000-01-01' and '2000-01-11' group by productos.nombre_producto 

select  productos.nombre_producto, 
descuentos.descuento, SUM(detalle_venta.cantidad), detalle_venta.total,  SUM(detalle_venta.total),
vendedores.nombre_vendedor,  clientes.nombre_cliente,
ventas.fecha_venta, ventas.hora_venta, sucursales.nombre_sucursal
FROM detalle_venta
JOIN ventas ON detalle_venta.codigo_venta = ventas.codigo_venta
JOIN Clientes ON ventas.id_cliente = Clientes.id_cliente
JOIN Vendedores ON ventas.codigo_vendedor = Vendedores.codigo_vendedor
join sucursales ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal
JOIN Descuentos ON detalle_venta.codigo_descuento = Descuentos.codigo_descuento
JOIN Productos ON detalle_venta.id_producto = Productos.id_producto
where sucursales.nombre_sucursal = 'Verduleria Apoquindo' GROUP BY Productos.nombre_producto;





SELECT sku_producto ,codigobarra_producto, 
nombre_producto , descripcion_producto , precio_neto , nombre_promocion
From productos 
join promociones on productos.codigo_promocion = promociones.codigo_promocion 
where  codigobarra_producto like '06-LE' and  sku_producto  like 'VAPO%'

SELECT sku_producto ,codigobarra_producto, 
nombre_producto , descripcion_producto , precio_neto , nombre_promocion
From productos 
join promociones on productos.codigo_promocion = promociones.codigo_promocion 
where  codigobarra_producto = '06-LE' 



select * from vendedores where nombre_vendedor like 'Pedr%' 


select * from ventas

 
 
 select * from detalle_venta;
 
 
 select detalle_venta.codigo_detalle_venta,ventas.codigo_venta, productos.nombre_producto,
descuentos.descuento, detalle_venta.cantidad,  detalle_venta.total,
vendedores.nombre_vendedor,  clientes.nombre_cliente, 
ventas.fecha_venta, ventas.hora_venta, sucursales.nombre_sucursal
FROM detalle_venta
JOIN ventas ON detalle_venta.codigo_venta = ventas.codigo_venta
JOIN Clientes ON ventas.id_cliente = Clientes.id_cliente
JOIN Vendedores ON ventas.codigo_vendedor = Vendedores.codigo_vendedor 
join sucursales ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal
JOIN Descuentos ON detalle_venta.codigo_descuento = Descuentos.codigo_descuento
JOIN Productos ON detalle_venta.id_producto = Productos.id_producto
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
SET FOREIGN_KEY_CHECKS = 1;