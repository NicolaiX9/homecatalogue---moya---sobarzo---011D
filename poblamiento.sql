-- 1. DESACTIVAR LLAVES FORÁNEAS (Esto evita el maldito error #1452)
SET FOREIGN_KEY_CHECKS = 0;

-- 2. LIMPIAR DATOS PREVIOS (Para que los IDs incremental arranquen limpios)
TRUNCATE TABLE usuario;
TRUNCATE TABLE tipo_usuario;
TRUNCATE TABLE carrito;
TRUNCATE TABLE carrito_detalle;
TRUNCATE TABLE producto;
TRUNCATE TABLE producto_categoria;
TRUNCATE TABLE stock;
TRUNCATE TABLE almacen;
TRUNCATE TABLE suministro;
TRUNCATE TABLE distribuidor;
TRUNCATE TABLE transportista;
TRUNCATE TABLE venta;
TRUNCATE TABLE comprobantes;
TRUNCATE TABLE despacho;

-- 3. POBLAR TABLAS MAESTRAS
-- tipo_usuario
INSERT INTO tipo_usuario (id, rol) VALUES (1, 'Administrador');
INSERT INTO tipo_usuario (id, rol) VALUES (2, 'Cliente');
INSERT INTO tipo_usuario (id, rol) VALUES (3, 'Vendedor');

-- producto_categoria
INSERT INTO producto_categoria (id, categoria) VALUES (1, 'Electrónica');
INSERT INTO producto_categoria (id, categoria) VALUES (2, 'Hogar y Cocina');

-- almacen
INSERT INTO almacen (id, calle_direccion, numero_direccion) VALUES (1, 'Hugo Bravo', '7455');

-- distribuidor
INSERT INTO distribuidor (id, rut_empresa, razon_social, telefono, email, calle_direccion, numero_direccion) 
VALUES (1, '23435345-4', 'Distribuidora Tech S.A.', 911112222, 'ventas@tech.cl', 'Av. Vitacura', '4000');

-- transportista
INSERT INTO transportista (id, nombre, rut, email) 
VALUES (1, 'Correos de Chile', '66543765-2', 'contacto@correos.cl');


-- 4. POBLAR TABLAS DEPENDIENTES (Usando IDs fijos para asegurar el mapeo de Hibernate)
-- usuario
INSERT INTO usuario (id, run, nombre, email, password, tipo_usuario_id) 
VALUES (1, '28675876-2', 'Javier Barrera', 'javierbarrer@gmail.com', 'password123', 2); 

-- carrito
INSERT INTO carrito (id, total, id_usuario) VALUES (1, 15000, 1); 

-- producto
INSERT INTO producto (id, nombre, descripcion, precio, producto_categoria_id) 
VALUES (1, 'Audífonos Bluetooth', 'Inalámbricos con cancelación de ruido', 15000, 1); 

-- carrito_detalle
INSERT INTO carrito_detalle (id, id_producto, cantidad, carrito_id) VALUES (1, 1, 1, 1);

-- suministro
INSERT INTO suministro (id, id_producto, costo, cantidad, distribuidor_id) VALUES (1, 1, 8000, 50, 1);

-- stock
INSERT INTO stock (id, cantidad, id_producto, id_almacen) VALUES (1, 40, 1, 1);

-- venta
INSERT INTO venta (id, total, fecha, id_carrito) VALUES (1, 15000, '2026-05-20', 1); 

-- comprobantes
INSERT INTO comprobantes (id, nmro_comprobante, pdf_url, total, id_venta, fecha_emision) 
VALUES (1, 'F-000123', 'https://storage.empresa.com/pdf/f-000123.pdf', 15000, 1, '2026-05-20');

-- despacho
INSERT INTO despacho (id, fecha_desp, calle_direccion, num_direccion, id_venta) 
VALUES (1, '2026-05-21', 'Hugo Bravo', '7455', 1);


-- 5. REACTIVAR VALIDADOR DE LLAVES FORÁNEAS (Dejamos la BD segura de nuevo)
SET FOREIGN_KEY_CHECKS = 1;