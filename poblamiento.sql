
-- Tipo_usuario
INSERT INTO Tipo_usuario (rol) VALUES ('Administrador'); -- ID 1
INSERT INTO Tipo_usuario (rol) VALUES ('Cliente');       -- ID 2
INSERT INTO Tipo_usuario (rol) VALUES ('Vendedor');      -- ID 3

-- Producto_Categoria
INSERT INTO Producto_Categoria (categoria) VALUES ('Electrónica');  -- ID 1
INSERT INTO Producto_Categoria (categoria) VALUES ('Hogar y Cocina'); -- ID 2
INSERT INTO Producto_Categoria (categoria) VALUES ('Deportes');       -- ID 3

-- Almacen
INSERT INTO Almacen (calle_direccion, numero_direccion, Stock_id) VALUES ('Av. Central', '1020', NULL);
INSERT INTO Almacen (calle_direccion, numero_direccion, Stock_id) VALUES ('Calle Industrial', '450', NULL);

-- Transportista
INSERT INTO Transportista (nombre, email, calle_direccion, numero_direccion, Transportista_ID) 
VALUES ('ChileExpress', 'contacto@chileexpress.cl', 'Américo Vespucio', '1500', 101);
INSERT INTO Transportista (nombre, email, calle_direccion, numero_direccion, Transportista_ID) 
VALUES ('Starken', 'soporte@starken.cl', 'Ruta 5 Sur', '2200', 102);

-- Distribuidor
INSERT INTO Distribuidor (rut_empresa, razon_social, telefono, email, calle_direccion, numero_direccion) 
VALUES ('76.123.456-7', 'Distribuidora Tech S.A.', '+56911112222', 'ventas@techdist.cl', 'Av. Vitacura', '4000');
INSERT INTO Distribuidor (rut_empresa, razon_social, telefono, email, calle_direccion, numero_direccion) 
VALUES ('88.987.654-3', 'Logística Global Ltda', '+56933334444', 'contacto@loglobal.com', 'San Diego', '850');


-- Registramos usuarios con Carrito_id en NULL inicialmente
INSERT INTO Usuario (nombre, email, password, id_rol, Carrito_id, Tipo_usuario_id) 
VALUES ('Juan Pérez', 'juan.perez@email.com', 'hash_1', 2, NULL, 2); -- Asume ID 1
INSERT INTO Usuario (nombre, email, password, id_rol, Carrito_id, Tipo_usuario_id) 
VALUES ('María López', 'maria.lopez@email.com', 'hash_2', 2, NULL, 2); -- Asume ID 2

-- Creamos los carritos amarrados a los IDs de usuario autoincrementados (1 y 2)
INSERT INTO Carrito (total, id_usuario, Usuario_id, Venta_Venta_ID) VALUES (15000, 1, 1, NULL); -- Asume ID 1
INSERT INTO Carrito (total, id_usuario, Usuario_id, Venta_Venta_ID) VALUES (45000, 2, 2, NULL); -- Asume ID 2

-- Actualizamos el Usuario con el ID del carrito correspondiente para cerrar el ciclo
UPDATE Usuario SET Carrito_id = 1 WHERE id = 1;
UPDATE Usuario SET Carrito_id = 2 WHERE id = 2;


INSERT INTO Producto (nombre, descripcion, precio, id_categoria, Carrito_Detalle_id, Producto_Categoria_id) 
VALUES ('Audífonos Bluetooth', 'Inalámbricos con cancelación de ruido', 15000, 1, NULL, 1); -- Asume ID 1
INSERT INTO Producto (nombre, descripcion, precio, id_categoria, Carrito_Detalle_id, Producto_Categoria_id) 
VALUES ('Cafetera Eléctrica', 'Cafetera de goteo para 12 tazas', 30000, 2, NULL, 2); -- Asume ID 2
INSERT INTO Producto (nombre, descripcion, precio, id_categoria, Carrito_Detalle_id, Producto_Categoria_id) 
VALUES ('Balón de Fútbol', 'Balón oficial tamaño 5', 15000, 3, NULL, 3); -- Asume ID 3

-- Suministros (Relaciona Producto y Distribuidor maestros)
INSERT INTO Suministro (id_producto, id_distribuidor, costo, cantidad, Producto_id, Distribuidor_id) 
VALUES (1, 1, 8000, 100, 1, 1);
INSERT INTO Suministro (id_producto, id_distribuidor, costo, cantidad, Producto_id, Distribuidor_id) 
VALUES (2, 1, 18000, 50, 2, 1);

-- Stock (Relaciona Producto y Almacen maestros)
INSERT INTO Stock (id_producto, cantidad, id_almacen, Producto_id) VALUES (1, 50, 1, 1);
INSERT INTO Stock (id_producto, cantidad, id_almacen, Producto_id) VALUES (2, 30, 1, 2);
INSERT INTO Stock (id_producto, cantidad, id_almacen, Producto_id) VALUES (3, 120, 2, 3);


-- Carrito_Detalle
INSERT INTO Carrito_Detalle (id_carrito, id_producto, cantidad, Carrito_id, Producto_id) VALUES (1, 1, 1, 1, 1);
INSERT INTO Carrito_Detalle (id_carrito, id_producto, cantidad, Carrito_id, Producto_id) VALUES (2, 2, 1, 2, 2);
INSERT INTO Carrito_Detalle (id_carrito, id_producto, cantidad, Carrito_id, Producto_id) VALUES (2, 3, 1, 2, 3);

-- Venta (Venta_ID actúa como la PK manual/incremental según tu flujo)
INSERT INTO Venta (Carrito_id, id_carrito, total, fecha, Comprobantes_id, Despacho_id, Venta_ID) 
VALUES (1, 1, 15000, TO_DATE('2026-06-20', 'YYYY-MM-DD'), 1, 1, 1001);
INSERT INTO Venta (Carrito_id, id_carrito, total, fecha, Comprobantes_id, Despacho_id, Venta_ID) 
VALUES (2, 2, 45000, TO_DATE('2026-06-21', 'YYYY-MM-DD'), 2, 2, 1002);

-- Actualizar el Carrito con la Venta efectuada
UPDATE Carrito SET Venta_Venta_ID = 1001 WHERE id = 1;
UPDATE Carrito SET Venta_Venta_ID = 1002 WHERE id = 2;

-- Comprobantes
INSERT INTO Comprobantes (nmro_comprobante, pdf_url, total, id_venta, fecha_emision, Venta_Venta_ID) 
VALUES (55001, 'https://storage.empresa.com/facturas/f-55001.pdf', 15000, 1001, TO_DATE('2026-06-20', 'YYYY-MM-DD'), 1001);
INSERT INTO Comprobantes (nmro_comprobante, pdf_url, total, id_venta, fecha_emision, Venta_Venta_ID) 
VALUES (55002, 'https://storage.empresa.com/facturas/f-55002.pdf', 45000, 1002, TO_DATE('2026-06-21', 'YYYY-MM-DD'), 1002);

-- Despacho (Usa el Transportista_ID que definimos en el paso 1, ej: 101 y 102)
INSERT INTO Despacho (fecha_desp, calle_direccion, num_direccion, id_venta, Transportista_Transportista_ID) 
VALUES (TO_DATE('2026-06-21', 'YYYY-MM-DD'), 'Av. Siempreviva', 742, 1001, 101);
INSERT INTO Despacho (fecha_desp, calle_direccion, num_direccion, id_venta, Transportista_Transportista_ID) 
VALUES (TO_DATE('2026-06-22', 'YYYY-MM-DD'), 'Calle Falsa', 123, 1002, 102);

COMMIT;