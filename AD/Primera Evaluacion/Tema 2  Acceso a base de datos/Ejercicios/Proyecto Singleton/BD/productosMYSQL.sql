DROP DATABASE IF exists productosMYSQL;
CREATE DATABASE productosMYSQL;
USE productosMYSQL;

-- Crear la tabla de usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    ano_nacimiento INT
);

-- Crear la tabla de productos
CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

-- Crear la tabla de pedidos
CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    fecha_pedido DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON UPDATE CASCADE 
);

-- Crear la tabla de relación pedidos_productos
CREATE TABLE pedidos_productos (
    id_pedido INT,
    id_producto INT,
    cantidad INT NOT NULL,
    PRIMARY KEY (id_pedido, id_producto),
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido) ON UPDATE CASCADE ,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON UPDATE CASCADE 
);

-- Insertar datos en la tabla productos
INSERT INTO usuarios (nombre, email, ano_nacimiento) VALUES
('Juan Pérez', 'juan@gmail.com', 1985),
('María García', 'maria.garcia@outlook.es', 1990),
('Carlos López', 'carlos.lopez@gmail.com', 1978),
('Ana Torres', 'ana.torres@outlook.com', 1995);

-- Insertar datos en la tabla pedidos
INSERT INTO productos (nombre_producto, precio, stock) VALUES
('Laptop', 1200.50, 10),
('Smartphone', 650.00, 25),
('Tablet', 300.75, 15),
('Monitor', 150.00, 20);

-- Insertar datos en la tabla pedidos
INSERT INTO pedidos (id_usuario, fecha_pedido) VALUES
(1, '2024-09-10'),
(2, '2024-10-11'),
(3, '2024-09-12'),
(4, '2024-11-12');


-- Insertar datos en la tabla pedidos_productos
INSERT INTO pedidos_productos (id_pedido, id_producto, cantidad) VALUES
(1, 1, 2),  -- Juan Pérez compró 2 Laptops
(2, 2, 1),  -- María García compró 1 Smartphone
(3, 3, 3),  -- Carlos López compró 3 Tablets
(4, 4, 1);  -- Ana Torres compró 1 Monitor

