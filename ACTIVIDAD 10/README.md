# Sistema Bancario Java Swing + JDBC

Este proyecto es una aplicación de escritorio para la gestión bancaria, implementada en Java utilizando Swing para la interfaz gráfica y JDBC con MySQL para la persistencia de datos.

## Requisitos Previos

1.  **JDK 8** o superior.
2.  **MySQL Server**.
3.  **MySQL JDBC Driver** (mysql-connector-java).

## Configuración de la Base de Datos

1.  Asegúrate de que el servicio MySQL esté ejecutándose.
2.  Ejecuta el script `schema.sql` en tu cliente MySQL favorito (Workbench, DBeaver, CLI) para crear la base de datos y las tablas.
    ```sql
    source schema.sql
    ```
3.  Verifica la configuración de conexión en `src/com/banco/dao/DatabaseConnection.java`:
    ```java
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Coloca tu contraseña aquí
    ```

## Compilación y Ejecución

Necesitas tener el driver de MySQL en tu classpath. Asumiendo que tienes el `.jar` (ej. `mysql-connector-j-8.x.x.jar`) en una carpeta `lib`:

**Compilar:**
```bash
javac -cp ".;lib/mysql-connector.jar" -d bin src/com/banco/model/*.java src/com/banco/dao/*.java src/com/banco/ui/*.java src/com/banco/main/*.java
```

**Ejecutar:**
```bash
java -cp ".;bin;lib/mysql-connector.jar" com.banco.main.Main
```

## Usuarios de Prueba

El script crea un administrador por defecto:
- **DNI**: `00000000`
- **Password**: `admin123`

## Funcionalidades Implementadas

- **Administrador**: Registro de Empleados y Clientes.
- **Empleado**: Registro de Clientes y Apertura de Cuentas.
- **Cliente**: Listado de cuentas, Depósitos, Retiros e Historial de Transacciones.
