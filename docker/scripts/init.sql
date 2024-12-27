CREATE DATABASE IF NOT EXISTS banco_db;
USE banco_db;

-- Tablas con secuencias
CREATE TABLE IF NOT EXISTS cliente (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50),
    edad INT,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    estado BOOLEAN DEFAULT true
    );

CREATE TABLE IF NOT EXISTS cuenta (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      numero_cuenta VARCHAR(20) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(20) NOT NULL,
    saldo_inicial DECIMAL(10,2) NOT NULL,
    estado BOOLEAN DEFAULT true,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
    );

CREATE TABLE IF NOT EXISTS movimiento (
                                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                          fecha DATETIME NOT NULL,
                                          tipo_movimiento VARCHAR(20) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    saldo DECIMAL(10,2) NOT NULL,
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
    );

-- Datos de prueba
INSERT INTO cliente (nombre, direccion, telefono, password, identificacion, genero, edad) VALUES
                                                                                              ('Jose Lema', 'Otavalo sn y principal', '098254785', '1234', '1234567890', 'M', 25),
                                                                                              ('Marianela Montalvo', 'Amazonas y NNUU', '097548965', '5678', '1234567891', 'F', 30),
                                                                                              ('Juan Osorio', '13 junio y Equinoccial', '098874587', '1245', '1234567892', 'M', 28);

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, cliente_id) VALUES
                                                                               ('478758', 'Ahorro', 2000.00, 1),
                                                                               ('225487', 'Corriente', 100.00, 2),
                                                                               ('495878', 'Ahorro', 0.00, 3),
                                                                               ('496825', 'Ahorro', 540.00, 2),
                                                                               ('585545', 'Corriente', 1000.00, 1);

INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id) VALUES
                                                                             (NOW(), 'DEBITO', -575.00, 1425.00, 1),
                                                                             (NOW(), 'CREDITO', 600.00, 700.00, 2),
                                                                             (NOW(), 'CREDITO', 150.00, 150.00, 3),
                                                                             (NOW(), 'DEBITO', -540.00, 0.00, 4);