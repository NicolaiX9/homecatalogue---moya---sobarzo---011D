SET FOREIGN_KEY_CHECKS = 0;

-- 1. db_usuarios
INSERT INTO db_usuarios.tipo_usuario (id) VALUES (1), (2), (3)
ON DUPLICATE KEY UPDATE id=VALUES(id);

INSERT INTO db_usuarios.usuario (id) VALUES (1)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 2. db_productos
INSERT INTO db_productos.producto_categoria (id) VALUES (1), (2), (3)
ON DUPLICATE KEY UPDATE id=VALUES(id);

INSERT INTO db_productos.producto (id) VALUES (1), (2)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 3. db_transportistas
INSERT INTO db_transportistas.transportista (id) VALUES (1), (2)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 4. db_distribuidores
INSERT INTO db_distribuidores.distribuidor (id) VALUES (1), (2)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 5. db_carritos
INSERT INTO db_carritos.carrito (id) VALUES (1)
ON DUPLICATE KEY UPDATE id=VALUES(id);

INSERT INTO db_carritos.carrito_detalle (id) VALUES (1), (2)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 6. db_almacenes
INSERT INTO db_almacenes.almacen (id) VALUES (1), (2)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 7. db_stocks
INSERT INTO db_stocks.stock (id) VALUES (1), (2)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 8. db_ventas
INSERT INTO db_ventas.venta (id) VALUES (1)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 9. db_despachos
INSERT INTO db_despachos.despacho (id) VALUES (1)
ON DUPLICATE KEY UPDATE id=VALUES(id);

-- 10. db_comprobantes
INSERT INTO db_comprobantes.comprobantes (id) VALUES (1)
ON DUPLICATE KEY UPDATE id=VALUES(id);

SET FOREIGN_KEY_CHECKS = 1;