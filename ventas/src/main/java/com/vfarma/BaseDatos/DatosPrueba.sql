-- Insertar datos en la tabla Usuario
INSERT INTO Usuario (ClaveEmpleado, Nombre, Sucursal, Turno, Rol, Contrasena) VALUES
('EMP001', 'Juan Perez', 'Sucursal Norte', 'Matutino', 'Administrador', 'password1'),
('EMP002', 'Ana Gómez', 'Sucursal Centro', 'Vespertino', 'Cajero', 'password2'),
('EMP003', 'Luis Torres', 'Sucursal Sur', 'Nocturno', 'Supervisor', 'password3');

-- Insertar datos en la tabla Categoria
INSERT INTO Categoria (Nombre) VALUES
('Electrónica'),
('Alimentos'),
('Limpieza');

-- Insertar datos en la tabla Familia
INSERT INTO Familia (Nombre) VALUES
('Computadoras'),
('Frutas'),
('Detergentes');

-- Insertar datos en la tabla Proveedor
INSERT INTO Proveedor (Nombre) VALUES
('Proveedor A'),
('Proveedor B'),
('Proveedor C');

-- Insertar datos en la tabla Producto
INSERT INTO Producto (Nombre, Descripcion, Precio, CategoriaID, FamiliaID, ProveedorID) VALUES
('Laptop', 'Laptop de alta gama', 15000.00, 1, 1, 1),
('Manzana', 'Manzana roja importada', 30.00, 2, 2, 2),
('Detergente', 'Detergente multiusos', 50.00, 3, 3, 3);

-- Insertar datos en la tabla Inventario
INSERT INTO Inventario (ProductoID, Cantidad) VALUES
(1, 100),
(2, 200),
(3, 150);

-- Insertar datos en la tabla Mov
INSERT INTO Mov (Tipo) VALUES
('Venta'),
('Devolución');

-- Insertar datos en la tabla Venta
INSERT INTO Venta (Empresa, Mov, MovID, FechaEmision, Concepto, Moneda, TipoCambio, Usuario, UsuarioID, Referencia, Observaciones, Estatus, RenglonID, Cliente, Almacen, Descuento, DescuentoGlobal, Importe, Impuestos, Sucursal) VALUES
('Empresa 1', 'Venta', 1, '2024-10-25 10:00:00', 'Venta de productos', 'MXN', 1.00, 'Juan Perez', 1, 'REF001', 'Observación de prueba', 'Completado', 1, 'Cliente A', 'Almacén Norte', 10.00, 5.00, 1500.00, 240.00, 'Sucursal Norte'),
('Empresa 2', 'Devolución', 2, '2024-10-25 12:00:00', 'Devolución de productos', 'USD', 20.00, 'Ana Gómez', 2, 'REF002', 'Observación de devolución', 'Pendiente', 2, 'Cliente B', 'Almacén Centro', 15.00, 0.00, 500.00, 80.00, 'Sucursal Centro');

-- Insertar datos en la tabla VentaDetalle
INSERT INTO VentaDetalle (VentaID, Renglon, RenglonSub, RenglonID, RenglonTipo, Cantidad, Almacen, Codigo, Producto, ProductoID, Precio, DescuentoTipo, DescuentoLinea, DescuentoImporte, Impuesto1, Impuesto2, Impuesto3, DescripcionExtra, Costo, Unidad, Factor, CantidadInventario, Sucursal) VALUES
(1, 1, 1, 1, 'Producto', 2, 'Almacén Norte', 'PROD001', 'Laptop', 1, 15000.00, 'Descuento Especial', 500.00, 1000.00, 240.00, 0.00, 0.00, 'Laptop de gama alta', 12000.00, 'Unidad', 1, 100, 'Sucursal Norte'),
(1, 2, 1, 2, 'Producto', 5, 'Almacén Norte', 'PROD002', 'Manzana', 2, 30.00, NULL, 0.00, 0.00, 0.00, 0.00, 0.00, 'Manzana importada', 20.00, 'Kg', 1, 200, 'Sucursal Norte');

-- Insertar datos en la tabla VentaCobro
INSERT INTO VentaCobro (VentaID, Cajero, CajeroID, Importe1, Importe2, FormaCobro1, FormaCobro2, Referencia1, Referencia2, Observaciones1, Observaciones2, Cambio, FormaCobroCambio, Redondeo, DelEfectivo, Sucursal) VALUES
(1, 'Juan Perez', 1, 1500.00, 0.00, 'Efectivo', NULL, 'REFCOB001', NULL, 'Cobro realizado en efectivo', NULL, 0.00, 'Efectivo', 0.00, 1500.00, 'Sucursal Norte'),
(2, 'Ana Gómez', 2, 500.00, 0.00, 'Tarjeta', NULL, 'REFCOB002', NULL, 'Cobro realizado con tarjeta', NULL, 0.00, 'Tarjeta', 0.00, 500.00, 'Sucursal Centro');
