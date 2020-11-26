
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
VALUES('001','Pedro Gatica',1),('002','Kimberly Soazo',1),('003','Joaquin Valenzuela',2),
('004','Pricilla Guzman',2),('005','Juan Perez',3),('006','Pablo Rojas',3);

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
VALUES(1,'Sin Promocion',0),(2,'Promocion 2x1',50),(3,'Promocion 3x1',80);

-- -----------------------------------------------------
-- Insert Ventas
-- -----------------------------------------------------
/*INSERT INTO ventas(`codigo_vendedor`,`id_cliente`,`fecha_venta`,`hora_venta`)
VALUES('1',1,'0000-00-00','05:05:00'),('1',2,'2000-01-01','05:05:00'),('1',1,'2000-01-03','05:05:00'),('1',1,'2000-02-01','05:05:00'),
('1',1,'2000-01-02','05:05:00'),('1',1,'2000-01-11','05:05:00'),('1',1,'2000-01-11','05:05:00'),('1',1,'2000-01-08','05:05:00');*/

select * from sucursales;
select * from historico_productos;
-- -----------------------------------------------------
-- Insert Productos
-- -----------------------------------------------------
/*INSERT INTO productos(`Id_producto`,`sku_producto`,`codigobarra_producto`,`nombre_producto`,`descripcion_producto`,`precio_neto`,`precio_iva`,`codigo_promocion`)
VALUES(2,'VAPO02MC','02-MC','Melon Calameno','Melon Calameno',1500,200,2)(2,'VAPO01MT','01-MT','Melon Tuna','Melon tuna',1500,200,2),('VAPO02PV','02-PV','Platano Verde','Platano Verde',1500,200,2),
('VAPO03MR','03-MR','Manzana ROja','Manzana Roja',1500,200,1),('VAPO04MV','04-MV','Manzana Verde','Manzana Verde',1500,200,1),
('VAPO05LE','05-LE','Lechuga Escarola','Lechuga Escarola',1500,200,1);*/


-- -----------------------------------------------------
--
-- -----------------------------------------------------



-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-11-2020 a las 00:04:49
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `verduleria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `rut_cliente` varchar(10) NOT NULL,
  `nombre_cliente` varchar(45) NOT NULL,
  `correo_cliente` varchar(45) NOT NULL,
  `telefono_cliente` varchar(9) NOT NULL,
  `direccion_cliente` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `rut_cliente`, `nombre_cliente`, `correo_cliente`, `telefono_cliente`, `direccion_cliente`) VALUES
(1, '11111111-1', 'Simon Guzman', 'sguzman@gmail.com', '123456789', 'Santiago 1260'),
(2, '22222222-2', 'Jazmin Fuenzalida', 'jfuenzalida@gmail.com', '234124567', 'Maipu 2260'),
(3, '33333333-3', 'Mario Gonzales', 'mgonzales@gmail.com', '89785136', 'Apoquindo 1260'),
(4, '44444444-4', 'Franco Lemus', 'flemus@gmail.com', '345212345', 'Estacion Central 260'),
(5, '55555555-5', 'Maria Rojas', 'mrojas@gmail.com', '589785219', 'Santiago 160'),
(6, '66666666-6', 'Claudia Riveros', 'criveros@gmail.com', '894568469', 'Las Condes 1280');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `codigo_descuento` int(11) NOT NULL,
  `descuento` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `descuentos`
--

INSERT INTO `descuentos` (`codigo_descuento`, `descuento`) VALUES
(1, 0),
(2, 5),
(3, 10),
(4, 15),
(5, 20),
(6, 25),
(7, 30),
(8, 35),
(9, 40),
(10, 45),
(11, 50),
(12, 55),
(13, 60),
(14, 65),
(15, 70),
(16, 75),
(17, 80),
(18, 85),
(19, 90),
(20, 95),
(21, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `codigo_detalle_venta` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `codigo_venta` int(11) NOT NULL,
  `codigo_descuento` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historico_productos`
--

CREATE TABLE `historico_productos` (
  `codigo_historico_p` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `precio_producto_h` int(11) NOT NULL,
  `fecha_h` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `historico_productos`
--

INSERT INTO `historico_productos` (`codigo_historico_p`, `id_producto`, `precio_producto_h`, `fecha_h`) VALUES
(1, 1, 420, '2020-11-25'),
(2, 2, 420, '2020-11-25'),
(3, 3, 294, '2020-11-25'),
(4, 4, 336, '2020-11-25'),
(5, 5, 252, '2020-11-25'),
(6, 6, 168, '2020-11-25'),
(7, 7, 672, '2020-11-25'),
(8, 8, 672, '2020-11-25'),
(9, 9, 1681, '2020-11-25'),
(10, 10, 8403, '2020-11-25'),
(11, 11, 8403, '2020-11-25'),
(12, 12, 420, '2020-11-25'),
(13, 13, 420, '2020-11-25'),
(14, 14, 294, '2020-11-25'),
(15, 15, 336, '2020-11-25'),
(16, 16, 252, '2020-11-25'),
(17, 17, 168, '2020-11-25'),
(18, 18, 672, '2020-11-25'),
(19, 19, 672, '2020-11-25'),
(20, 20, 1681, '2020-11-25'),
(21, 21, 8403, '2020-11-25'),
(22, 22, 8403, '2020-11-25'),
(23, 23, 420, '2020-11-25'),
(24, 24, 420, '2020-11-25'),
(25, 25, 294, '2020-11-25'),
(26, 26, 336, '2020-11-25'),
(27, 27, 252, '2020-11-25'),
(28, 28, 168, '2020-11-25'),
(29, 29, 672, '2020-11-25'),
(30, 30, 672, '2020-11-25'),
(31, 31, 1681, '2020-11-25'),
(32, 32, 8403, '2020-11-25'),
(33, 33, 8403, '2020-11-25'),
(34, 34, 420, '2020-11-25'),
(35, 35, 420, '2020-11-25'),
(36, 36, 294, '2020-11-25'),
(37, 37, 336, '2020-11-25'),
(38, 38, 252, '2020-11-25'),
(39, 39, 168, '2020-11-25'),
(40, 40, 672, '2020-11-25'),
(41, 41, 672, '2020-11-25'),
(42, 42, 1681, '2020-11-25'),
(43, 43, 8403, '2020-11-25'),
(44, 44, 8403, '2020-11-25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL,
  `sku_producto` varchar(8) NOT NULL,
  `codigobarra_producto` varchar(5) NOT NULL,
  `nombre_producto` varchar(45) NOT NULL,
  `descripcion_producto` varchar(200) NOT NULL,
  `precio_neto` float NOT NULL,
  `precio_iva` float NOT NULL,
  `codigo_promocion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `sku_producto`, `codigobarra_producto`, `nombre_producto`, `descripcion_producto`, `precio_neto`, `precio_iva`, `codigo_promocion`) VALUES
(1, 'VAPO01PR', '01-PR', 'Pimenton Rojo', 'Pimenton Rojo', 420, 500, 1),
(2, 'VAPO02PV', '02-PV', 'Pimenton Verde', 'Pimenton Verde', 420, 500, 1),
(3, 'VAPO03ZH', '03-ZH', 'Zanahoria', 'Zanahoria', 294, 350, 1),
(4, 'VAPO04CB', '04-CB', 'Cebolla', 'Cebolla', 336, 400, 1),
(5, 'VAPO05PP', '05-PP', 'Pepino', 'Pepino', 252, 300, 1),
(6, 'VAPO06PL', '06-PL', 'Platano', 'Platano', 168, 200, 1),
(7, 'VAPO07CL', '07-CL', 'Cilantro', 'Cilantro', 672, 800, 1),
(8, 'VAPO08PG', '08-PG', 'Perejil', 'Perejil', 672, 800, 1),
(9, 'VAPO09LE', '09-LE', 'Lechuga Escarola', 'Lechuga Escarola', 1681, 2000, 2),
(10, 'VAPO10SD', '10-SD', 'Sandia', 'Sandia', 8403, 10000, 2),
(11, 'VAPO11ML', '11-ML', 'Melon', 'Melon', 8403, 10000, 3),
(12, 'VBIO01PR', '01-PR', 'Pimenton Rojo', 'Pimenton Rojo', 420, 500, 1),
(13, 'VBIO02PV', '02-PV', 'Pimenton Verde', 'Pimenton Verde', 420, 500, 1),
(14, 'VBIO03ZH', '03-ZH', 'Zanahoria', 'Zanahoria', 294, 350, 1),
(15, 'VBIO04CB', '04-CB', 'Cebolla', 'Cebolla', 336, 400, 1),
(16, 'VBIO05PP', '05-PP', 'Pepino', 'Pepino', 252, 300, 1),
(17, 'VBIO06PL', '06-PL', 'Platano', 'Platano', 168, 200, 1),
(18, 'VBIO07CL', '07-CL', 'Cilantro', 'Cilantro', 672, 800, 1),
(19, 'VBIO08PG', '08-PG', 'Perejil', 'Perejil', 672, 800, 1),
(20, 'VBIO09LE', '09-LE', 'Lechuga Escarola', 'Lechuga Escarola', 1681, 2000, 2),
(21, 'VBIO10SD', '10-SD', 'Sandia', 'Sandia', 8403, 10000, 2),
(22, 'VBIO11ML', '11-ML', 'Melon', 'Melon', 8403, 10000, 3),
(23, 'VCON01PR', '01-PR', 'Pimenton Rojo', 'Pimenton Rojo', 420, 500, 1),
(24, 'VCON02PV', '02-PV', 'Pimenton Verde', 'Pimenton Verde', 420, 500, 1),
(25, 'VCON03ZH', '03-ZH', 'Zanahoria', 'Zanahoria', 294, 350, 1),
(26, 'VCON04CB', '04-CB', 'Cebolla', 'Cebolla', 336, 400, 1),
(27, 'VCON05PP', '05-PP', 'Pepino', 'Pepino', 252, 300, 1),
(28, 'VCON06PL', '06-PL', 'Platano', 'Platano', 168, 200, 1),
(29, 'VCON07CL', '07-CL', 'Cilantro', 'Cilantro', 672, 800, 1),
(30, 'VCON08PG', '08-PG', 'Perejil', 'Perejil', 672, 800, 1),
(31, 'VCON09LE', '09-LE', 'Lechuga Escarola', 'Lechuga Escarola', 1681, 2000, 2),
(32, 'VCON10SD', '10-SD', 'Sandia', 'Sandia', 8403, 10000, 2),
(33, 'VCON11ML', '11-ML', 'Melon', 'Melon', 8403, 10000, 3),
(34, 'VONL01PR', '01-PR', 'Pimenton Rojo', 'Pimenton Rojo', 420, 500, 1),
(35, 'VONL02PV', '02-PV', 'Pimenton Verde', 'Pimenton Verde', 420, 500, 1),
(36, 'VONL03ZH', '03-ZH', 'Zanahoria', 'Zanahoria', 294, 350, 1),
(37, 'VONL04CB', '04-CB', 'Cebolla', 'Cebolla', 336, 400, 1),
(38, 'VONL05PP', '05-PP', 'Pepino', 'Pepino', 252, 300, 1),
(39, 'VONL06PL', '06-PL', 'Platano', 'Platano', 168, 200, 1),
(40, 'VONL07CL', '07-CL', 'Cilantro', 'Cilantro', 672, 800, 1),
(41, 'VONL08PG', '08-PG', 'Perejil', 'Perejil', 672, 800, 1),
(42, 'VONL09LE', '09-LE', 'Lechuga Escarola', 'Lechuga Escarola', 1681, 2000, 2),
(43, 'VONL10SD', '10-SD', 'Sandia', 'Sandia', 8403, 10000, 2),
(44, 'VONL11ML', '11-ML', 'Melon', 'Melon', 8403, 10000, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones`
--

CREATE TABLE `promociones` (
  `codigo_promocion` int(11) NOT NULL,
  `nombre_promocion` varchar(45) NOT NULL,
  `porcentaje_promocion` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `promociones`
--

INSERT INTO `promociones` (`codigo_promocion`, `nombre_promocion`, `porcentaje_promocion`) VALUES
(1, 'Sin Promocion', 0),
(2, 'Promocion 2x1', 50),
(3, 'Promocion 3x1', 80);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `codigo_sucursal` int(11) NOT NULL,
  `nombre_sucursal` varchar(45) NOT NULL,
  `direccion_sucursal` varchar(45) NOT NULL,
  `id_tipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`codigo_sucursal`, `nombre_sucursal`, `direccion_sucursal`, `id_tipo`) VALUES
(1, 'Verduleria Apoquindo', 'Apoquindo 1025', 1),
(2, 'Verduleria Bio-Bio', 'Carrera 123', 1),
(3, 'Verduleria Concepcion', 'Rosas 458', 1),
(4, 'Verduleria Online', 'Nueva Providencia 1001', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_sucursal`
--

CREATE TABLE `tipos_sucursal` (
  `id_tipo` int(11) NOT NULL,
  `nombre_tipo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipos_sucursal`
--

INSERT INTO `tipos_sucursal` (`id_tipo`, `nombre_tipo`) VALUES
(1, 'Fisica'),
(2, 'Online');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `codigo_vendedor` int(11) NOT NULL,
  `nombre_vendedor` varchar(45) NOT NULL,
  `codigo_sucursal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`codigo_vendedor`, `nombre_vendedor`, `codigo_sucursal`) VALUES
(1, 'Pedro Gatica', 1),
(2, 'Kimberly Soazo', 1),
(3, 'Joaquin Valenzuela', 2),
(4, 'Pricilla Guzman', 2),
(5, 'Juan Perez', 3),
(6, 'Pablo Rojas', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `codigo_venta` int(11) NOT NULL,
  `codigo_vendedor` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `fecha_venta` date NOT NULL,
  `hora_venta` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`codigo_descuento`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`codigo_detalle_venta`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `codigo_venta` (`codigo_venta`),
  ADD KEY `codigo_descuento` (`codigo_descuento`);

--
-- Indices de la tabla `historico_productos`
--
ALTER TABLE `historico_productos`
  ADD PRIMARY KEY (`codigo_historico_p`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `codigo_promocion` (`codigo_promocion`);

--
-- Indices de la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD PRIMARY KEY (`codigo_promocion`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`codigo_sucursal`),
  ADD KEY `id_tipo` (`id_tipo`);

--
-- Indices de la tabla `tipos_sucursal`
--
ALTER TABLE `tipos_sucursal`
  ADD PRIMARY KEY (`id_tipo`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`codigo_vendedor`),
  ADD KEY `codigo_sucursal` (`codigo_sucursal`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`codigo_venta`),
  ADD KEY `codigo_vendedor` (`codigo_vendedor`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `codigo_descuento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `codigo_detalle_venta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historico_productos`
--
ALTER TABLE `historico_productos`
  MODIFY `codigo_historico_p` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `codigo_sucursal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipos_sucursal`
--
ALTER TABLE `tipos_sucursal`
  MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `codigo_venta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  ADD CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`codigo_venta`) REFERENCES `ventas` (`codigo_venta`),
  ADD CONSTRAINT `detalle_venta_ibfk_3` FOREIGN KEY (`codigo_descuento`) REFERENCES `descuentos` (`codigo_descuento`);

--
-- Filtros para la tabla `historico_productos`
--
ALTER TABLE `historico_productos`
  ADD CONSTRAINT `historico_productos_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`codigo_promocion`) REFERENCES `promociones` (`codigo_promocion`);

--
-- Filtros para la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD CONSTRAINT `sucursales_ibfk_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipos_sucursal` (`id_tipo`);

--
-- Filtros para la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD CONSTRAINT `vendedores_ibfk_1` FOREIGN KEY (`codigo_sucursal`) REFERENCES `sucursales` (`codigo_sucursal`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`codigo_vendedor`) REFERENCES `vendedores` (`codigo_vendedor`),
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;









