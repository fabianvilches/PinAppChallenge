# Microservicio para Gestión de Clientes con Mensajería Asíncrona

Este proyecto es un microservicio basado en Spring Boot para administrar clientes y gestionar tareas asíncronas usando RabbitMQ. Se despliega en Google Cloud Platform (GCP) utilizando Cloud Run.

## Cómo ejecutar el proyecto
Requisitos previos
* Java 21 instalado (java -version para verificar).
* Maven (mvn -v para verificar).
* Docker (docker -v para verificar).
* RabbitMQ (docker run -d -p 5672:5672 rabbitmq para correrlo en Docker).
* Cuenta en Google Cloud Platform con Google Cloud SDK instalado.

## Pasos para ejecutar localmente
- Clonar el repositorio:
	git clone https://github.com/fabianvilches/pinapp-challenge.git
	cd pinapp-challenge
- Construir el proyecto con Maven:
	mvn clean install
- Ejecutar el servicio: 
	mvn spring-boot:run
- Acceder a Swagger
	http://localhost:8080/swagger-ui/
	
## Ejecutar con docker
- Construir la imagen Docker:
	docker build -t pinapp-challenge
- Construir la imagen Docker:
	docker build -t pinapp-challenge 
- Ejecutar el contenedor:
	docker run -d -p 8080:8080 pinapp-challenge
- Acceder al servicio:
	http://localhost:8080/swagger-ui/

## Despliegue en GCP con Cloud Run
- Configurar Google Cloud SDK:
	gcloud init
	gcloud config set project PinAppChallenge

- Subir la imagen Docker a Google Container Registry:
	docker build -t gcr.io/PinAppChallenge/pinapp-challenge .
	gcloud auth configure-docker
	docker push gcr.io/PinAppChallenge/pinapp-challenge

- Desplegar en Cloud Run:
   gcloud run deploy pinapp-challenge \
       --image gcr.io/PinAppChallenge/pinapp-challenge \
       --platform managed \
       --region us-central1 \
       --allow-unauthenticated

- Acceder al microservicio:
	Google generará una URL pública "https://pinapp-challenge-xyz.run.app"

A partir de ahi se puede probar los endpoints desde el navegador o desde postman

## Pruebas con Postman
- Generar Token JWT:
   POST /auth/login?username=Fabian
   Copiar el token devuelto.

- Acceder a Endpoints Protegidos:
   GET /clientes
   Agregar Authorization: Bearer TOKEN_GENERADO en headers.

## Decisiones Arquitectónicas
- Patrones de Diseño Aplicados:
	DTOs y Records: Uso de record en Java 21 para transferir datos de manera 
	eficiente. 
	Singleton y Bean Management: Uso de @Bean y @Configuration en Spring Boot. 
	Mensajería Asíncrona con RabbitMQ: Implementación de RabbitListener para tareas 
	no bloqueantes.
	Seguridad con JWT: Autenticación basada en tokens para protección de endpoints.  	Persistencia con JPA: Uso de Spring Data JPA para manejo eficiente de base de 
	datos MySQL.

## Herramientas Utilizadas
- Spring Boot 3.x: Framework para desarrollo rápido. 
- Docker: Contenerización para despliegue en GCP.
- RabbitMQ: Procesamiento asíncrono de tareas de clientes.
- JWT: Seguridad basada en tokens.
- Swagger: Documentación interactiva de la API.
- Maven: Gestión de dependencias y compilación.
- Google Cloud Run: Plataforma serverless de GCP.


## Mensajería Asíncrona en PinApp-Challenge
Este microservicio implementa **RabbitMQ** para gestionar tareas asíncronas relacionadas con clientes, mejorando la eficiencia y evitando bloqueos en procesos críticos.

## Casos de Uso
- Generación de reportes de clientes en segundo plano
- Envío de notificaciones automáticas tras registro
- Procesamiento de datos y validaciones sin afectar el rendimiento

## Implementación
La solución usa una cola llamada 'clientesQueue', donde los eventos de cliente se envían y procesan de manera asíncrona por el consumidor de mensajes.

## Tecnologías Usadas
- Spring Boot
- RabbitMQ
- Spring AMQP
