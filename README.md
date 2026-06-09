# COOVALLUNA Ltda. — Sistema de Información

Proyecto Final — Bases de Datos Relacionales  
Universidad del Valle, Sede Tuluá  
Período Académico Febrero – Junio 2026

---

## Descripción

Sistema de información para la Cooperativa de Ahorro y Crédito **COOVALLUNA Ltda.**, desarrollado como proyecto final del curso de Bases de Datos Relacionales. Permite gestionar asociados, productos financieros y reportes operativos a través de tres perfiles de usuario: Administrador, Asesor/Cajero y Asociado.

---

## Tecnologías utilizadas

| Componente | Tecnología |
|---|---|
| Lenguaje | Java (JDK 25) |
| Interfaz gráfica | Java Swing |
| Base de datos | PostgreSQL 18 |
| Driver de conexión | postgresql-42.7.11.jar |
| Selector de fechas | jcalendar-1.4.jar |
| Generación de PDF | itextpdf-5.5.13.3.jar |
| IDE | Apache NetBeans |

---

## Requisitos previos

Antes de ejecutar el sistema asegúrese de tener instalado:

- **JDK 25** o superior
- **PostgreSQL 18** o superior
- **Apache NetBeans** (recomendado) o cualquier IDE compatible con Java

---

## Configuración de la base de datos

### 1. Crear la base de datos

Abra pgAdmin o la terminal de PostgreSQL y ejecute:

```sql
CREATE DATABASE coovalluna;
```

### 2. Ejecutar el script DDL

Abra el archivo `Script_DDL.sql` y ejecútelo sobre la base de datos `coovalluna`. Esto creará las 14 tablas del sistema:

- agencia
- linea_credito
- tipo_empleado
- asociado
- fundador
- beneficiario
- empleado
- cuenta_ahorros
- movimiento
- credito
- pago_cuota
- asesora
- garantiza
- usuario

### 3. Ejecutar el script DML

Abra el archivo `Script_DML.sql` y ejecútelo sobre la base de datos `coovalluna`. Esto insertará los datos de prueba requeridos.

### 4. Ejecutar el trigger de beneficiarios

```sql
CREATE OR REPLACE FUNCTION validar_max_beneficiarios()
RETURNS TRIGGER AS $$
BEGIN
    IF (
        SELECT COUNT(*) FROM beneficiario
        WHERE cedula_asociado = NEW.cedula_asociado
    ) >= 4 THEN
        RAISE EXCEPTION 'El asociado ya tiene 4 beneficiarios registrados';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_max_beneficiarios
BEFORE INSERT ON beneficiario
FOR EACH ROW
EXECUTE FUNCTION validar_max_beneficiarios();
```

---

## Configuración del proyecto en NetBeans

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
```

### 2. Abrir el proyecto

- Abrir NetBeans
- Ir a **File → Open Project**
- Seleccionar la carpeta del proyecto `COVALLUNA`

### 3. Agregar las librerías

Ir a **Project Properties → Libraries → Classpath → Add JAR/Folder** y agregar:

- `postgresql-42.7.11.jar`
- `jcalendar-1.4.jar`
- `itextpdf-5.5.13.3.jar`

### 4. Configurar la conexión

Abrir el archivo `src/conexion/Conexion.java` y verificar los datos de conexión:

```java
private static final String URL =
        "jdbc:postgresql://localhost:5432/coovalluna";
private static final String USER = "postgres";
private static final String PASSWORD = "univalle";
```

Cambiar `USER` y `PASSWORD` según la configuración local de PostgreSQL.

### 5. Ejecutar el proyecto

- Hacer clic derecho sobre el proyecto
- Seleccionar **Run** o presionar `F6`
- El sistema iniciará en la pantalla de login

---

## Credenciales de acceso

### Administrador
| Campo | Valor |
|---|---|
| Usuario | admin |
| Contraseña | 123 |

### Asesor/Cajero
Los usuarios asesores son creados desde el módulo de Gestión de Usuarios del Administrador. La cédula del asesor debe existir previamente en la tabla `empleado`.

### Asociado
Los usuarios asociados son creados desde el módulo de Gestión de Usuarios del Administrador. La cédula del asociado debe existir previamente en la tabla `asociado`.

---

## Estructura del proyecto

```
COVALLUNA/
├── src/
│   ├── conexion/
│   │   └── Conexion.java
│   ├── dao/
│   │   ├── AgenciaDAO.java
│   │   ├── AsociadoDAO.java
│   │   ├── BeneficiarioDAO.java
│   │   ├── CreditoDAO.java
│   │   ├── CuentaAhorrosDAO.java
│   │   ├── EmpleadoDAO.java
│   │   ├── MovimientoDAO.java
│   │   ├── PagoCuotaDAO.java
│   │   ├── SolicitudActualizacionDAO.java
│   │   ├── TipoEmpleadoDAO.java
│   │   └── UsuarioDAO.java
│   ├── modelo/
│   │   ├── Agencia.java
│   │   ├── Asociado.java
│   │   ├── Beneficiario.java
│   │   ├── Credito.java
│   │   ├── CuentaAhorros.java
│   │   ├── Empleado.java
│   │   ├── Garantia.java
│   │   ├── Movimiento.java
│   │   ├── PagoCuota.java
│   │   ├── Sesion.java
│   │   ├── SolicitudActualizacion.java
│   │   ├── TipoEmpleado.java
│   │   └── Usuario.java
│   ├── vista/
│   │   ├── FrmAdministrador.java
│   │   ├── FrmAsesor.java
│   │   ├── FrmAsociado.java
│   │   ├── FrmCrearAgencia.java
│   │   ├── FrmCrearAsociado.java
│   │   ├── FrmCrearBeneficiario.java
│   │   ├── FrmCrearCredito.java
│   │   ├── FrmCrearCuenta.java
│   │   ├── FrmCrearEmpleado.java
│   │   ├── FrmCrearMovimiento.java
│   │   ├── FrmCrearPagoCuota.java
│   │   ├── FrmEditarAgencia.java
│   │   ├── FrmEditarAsociado.java
│   │   ├── FrmEditarBeneficiario.java
│   │   ├── FrmEditarCuenta.java
│   │   ├── FrmEditarEmpleado.java
│   │   ├── FrmExtractoCuenta.java
│   │   ├── FrmGestionAgencia.java
│   │   ├── FrmGestionAsociado.java
│   │   ├── FrmGestionBeneficiario.java
│   │   ├── FrmGestionCredito.java
│   │   ├── FrmGestionCuenta.java
│   │   ├── FrmGestionEmpleado.java
│   │   ├── FrmGestionMovimiento.java
│   │   ├── FrmGestionUsuarios.java
│   │   ├── FrmInfoPersonal.java
│   │   ├── FrmLogin.java
│   │   ├── FrmMisCreditos.java
│   │   ├── FrmMisCuentas.java
│   │   ├── FrmReportes.java
│   │   ├── FrmReportesAsesor.java
│   │   ├── FrmSolicitudesPendientes.java
│   │   ├── FrmSolicitarActualizacion.java
│   │   └── FrmLogin.java
│   └── principal/
│       └── Main.java
├── Script_DDL.sql
├── Script_DML.sql
└── README.md
```

---

## Perfiles de usuario y funcionalidades

### Perfil 1 — Administrador
- Gestión completa de agencias, empleados y asociados
- Gestión de usuarios del sistema (crear, cambiar contraseña, eliminar)
- Acceso a los 7 reportes del sistema

### Perfil 2 — Asesor/Cajero
- Registro y consulta de asociados y beneficiarios
- Gestión de cuentas de ahorro y movimientos
- Radicación de créditos y registro de pagos
- Aprobación o rechazo de solicitudes de actualización de datos
- Acceso a reportes operativos (reportes 1, 2, 4 y 5)

### Perfil 3 — Asociado
- Consulta de información personal y beneficiarios
- Consulta de saldo y extracto de cuentas de ahorro
- Consulta del estado de créditos
- Descarga de extracto en PDF o CSV
- Solicitud de actualización de datos de contacto

---

## Reportes disponibles

| # | Reporte | Perfil |
|---|---|---|
| 1 | Listado de asociados por estado y municipio | Admin, Asesor |
| 2 | Extracto de cuenta con filtros | Admin, Asesor, Asociado |
| 3 | Estado de cartera por línea y estado | Admin |
| 4 | Asociados en mora | Admin, Asesor |
| 5 | Historial de pagos de un crédito | Admin, Asesor |
| 6 | Productividad de asesores por agencia | Admin |
| 7 | Créditos con codeudoría activa | Admin |

---

## Restricciones técnicas implementadas

- Todos los scripts DDL y DML fueron escritos manualmente por el equipo
- Todas las consultas usan `PreparedStatement` para prevenir inyección SQL
- No se utilizó ningún ORM ni herramienta de generación automática de SQL
- Un asociado puede tener máximo 4 beneficiarios (validado en aplicación y BD mediante trigger)
- El saldo de las cuentas se calcula dinámicamente a partir de los movimientos

---

## Autores

| Nombre | Código |
|---|---|
| [Alejandro Ariza Jimenez] | [2451225-2724] |
| [Orlando Stiven Herrera Martinez] | [2569371 - 3743] |
| [Samuel García Parra] | [202459476 - 3743] |

Grupo: [BASES DE DATOS-52 | Grupo E]  
Docente: [Mauricio Lopez Benitez]
