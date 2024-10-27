-- Crear tabla Usuario
CREATE TABLE Usuario (
    UsuarioID INT PRIMARY KEY AUTO_INCREMENT,     -- ID del usuario
    ClaveEmpleado VARCHAR(50) NOT NULL UNIQUE,    -- Clave del empleado (única)
    Nombre VARCHAR(100) NOT NULL,                 -- Nombre completo del usuario
    Sucursal VARCHAR(100) NOT NULL,               -- Sucursal donde trabaja el usuario
    Turno VARCHAR(50) NOT NULL,                   -- Turno del usuario (por ejemplo: matutino, vespertino)
    Rol VARCHAR(50) NOT NULL,                     -- Rol del usuario (por ejemplo: administrador, cajero)
    Contrasena VARCHAR(255) NOT NULL              -- Contraseña del usuario (se recomienda encriptarla)
);

-- Crear tabla Categoria
CREATE TABLE Categoria (
    CategoriaID INT PRIMARY KEY AUTO_INCREMENT,   -- ID de la categoría
    Nombre VARCHAR(100) NOT NULL                  -- Nombre de la categoría
);

-- Crear tabla Familia
CREATE TABLE Familia (
    FamiliaID INT PRIMARY KEY AUTO_INCREMENT,     -- ID de la familia
    Nombre VARCHAR(100) NOT NULL                  -- Nombre de la familia
);

-- Crear tabla Proveedor
CREATE TABLE Proveedor (
    ProveedorID INT PRIMARY KEY AUTO_INCREMENT,   -- ID del proveedor
    Nombre VARCHAR(100) NOT NULL                  -- Nombre del proveedor
);

-- Crear tabla Producto
CREATE TABLE Producto (
    ProductoID INT PRIMARY KEY AUTO_INCREMENT,    -- ID del producto
    Nombre VARCHAR(100) NOT NULL,                 -- Nombre del producto
    Descripcion VARCHAR(255),                     -- Descripción del producto
    Precio DECIMAL(10, 2) NOT NULL,               -- Precio del producto
    CategoriaID INT NOT NULL,                     -- ID de la categoría (FK)
    FamiliaID INT NOT NULL,                       -- ID de la familia (FK)
    ProveedorID INT NOT NULL,                     -- ID del proveedor (FK)
    FOREIGN KEY (CategoriaID) REFERENCES Categoria(CategoriaID),
    FOREIGN KEY (FamiliaID) REFERENCES Familia(FamiliaID),
    FOREIGN KEY (ProveedorID) REFERENCES Proveedor(ProveedorID)
);

-- Crear tabla Mov
CREATE TABLE Mov (
    MovID INT PRIMARY KEY AUTO_INCREMENT,         -- ID del movimiento
    Tipo VARCHAR(50) NOT NULL                     -- Tipo de movimiento (venta, devolución, etc.)
);

-- Crear tabla Venta
CREATE TABLE Venta (
    VentaID INT PRIMARY KEY AUTO_INCREMENT,       -- ID de la venta
    Empresa VARCHAR(100) NOT NULL,                -- Nombre de la empresa
    Mov VARCHAR(50) NOT NULL,                     -- Movimiento
    MovID INT NOT NULL,                           -- ID del movimiento
    FechaEmision DATETIME NOT NULL,               -- Fecha de emisión
    Concepto VARCHAR(255) NOT NULL,               -- Concepto de la venta
    Moneda VARCHAR(10) NOT NULL,                  -- Moneda utilizada
    TipoCambio DECIMAL(10, 4),                    -- Tipo de cambio aplicado
    Usuario VARCHAR(100) NOT NULL,                -- Nombre del usuario que realiza la venta
    UsuarioID INT NOT NULL,                       -- ID del usuario que realiza la venta (FK)
    Referencia VARCHAR(100),                      -- Referencia de la venta
    Observaciones VARCHAR(255),                   -- Observaciones adicionales
    Estatus VARCHAR(50) NOT NULL,                 -- Estado de la venta
    RenglonID INT NOT NULL,                       -- ID del renglón
    Cliente VARCHAR(100) NOT NULL,                -- Cliente al que se le factura
    Almacen VARCHAR(100) NOT NULL,                -- Almacén donde se realiza la venta
    Descuento DECIMAL(10, 2),                     -- Descuento aplicado
    DescuentoGlobal DECIMAL(10, 2),               -- Descuento global aplicado
    Importe DECIMAL(10, 2) NOT NULL,              -- Importe total de la venta
    Impuestos DECIMAL(10, 2) NOT NULL,            -- Importe de impuestos
    Sucursal VARCHAR(100) NOT NULL,               -- Sucursal donde se realiza la venta
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(UsuarioID),
    FOREIGN KEY (MovID) REFERENCES Mov(MovID)
);

-- Crear tabla VentaDetalle
CREATE TABLE VentaDetalle (
    VentaDetalleID INT PRIMARY KEY AUTO_INCREMENT, -- ID del detalle de la venta
    VentaID INT NOT NULL,                          -- ID de la venta (FK)
    Renglon INT NOT NULL,                          -- Número de renglón
    RenglonSub INT NOT NULL,                       -- Sub renglón
    RenglonID INT NOT NULL,                        -- ID del renglón
    RenglonTipo VARCHAR(50) NOT NULL,              -- Tipo del renglón (por ejemplo: producto, servicio)
    Cantidad INT NOT NULL,                         -- Cantidad de productos vendidos
    Almacen VARCHAR(100) NOT NULL,                 -- Almacén donde se realiza la venta
    Codigo VARCHAR(50) NOT NULL,                   -- Código del producto
    Producto VARCHAR(100) NOT NULL,                -- Nombre del producto
    ProductoID INT NOT NULL,                       -- ID del producto (FK)
    Precio DECIMAL(10, 2) NOT NULL,                -- Precio del producto
    DescuentoTipo VARCHAR(50),                     -- Tipo de descuento
    DescuentoLinea DECIMAL(10, 2),                 -- Descuento aplicado en la línea
    DescuentoImporte DECIMAL(10, 2),               -- Importe total de descuento
    Impuesto1 DECIMAL(10, 2),                      -- Importe del primer impuesto
    Impuesto2 DECIMAL(10, 2),                      -- Importe del segundo impuesto
    Impuesto3 DECIMAL(10, 2),                      -- Importe del tercer impuesto
    DescripcionExtra VARCHAR(255),                -- Descripción adicional del producto
    Costo DECIMAL(10, 2),                          -- Costo del producto
    Unidad VARCHAR(50),                            -- Unidad de medida
    Factor INT NOT NULL,                           -- Factor para el cálculo de cantidad
    CantidadInventario INT NOT NULL,               -- Cantidad en inventario
    Sucursal VARCHAR(100) NOT NULL,                -- Sucursal donde se realiza la venta
    FOREIGN KEY (VentaID) REFERENCES Venta(VentaID),
    FOREIGN KEY (ProductoID) REFERENCES Producto(ProductoID)
);

-- Crear tabla VentaCobro
CREATE TABLE VentaCobro (
    VentaCobroID INT PRIMARY KEY AUTO_INCREMENT,  -- ID del cobro
    VentaID INT NOT NULL,                         -- ID de la venta (FK)
    Cajero VARCHAR(100) NOT NULL,                 -- Nombre del cajero que procesa el cobro
    CajeroID INT NOT NULL,                        -- ID del cajero (FK a Usuario)
    Importe1 DECIMAL(10, 2) NOT NULL,             -- Importe del primer método de pago
    Importe2 DECIMAL(10, 2),                      -- Importe del segundo método de pago
    FormaCobro1 VARCHAR(50) NOT NULL,             -- Forma de cobro del primer método
    FormaCobro2 VARCHAR(50),                      -- Forma de cobro del segundo método
    Referencia1 VARCHAR(100),                     -- Referencia del primer método de pago
    Referencia2 VARCHAR(100),                     -- Referencia del segundo método de pago
    Observaciones1 VARCHAR(255),                  -- Observaciones del primer método de pago
    Observaciones2 VARCHAR(255),                  -- Observaciones del segundo método de pago
    Cambio DECIMAL(10, 2) NOT NULL,               -- Cambio entregado al cliente
    FormaCobroCambio VARCHAR(50) NOT NULL,        -- Forma de cobro del cambio
    Redondeo DECIMAL(10, 2),                      -- Importe redondeado
    DelEfectivo DECIMAL(10, 2),                   -- Importe del efectivo recibido
    Sucursal VARCHAR(100) NOT NULL,               -- Sucursal donde se realiza el cobro
    FOREIGN KEY (VentaID) REFERENCES Venta(VentaID),
    FOREIGN KEY (CajeroID) REFERENCES Usuario(UsuarioID)
);

ALTER TABLE Producto 
ADD COLUMN Existencia INT DEFAULT 0 NOT NULL; 

CREATE TABLE Compra (
    ProductoID INT NOT NULL,                   -- ID del producto (FK)
    ProveedorID INT NOT NULL,                  -- ID del proveedor (FK)
    Fecha DATE NOT NULL,                       -- Fecha de la compra
    FechaCaducidad DATE,                       -- Fecha de caducidad del producto
    Cantidad INT NOT NULL,                     -- Cantidad comprada
    FOREIGN KEY (ProductoID) REFERENCES Producto(ProductoID),
    FOREIGN KEY (ProveedorID) REFERENCES Proveedor(ProveedorID)
    PRIMARY KEY (ProductoID, ProveedorID, Fecha),
);