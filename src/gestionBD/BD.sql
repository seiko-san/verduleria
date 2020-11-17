
CREATE database verduleria;
USE verduleria ;

-- -----------------------------------------------------
-- Table Clientes
-- -----------------------------------------------------
CREATE TABLE Clientes (
  `rut_cliente` INT NOT NULL,
  `nombre_cliente` VARCHAR(45) NOT NULL,
  `correo_cliente` VARCHAR(45) NOT NULL,
  `telefono_cliente` INT NOT NULL,
  `direccion_cliente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`rut_cliente`));


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
  `sku_producto` INT NOT NULL,
  `codigobarra_producto` VARCHAR(200) NOT NULL,
  `nombre_producto` VARCHAR(45) NOT NULL,
  `descripcion_producto` VARCHAR(200) NOT NULL,
  `precio_neto` FLOAT NOT NULL,
  `precio_iva` FLOAT NOT NULL,
  `codigo_promocion` INT NOT NULL,
  PRIMARY KEY (`sku_producto`),
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
  `rut_cliente` INT NOT NULL,
  `fecha_venta` DATE NOT NULL,
  PRIMARY KEY (`codigo_venta`),
    FOREIGN KEY (`codigo_vendedor`) REFERENCES Vendedores (`codigo_vendedor`),
    FOREIGN KEY (`rut_cliente`)REFERENCES Clientes (`rut_cliente`));


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
  `Productos_sku_producto` INT NOT NULL,
  `codigo_venta` INT NOT NULL,
  `codigo_descuento` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `cantidad` INT NOT NULL,
  `total` INT NOT NULL,
  PRIMARY KEY (`codigo_detalle_venta`),
    FOREIGN KEY (`Productos_sku_producto`) REFERENCES Productos (`sku_producto`),
    FOREIGN KEY (`codigo_venta`) REFERENCES Ventas (`codigo_venta`),
    FOREIGN KEY (`codigo_descuento`) REFERENCES Descuentos (`codigo_descuento`));


-- -----------------------------------------------------
-- Table Historico_Productos
-- -----------------------------------------------------
CREATE TABLE Historico_Productos (
  `codigo_historico_p` INT NOT NULL AUTO_INCREMENT,
  `sku_producto` INT NOT NULL,
  `precio_producto_h` INT NOT NULL,
  `fecha_h` DATE NOT NULL,
  PRIMARY KEY (`codigo_historico_p`),
    FOREIGN KEY (`sku_producto`) REFERENCES Productos (`sku_producto`));

