CREATE DATABASE IF NOT EXISTS banco_db;
USE banco_db;

CREATE TABLE IF NOT EXISTS personas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    correo VARCHAR(100),
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL -- 'ADMIN', 'EMPLEADO', 'CLIENTE'
);

CREATE TABLE IF NOT EXISTS cuentas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    saldo DECIMAL(15, 2) DEFAULT 0.00,
    tipo_cuenta VARCHAR(50) -- 'AHORRO', 'CORRIENTE'
);

CREATE TABLE IF NOT EXISTS cliente_cuenta (
    id_cliente INT,
    id_cuenta INT,
    PRIMARY KEY (id_cliente, id_cuenta),
    FOREIGN KEY (id_cliente) REFERENCES personas(id),
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id)
);

CREATE TABLE IF NOT EXISTS transacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT,
    tipo_transaccion VARCHAR(20) NOT NULL, -- 'DEPOSITO', 'RETIRO'
    monto DECIMAL(15, 2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id)
);

-- Insertar un admin por defecto
INSERT INTO personas (nombre, apellido, dni, correo, contrasena, rol) 
VALUES ('Super', 'Admin', '00000000', 'admin@banco.com', 'admin123', 'ADMIN') 
ON DUPLICATE KEY UPDATE id=id;
