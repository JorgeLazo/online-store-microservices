# Online-Store---Arquitectura-de-Microservicios-con-Spring-Cloud

Este proyecto es una plataforma de comercio electrónico diseñada bajo un enfoque de arquitectura de microservicios descentralizada. Implementa patrones clave de sistemas distribuidos utilizando el ecosistema de Spring Boot, Spring Cloud y componentes de Netflix OSS para garantizar alta disponibilidad, tolerancia a fallos y escalabilidad horizontal.

## 🚀 Arquitectura del Sistema

El sistema está compuesto por múltiples componentes especializados que interactúan entre sí de manera transparente:

### 1. Componentes de Infraestructura (Spring Cloud)
*   **API Gateway (Edge Service):** Actúa como el punto único de entrada para todas las solicitudes de los clientes (Browser, Mobile, IoT). Se encarga de la redirección de rutas y la seguridad perimetral.
*   **Service Registry (Eureka Server):** Directorio centralizado donde cada microservicio se registra dinámicamente al iniciar, permitiendo la localización automática de servicios (Service Discovery).
*   **Load Balancer (Ribbon):** Balanceador de carga del lado del cliente que distribuye las peticiones equitativamente entre las instancias disponibles de un mismo microservicio.
*   **Config Server (Spring Cloud Config):** Centraliza la configuración de todos los entornos (desarrollo, producción) en un repositorio externo, permitiendo cambios en caliente sin reiniciar los servicios.
*   **Circuit Breaker (Hystrix):** Mecanismo de tolerancia a fallos que aisla los servicios caídos o lentos, evitando el efecto de degradación en cascada mediante respuestas alternativas (*fallbacks*).
*   **Distributed Tracing (Sleuth):** Rastreo de peticiones de extremo a extremo asignando IDs únicos a cada transacción para facilitar la auditoría y depuración en entornos distribuidos.

### 2. Microservicios de Negocio
Cada microservicio cuenta con su propia lógica, ciclo de vida independiente y base de datos aislada (patrón *Database per Service*):
*   **Product Service:** Gestión del catálogo de productos, inventario y categorías.
*   **Customer Service:** Administración de perfiles de usuario, autenticación y datos de clientes.
*   **Shopping Service:** Gestión del carrito de compras, procesamiento de órdenes y transacciones.

### 3. Persistencia y Mensajería
*   **Databases:** Almacenamiento independiente por servicio para garantizar el desacoplamiento.
*   **Message Brokers:** Comunicación asíncrona basada en eventos entre microservicios para mantener la consistencia eventual.
*   **Logging Centralizado:** Recolección de trazas y logs utilizando herramientas como **Log4j** y el stack **ELK** (Elasticsearch, Logstash, Kibana).

---

## 🛠️ Tecnologías Utilizadas

*   **Backend:** Java / Spring Boot
*   **Microservicios & Cloud:** Spring Cloud (Eureka, Ribbon, Hystrix, Config Server, Gateway, Sleuth)
*   **Bases de Datos:** *[Especificar aquí: ej. PostgreSQL, MongoDB, MySQL]*
*   **Mensajería:** *[Especificar aquí: ej. RabbitMQ, Apache Kafka]*
*   **Monitoreo y Logs:** Hystrix Dashboard, ELK Stack

---

## 🚦 Orden de Encendido de los Servicios

Debido a las dependencias de infraestructura, los servicios deben iniciarse estrictamente en el siguiente orden:

1.  **Microservicios de Negocio (Product, Customer, Shopping):** Se registran automáticamente en Eureka y obtienen su configuración del Config Server.
2.  **Config Server:** Debe estar listo primero para proveer las propiedades al resto de componentes.
3.  **Eureka Server:** Para que los demás servicios tengan dónde registrarse al iniciar.
4.  **Feign:**
5.  **Dashboards (Hystrix)**
6.  **API Gateway:** Una vez que los servicios de negocio estén registrados.
7.  **Actuator & Admin:**
8.  **Sleuth:**
9.  **Bases de Datos y Message Brokers:** Infraestructura de persistencia y comunicación.
6   **Dashboards (Hystrix, Sleuth):** Para comenzar el monitoreo del ecosistema.

---

## 📐 Patrones de Diseño Implementados

*   **API Gateway Pattern:** Centralización de llamadas de clientes.
*   **Service Discovery:** Registro y localización dinámica de instancias.
*   **Circuit Breaker:** Resiliencia y tolerancia a fallos.
*   **Database per Service:** Autonomía total de los datos de cada microservicio.
*   **Externalized Configuration:** Configuración independiente del código fuente.
