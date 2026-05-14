# Documentación Técnica del Sistema de Gestión Veterinaria

---

# 1. Introducción

## 1.1 Objetivo del documento

El presente documento tiene como finalidad describir técnica y funcionalmente el sistema de gestión veterinaria desarrollado mediante tecnologías Java Spring Boot, Thymeleaf, Docker y despliegue en AWS EC2. La documentación incluye la descripción general del sistema, arquitectura, componentes, estructura de base de datos, manual técnico, manual de usuario y procedimientos de despliegue.

## 1.2 Alcance

La aplicación permite administrar procesos fundamentales de una clínica veterinaria:

- Gestión de clientes.
- Registro de mascotas.
- Gestión de veterinarios.
- Agendamiento de citas.
- Registro de historial clínico.
- Generación de facturas.
- Administración de servicios.
- Gestión administrativa.

## 1.3 Tecnologías utilizadas

| Tecnología | Descripción |
|---|---|
| Java 17 | Lenguaje principal del backend |
| Spring Boot | Framework backend MVC |
| Thymeleaf | Motor de plantillas HTML |
| Maven | Gestión de dependencias |
| Docker | Contenerización |
| AWS EC2 | Despliegue en nube |
| Hibernate/JPA | Persistencia ORM |
| HTML/CSS | Interfaz visual |

---

# 2. Arquitectura del Sistema

## 2.1 Arquitectura general

La aplicación utiliza una arquitectura MVC (Modelo Vista Controlador).

### Componentes:

1. Modelo
   - Representa las entidades del sistema.
   - Incluye clases como Usuario, Mascota, Factura, HistorialClinico y Cita.

2. Vista
   - Implementada mediante Thymeleaf.
   - Permite interacción visual con usuarios.

3. Controlador
   - Gestiona solicitudes HTTP.
   - Coordina lógica entre vistas y servicios.

4. Servicio
   - Implementa la lógica de negocio.

5. Repositorio
   - Acceso a datos mediante Spring Data JPA.

---

# 3. Estructura del Proyecto

## 3.1 Organización de carpetas

```text
src/main/java/com/veterinaria/veterinaria
│
├── controller
├── model
├── repository
├── service
└── VeterinariaApplication.java

src/main/resources
│
├── templates
├── static
└── application.properties
```

## 3.2 Controladores principales

| Controlador | Función |
|---|---|
| AdminController | Gestión administrativa |
| ClienteController | Funciones del cliente |
| VeterinarioController | Funciones del veterinario |
| FacturaController | Gestión de facturación |
| HistorialController | Gestión historial clínico |

---

# 4. Modelado de Entidades

## 4.1 Entidad Usuario

Representa clientes, administradores y veterinarios.

### Campos:

| Campo | Tipo |
|---|---|
| id | Long |
| nombres | String |
| apellidos | String |
| correo | String |
| password | String |
| rol | String |
| numeroDocumento | String |

---

## 4.2 Entidad Mascota

Representa las mascotas registradas.

### Campos:

| Campo | Tipo |
|---|---|
| id | Long |
| nombre | String |
| especie | String |
| raza | String |
| edad | Integer |
| cedulaDueno | String |

---

## 4.3 Entidad Factura

Gestiona la facturación.

### Campos:

| Campo | Tipo |
|---|---|
| id | Long |
| cliente | String |
| mascota | String |
| documentoCliente | String |
| items | List<ItemFactura> |

---

## 4.4 Entidad Historial Clínico

Permite registrar información médica.

### Campos:

| Campo | Tipo |
|---|---|
| id | Long |
| fecha | LocalDate |
| descripcion | String |
| tipo | String |

---

# 5. Flujo Funcional del Sistema

## 5.1 Flujo Administrador

El administrador puede:

- Registrar veterinarios.
- Registrar clientes.
- Generar facturas.
- Configurar tarifas.
- Consultar registros.

## 5.2 Flujo Veterinario

El veterinario puede:

- Registrar mascotas.
- Gestionar historial clínico.
- Consultar mascotas.
- Registrar vacunas.
- Registrar tratamientos.

## 5.3 Flujo Cliente

El cliente puede:

- Consultar mascotas.
- Agendar citas.
- Consultar historial clínico.
- Consultar facturas.

---

# 6. Script SQL de Base de Datos

## 6.1 Script creación tabla usuarios

```sql
CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    correo VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    rol VARCHAR(50),
    numero_documento VARCHAR(50) UNIQUE
);
```

## 6.2 Script creación tabla mascotas

```sql
CREATE TABLE mascotas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    especie VARCHAR(50),
    raza VARCHAR(100),
    edad INT,
    cedula_dueno VARCHAR(50)
);
```

## 6.3 Script creación tabla facturas

```sql
CREATE TABLE facturas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente VARCHAR(100),
    mascota VARCHAR(100),
    documento_cliente VARCHAR(50)
);
```

## 6.4 Script creación historial clínico

```sql
CREATE TABLE historial_clinico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE,
    descripcion TEXT,
    tipo VARCHAR(50)
);
```

---

# 7. Configuración Docker

## 7.1 Dockerfile

```dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

## 7.2 Archivo .dockerignore

```text
.git
.idea
*.iml
node_modules
```

## 7.3 Construcción de imagen

```bash
./mvnw clean package -DskipTests

docker build -t veterinaria-app .
```

## 7.4 Ejecución del contenedor

```bash
docker run -d -p 80:8080 veterinaria-app
```

---

# 8. Despliegue en AWS EC2

## 8.1 Creación instancia

La aplicación fue desplegada en una instancia Ubuntu EC2.

### Configuración utilizada:

| Parámetro | Valor |
|---|---|
| Sistema operativo | Ubuntu Server |
| Tipo instancia | t2.micro |
| Puerto aplicación | 80 |
| Puerto SSH | 22 |

## 8.2 Conexión SSH

```bash
ssh -i di3.pem ubuntu@IP_PUBLICA
```

## 8.3 Configuración Security Group

| Puerto | Función |
|---|---|
| 22 | SSH |
| 80 | HTTP |
| 27017 | MongoDB |

---

# 9. Manual Técnico del Sistema

## 9.1 Instalación local

### Requisitos:

- Java 17
- Maven
- Docker
- Git

### Clonar proyecto

```bash
git clone URL_PROYECTO
```

### Ejecutar localmente

```bash
./mvnw spring-boot:run
```

---

## 9.2 Variables importantes

Archivo:

```text
application.properties
```

Configuraciones principales:

```properties
server.port=8080
spring.jpa.hibernate.ddl-auto=update
```

---

## 9.3 Estructura MVC

El patrón MVC permite:

- Separación de responsabilidades.
- Mejor mantenimiento.
- Escalabilidad.
- Modularización.

---

# 10. Manual de Usuario

## 10.1 Inicio de sesión

El usuario accede mediante correo y contraseña.

## 10.2 Funciones Administrador

### Registrar veterinario

1. Ingresar al panel administrador.
2. Seleccionar registrar veterinario.
3. Completar formulario.
4. Guardar información.

### Generar factura

1. Ingresar módulo facturación.
2. Registrar datos del cliente.
3. Registrar servicio.
4. Generar factura.

---

## 10.3 Funciones Veterinario

### Registrar mascota

1. Ingresar panel veterinario.
2. Seleccionar registrar mascota.
3. Asociar dueño.
4. Guardar.

### Registrar historial clínico

1. Abrir historial.
2. Registrar tratamiento.
3. Guardar cambios.

---

## 10.4 Funciones Cliente

### Agendar cita

1. Ingresar módulo citas.
2. Seleccionar fecha.
3. Confirmar registro.

### Consultar historial

1. Abrir historial clínico.
2. Consultar registros médicos.

---

# 11. Manejo de Errores

## 11.1 Error 400

Causado por parámetros faltantes.

### Solución:

Validar formularios HTML y RequestParam.

## 11.2 Error 500

Causado por errores internos del servidor.

### Solución:

Verificar logs Docker.

## 11.3 Error Thymeleaf

Causado por plantillas inexistentes.

### Solución:

Crear archivo HTML correspondiente.

---

# 12. Seguridad

## 12.1 Validaciones implementadas

- Validación formularios.
- Restricción de roles.
- Uso de contenedores.
- Control acceso servidor.

## 12.2 Recomendaciones futuras

- Implementar Spring Security.
- Implementar JWT.
- Implementar HTTPS.
- Implementar cifrado contraseñas.

---

# 13. Mejoras Futuras

## Funcionalidades futuras

- Sistema de pagos.
- Notificaciones email.
- Dashboard administrativo.
- Reportes PDF.
- Gestión inventario.
- Agenda inteligente.
- Integración WhatsApp.

---

# 14. Conclusiones

El sistema veterinario desarrollado permite administrar de manera centralizada los procesos fundamentales de una clínica veterinaria mediante tecnologías modernas de desarrollo backend y despliegue en la nube.

La arquitectura implementada facilita escalabilidad, mantenimiento y futuras mejoras funcionales.

El despliegue mediante Docker y AWS EC2 proporciona portabilidad y disponibilidad del sistema.

---

# 15. Referencias

- Documentación Spring Boot.
- Documentación Docker.
- AWS EC2 Documentation.
- Thymeleaf Documentation.
- Hibernate ORM Documentation.
- Java SE Documentation.

