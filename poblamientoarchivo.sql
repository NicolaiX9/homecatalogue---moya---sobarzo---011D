-- =======================================================================
-- 1. LIMPIEZA TOTAL DE REGISTROS (Mantiene tablas y resetea contadores)
-- =======================================================================
SET FOREIGN_KEY_CHECKS = 0;

-- db_usuarios
DELETE FROM `db_usuarios`.`usuario` WHERE 1=1;
ALTER TABLE `db_usuarios`.`usuario` AUTO_INCREMENT = 1;
DELETE FROM `db_usuarios`.`tipo_usuario` WHERE 1=1;
ALTER TABLE `db_usuarios`.`tipo_usuario` AUTO_INCREMENT = 1;

-- db_productos
DELETE FROM `db_productos`.`producto` WHERE 1=1;
ALTER TABLE `db_productos`.`producto` AUTO_INCREMENT = 1;
DELETE FROM `db_productos`.`producto_categoria` WHERE 1=1;
ALTER TABLE `db_productos`.`producto_categoria` AUTO_INCREMENT = 1;

-- db_distribuidores
DELETE FROM `db_distribuidores`.`suministro` WHERE 1=1;
ALTER TABLE `db_distribuidores`.`suministro` AUTO_INCREMENT = 1;
DELETE FROM `db_distribuidores`.`distribuidor` WHERE 1=1;
ALTER TABLE `db_distribuidores`.`distribuidor` AUTO_INCREMENT = 1;

-- db_almacenes
DELETE FROM `db_almacenes`.`almacen` WHERE 1=1;
ALTER TABLE `db_almacenes`.`almacen` AUTO_INCREMENT = 1;

-- db_stocks
DELETE FROM `db_stocks`.`stock` WHERE 1=1;
ALTER TABLE `db_stocks`.`stock` AUTO_INCREMENT = 1;

-- db_carritos
DELETE FROM `db_carritos`.`carrito_detalle` WHERE 1=1;
ALTER TABLE `db_carritos`.`carrito_detalle` AUTO_INCREMENT = 1;
DELETE FROM `db_carritos`.`carrito` WHERE 1=1;
ALTER TABLE `db_carritos`.`carrito` AUTO_INCREMENT = 1;

-- db_ventas
DELETE FROM `db_ventas`.`venta` WHERE 1=1;
ALTER TABLE `db_ventas`.`venta` AUTO_INCREMENT = 1;

-- db_comprobantes
DELETE FROM `db_comprobantes`.`comprobantes` WHERE 1=1;
ALTER TABLE `db_comprobantes`.`comprobantes` AUTO_INCREMENT = 1;

-- db_transportistas
DELETE FROM `db_transportistas`.`transportista` WHERE 1=1;
ALTER TABLE `db_transportistas`.`transportista` AUTO_INCREMENT = 1;

-- db_despachos
DELETE FROM `db_despachos`.`despacho` WHERE 1=1;
ALTER TABLE `db_despachos`.`despacho` AUTO_INCREMENT = 1;

-- db_seguridad 
DELETE FROM `db_seguridad`.`usuarios` WHERE 1=1;
ALTER TABLE `db_seguridad`.`usuarios` AUTO_INCREMENT = 1;
DELETE FROM `db_seguridad`.`roles` WHERE 1=1;
ALTER TABLE `db_seguridad`.`roles` AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS = 1;


-- =======================================================================
-- 2. INSERCIÓN DE DATOS: OPERACIONES DEL NEGOCIO (LOGÍSTICA Y VENTAS)
-- =======================================================================

-- --- NIVEL 1: Tablas Maestras ---
INSERT INTO `db_usuarios`.`tipo_usuario` (`rol`) VALUES 
('ADMINISTRADOR'), ('CLIENTE');

INSERT INTO `db_productos`.`producto_categoria` (`categoria`) VALUES 
('Sillas de Escritorio'), ('Mesas de Reunión'), ('Iluminación LED');

INSERT INTO `db_distribuidores`.`distribuidor` (`rut_empresa`, `razon_social`, `telefono`, `email`, `calle_direccion`, `numero_direccion`) VALUES
('76.123.456-K', 'Distribuidora Ofisillas Ltda', 987654321, 'contacto@ofisillas.cl', 'Camino Los Alerces', '450'),
('88.987.654-3', 'Muebles Globales SA', 912345678, 'ventas@mueblesglobales.com', 'Av. Vitacura', '1200'),
('93.444.111-2', 'TecnoLuz Importaciones', 955544433, 'info@tecnoluz.cl', 'Industrial San James', '88');

INSERT INTO `db_almacenes`.`almacen` (`calle_direccion`, `numero_direccion`) VALUES
('Avenida Central', '1024'), ('Calle Industrial', '55'), ('Ruta Norte KM 12', 'S/N');

INSERT INTO `db_transportistas`.`transportista` (`nombre`, `rut`, `email`) VALUES
('Fletes Express Chile', '77.345.910-4', 'contacto@fletesexpress.cl'),
('Envíos Rápidos SpA', '81.555.222-K', 'logistica@enviosrapidos.cl'),
('Repartos Pro', '79.111.888-7', 'despachos@repartospro.cl');

-- --- NIVEL 2: Entidades del Negocio Vinculadas ---
INSERT INTO `db_usuarios`.`usuario` (`run`, `nombre`, `email`, `password`, `tipo_usuario_id`) VALUES
('12345678-9', 'Carlos Mendoza', 'carlos@empresa.com', '$2a$10$R9hZ...', 1),
('18765432-1', 'María José López', 'mariajose@gmail.com', '$2a$10$K7xF...', 2),
('15444333-K', 'Juan Pérez Gutiérrez', 'juan.perez@gmail.com', '$2a$10$M3wQ...', 2);

INSERT INTO `db_productos`.`producto` (`nombre`, `descripcion`, `precio`, `producto_categoria_id`) VALUES
('Silla Ergonómica Pro', 'Silla con soporte lumbar ajustable', 85000, 1),
('Escritorio Madera Noble', 'Mesa de reuniones de 2 metros', 150000, 2),
('Lámpara de Escritorio Touch', 'Lámpara LED dimerizable', 25000, 3);

INSERT INTO `db_carritos`.`carrito` (`total`, `id_usuario`) VALUES 
(135000, 2), (150000, 1), (25000, 3);

INSERT INTO `db_distribuidores`.`suministro` (`id_producto`, `costo`, `cantidad`, `distribuidor_id`) VALUES
(1, 45000, 200, 1), (2, 90000, 50, 2), (3, 12000, 500, 3);

INSERT INTO `db_stocks`.`stock` (`cantidad`, `id_producto`, `id_almacen`) VALUES
(45, 1, 1), (10, 2, 2), (80, 3, 3);

-- --- NIVEL 3: Procesos Operativos ---
INSERT INTO `db_carritos`.`carrito_detalle` (`id_producto`, `cantidad`, `carrito_id`) VALUES
(1, 1, 1), (2, 1, 2), (3, 1, 3);

INSERT INTO `db_ventas`.`venta` (`total`, `fecha`, `id_carrito`) VALUES
(135000, '2026-07-01', 1), (150000, '2026-07-02', 2), (25000, '2026-07-02', 3);

-- --- NIVEL 4: Salidas ---
INSERT INTO `db_comprobantes`.`comprobantes` (`nmro_comprobante`, `pdf_url`, `total`, `id_venta`, `fecha_emision`) VALUES
('FACT-000101', 'https://storage.empresa.internal/pdfs/f-101.pdf', 135000, 1, '2026-07-01'),
('BOLE-000542', 'https://storage.empresa.internal/pdfs/b-542.pdf', 150000, 2, '2026-07-02'),
('BOLE-000543', 'https://storage.empresa.internal/pdfs/b-543.pdf', 25000, 3, '2026-07-02');

INSERT INTO `db_despachos`.`despacho` (`fecha_desp`, `calle_direccion`, `num_direccion`, `id_venta`) VALUES
('2026-07-03', 'Avenida Los Pajaritos', '1420', 1),
('2026-07-04', 'Pasaje Las Violetas', '302', 2),
('2026-07-05', 'Calle Nueva Extremadura', '9050', 3);


-- =======================================================================
-- 3. MICROSERVICIO DE SEGURIDAD (Forzado de llaves y mapeo estricto)
-- =======================================================================
USE `db_seguridad`;

-- Desactivamos revisiones globales para saltar el error #1452 y #1062 de una vez por todas
SET FOREIGN_KEY_CHECKS = 0;

-- 1. Insertamos los roles maestros asegurando sus IDs fijos (1 y 2)
INSERT INTO `db_seguridad`.`roles` (`id`, `nombre_rol`) VALUES
(1, 'ADMINISTRADOR'),
(2, 'CLIENTE');

-- 2. Insertamos los usuarios forzando sus IDs (1, 2, 3) para evitar el error duplicado '0'
INSERT INTO `db_seguridad`.`usuarios` (`id`, `nombre_usuario`, `contrasena`, `correo`) VALUES
(1, 'carlos.admin', '$2a$10$R9hZ...', 'carlos@empresa.com'),
(2, 'mariajose.cliente', '$2a$10$K7xF...', 'mariajose@gmail.com'),
(3, 'juan.perez', '123456', 'juanperez@gmail.com');

-- 3. Vinculamos las relaciones directamente en las columnas que creó Hibernate
INSERT INTO `db_seguridad`.`roles` (`usuario id`, `rol_id`) VALUES
(1, 1), -- carlos.admin es ADMINISTRADOR
(2, 2), -- mariajose.cliente es CLIENTE
(3, 2); -- juan.perez es CLIENTE

-- Volvemos a asegurar la integridad relacional de la base de datos
SET FOREIGN_KEY_CHECKS = 1;