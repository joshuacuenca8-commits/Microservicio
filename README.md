# MS-Productos — Microservicio de Productos

Microservicio REST desarrollado con Spring Boot para la gestión de productos. Forma parte de una arquitectura de microservicios junto a `ms-usuarios` y `ms-ordenes`.

---

## Tecnologías

| Tecnología | Versión |
|-----------|---------|
| Java | 21 |
| Spring Boot | 3.5.11 |
| Spring Cloud | 2025.0.0 |
| Spring Data JPA | - |
| H2 Database | Runtime |
| Springdoc OpenAPI | 2.8.6 |
| Lombok | - |
| JUnit 5 + Mockito | - |
| JaCoCo | 0.8.11 |
| Docker | - |

---

## Estructura del proyecto
```
Microservicio/
├── src/
│   ├── main/
│   │   ├── java/com/joshuaCuenca/microservicio/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   └── config/
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-uat.yml
│   │       └── application-prod.yml
│   └── test/
├── Dockerfile
└── pom.xml
```

---

## Profiles

| Profile | Base de datos | Uso |
|---------|--------------|-----|
| `dev` | H2 en memoria | Desarrollo local |
| `uat` | MySQL | Pruebas |
| `prod` | MySQL | Producción |

---

##  Cómo correr el proyecto

### Local
```bash
git clone https://github.com/tuUsuario/Microservicio.git
cd Microservicio
./mvnw spring-boot:run
```

### Con Docker
```bash
docker build -t microservicio .
docker run -p 8081:8081 --name mi-microservicio microservicio
```

---

## Endpoints

Base URL: `http://localhost:8081/api/productos`

| Método | Ruta | Descripción | Status |
|--------|------|-------------|--------|
| GET | `/` | Obtener todos | 200 |
| GET | `/{id}` | Obtener por ID | 200 / 404 |
| POST | `/` | Crear producto | 201 / 400 |
| PUT | `/{id}` | Actualizar | 200 / 404 |
| DELETE | `/{id}` | Eliminar | 204 / 404 |

### Request — POST
```json
{
    "nombre": "Laptop Dell",
    "precio": 999.99,
    "stock": 10
}
```

### Response — 201
```json
{
    "id": 1,
    "nombre": "Laptop Dell",
    "precio": 999.99,
    "stock": 10
}
```

---

## 📖 Swagger
```
http://localhost:8081/swagger-ui.html
```

---

##  Tests
```bash
# Correr tests
./mvnw test

# Reporte de cobertura
./mvnw test jacoco:report
# Reporte en: target/site/jacoco/index.html
```

**Cobertura actual: 76%**

| Tipo | Clase | Tests |
|------|-------|-------|
| Unitarios | ProductoServiceImplTest | 5 |
| Integración | ProductoControllerIntegrationTest | 7 |

---

## H2 Console (dev)
```
http://localhost:8081/h2-console
JDBC URL: jdbc:h2:mem:productos_dev
Username: sa
Password: (vacío)
```

---

##Ramas

| Rama | Descripción |
|------|-------------|
| `main` | Código estable |


---

## Autor

**Joshua Cuenca**
Becario Backend Java — Curso Microservicios con Spring Boot




