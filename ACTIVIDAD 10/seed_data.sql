USE banco_db;

INSERT INTO personas (nombre, apellido, dni, correo, contrasena, rol) VALUES 
('Jorge', 'Chavez', '10203040', 'jorge@banco.com', 'emp123', 'EMPLEADO'),
('Rosa', 'Merino', '87654321', 'rosa@banco.com', 'emp123', 'EMPLEADO');

INSERT INTO personas (nombre, apellido, dni, correo, contrasena, rol) VALUES 
('Juan', 'Quispe', '66506070', 'juan@mail.com', 'cli123', 'CLIENTE'),
('Maria', 'Flores', '61526374', 'maria@mail.com', 'cli123', 'CLIENTE'),
('Pedro', 'Castillo', '69876543', 'pedro@mail.com', 'cli123', 'CLIENTE');

INSERT INTO cuentas (numero_cuenta, saldo, tipo_cuenta) VALUES 
('191-111111-01', 5000.00, 'AHORRO'),
('191-222222-02', 150.50, 'CORRIENTE'),
('193-333333-03', 12000.00, 'AHORRO'),
('194-444444-04', 500.00, 'AHORRO');

INSERT INTO cliente_cuenta (id_cliente, id_cuenta)
SELECT p.id, c.id FROM personas p, cuentas c WHERE p.dni = '66506070' AND c.numero_cuenta = '191-111111-01';

INSERT INTO cliente_cuenta (id_cliente, id_cuenta)
SELECT p.id, c.id FROM personas p, cuentas c WHERE p.dni = '66506070' AND c.numero_cuenta = '191-222222-02';

INSERT INTO cliente_cuenta (id_cliente, id_cuenta)
SELECT p.id, c.id FROM personas p, cuentas c WHERE p.dni = '61526374' AND c.numero_cuenta = '193-333333-03';

INSERT INTO cliente_cuenta (id_cliente, id_cuenta)
SELECT p.id, c.id FROM personas p, cuentas c WHERE p.dni = '69876543' AND c.numero_cuenta = '194-444444-04';

INSERT INTO transacciones (id_cuenta, tipo_transaccion, monto, fecha) 
SELECT id, 'DEPOSITO', 1000.00, DATE_SUB(NOW(), INTERVAL 10 DAY) FROM cuentas WHERE numero_cuenta = '191-111111-01';

INSERT INTO transacciones (id_cuenta, tipo_transaccion, monto, fecha) 
SELECT id, 'RETIRO', -200.00, DATE_SUB(NOW(), INTERVAL 5 DAY) FROM cuentas WHERE numero_cuenta = '191-111111-01';

INSERT INTO transacciones (id_cuenta, tipo_transaccion, monto, fecha) 
SELECT id, 'TRANSFERENCIA', -500.00, DATE_SUB(NOW(), INTERVAL 2 DAY) FROM cuentas WHERE numero_cuenta = '191-111111-01';

INSERT INTO transacciones (id_cuenta, tipo_transaccion, monto, fecha) 
SELECT id, 'DEPOSITO', 500.00, DATE_SUB(NOW(), INTERVAL 15 DAY) FROM cuentas WHERE numero_cuenta = '191-222222-02';

INSERT INTO transacciones (id_cuenta, tipo_transaccion, monto, fecha) 
SELECT id, 'DEPOSITO', 15000.00, DATE_SUB(NOW(), INTERVAL 30 DAY) FROM cuentas WHERE numero_cuenta = '193-333333-03';

INSERT INTO transacciones (id_cuenta, tipo_transaccion, monto, fecha) 
SELECT id, 'RETIRO', -3000.00, DATE_SUB(NOW(), INTERVAL 1 DAY) FROM cuentas WHERE numero_cuenta = '193-333333-03';
