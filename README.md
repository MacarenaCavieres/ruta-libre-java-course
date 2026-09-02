# Ruta Libre - REST API

**Ruta Libre** es una API REST desarrollada en Spring Boot para gestionar el proceso de reserva y arriendo de vehículos.
El sistema gestiona clientes, vehículos de la flota y la creación/actualización de reservas, manteniendo las reglas de
negocio del dominio.

## Pila Tecnológica

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.x
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL 16 (vía Docker)
* **Documentación & Contratos:** SpringDoc OpenAPI 3 / Swagger-UI
* **Gestor de Dependencias:** Maven
* **Otras Herramientas:** Lombok, Jakarta Validation

---

## 🛠️ Arquitectura y Estructura del Proyecto

El proyecto sigue los principios de **Clean Architecture** y **Domain-Driven Design (DDD)**, organizando el código por
capas claras de dominio y exposición web.

```text
src/
└── main/
    └── java/
        └── com/mccr/rutalibre/
            ├── application/
            │   ├── config/
            │   │   ├── CorsConfig.java
            │   │   └── OpenApiConfig.java
            │   └── service/
            │       ├── BookingService.java
            │       ├── ClientService.java
            │       └── VehicleService.java
            └── domain/
                ├── controller/
                │   ├── BookingController.java
                │   ├── ClientController.java
                │   └── VehicleController.java
                ├── dto/
                │   └── booking/
                │       ├── CreateBookingRequest.java
                │       └── UpdateBookingRequest.java
                ├── model/
                │   ├── enums/
                │   │   └── VehicleStatus.java
                │   ├── Booking.java
                │   ├── Client.java
                │   ├── DriverLicense.java
                │   └── Vehicle.java
                └── repository/
                    ├── BookingRepository.java
                    ├── ClientRepository.java
                    └── VehicleRepository.java      
```

### Componentes Principales

* **Entidades de Dominio (`domain.model`):**
    * `Booking`: Representa la reserva vinculando un `Client` y un `Vehicle` con sus fechas de arriendo.
    * `Client`: Información personal del cliente con su licencia embebida (`DriverLicense`).
    * `Vehicle`: Flota de vehículos (patente, marca, modelo, año) y su estado (`AVAILABLE`, etc.).
* **DTOs / Records (`domain.dto`):** Objetos de entrada para la creación y actualización con validaciones (
  `CreateBookingRequest`, `UpdateBookingRequest`).
* **Controladores REST (`web.controller`):** Exposición de los endpoints HTTP (`BookingController`, `ClientController`,
  `VehicleController`) documentados con anotaciones de OpenAPI.

---

## 1. Instrucciones para Levantar la Base de Datos

El entorno de desarrollo utiliza **PostgreSQL 16** containerizado a través de Docker Compose con persistencia de datos
mediante volúmenes nombrados.

Para iniciar el contenedor de la base de datos en segundo plano, ejecuta:

```bash
docker compose up -d
```

> **Nota:** Esto creará el contenedor `pg-rutalibre` escuchando en el puerto `5432` con la base de datos `rutalibre_db`.

---

## 2. Instrucciones para Ejecutar la Aplicación en Modo Desarrollo

Una vez levantado el contenedor de PostgreSQL, puedes iniciar el servidor Spring Boot en el perfil de desarrollo (
`dev`):

```bash
./mvnw spring-boot:run
```

```
.\mvnw.cmd spring-boot:run
```

## 3. Ruta de Documentación y Pruebas de Contratos

Con la aplicación en ejecución en el entorno local (`dev`), puedes acceder a la interfaz gráfica interactiva de Swagger
UI y a la especificación OpenAPI a través de las siguientes URLs:

* **Swagger-UI (Interfaz interactiva):**  
  http://localhost:8080/swagger-ui.html

* **OpenAPI JSON (Especificación OpenAPI 3):**  
  http://localhost:8080/api-docs

## 4. Integración con el Frontend

El Frontend de la aplicación (desarrollado en React + Vite + TypeScript) consumirá esta API REST a través de HTTP (
`http://localhost:8080`).

### Enlace al Repositorio Client

* **Frontend Web App:
  ** [ruta-libre-frontend-java-course](https://github.com/MacarenaCavieres/ruta-libre-frontend-java-course)

## 5. Soporte CORS

Para permitir que la aplicación cliente interactúe con el backend desde su servidor de desarrollo (ej.
`http://localhost:5173`), la API expone reglas CORS en su configuración global en Spring Boot mediante:
