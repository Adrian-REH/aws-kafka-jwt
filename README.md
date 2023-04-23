# Microservicios con Kafka, JWT y AWS

El proyecto consiste en desarrollar un sistema de autenticación y procesamiento asíncrono de mensajes en un foro web utilizando las tecnologías Jwt y Kafka. El objetivo es permitir que los usuarios autenticados puedan publicar mensajes que se procesen de manera asíncrona mediante Kafka, y que se envíen notificaciones por correo electrónico a los autores de los mensajes. El proyecto implica el diseño y la implementación de la arquitectura del sistema, así como la integración de las tecnologías Jwt y Kafka. El resultado final será un sistema eficiente y escalable para gestionar los mensajes en el foro web.



## Arquitectura
### Estructura de Archivos en MICROSERVICIOS JAVA:
1. Autenticación
Lo utiliza como **_producer_** luego de que el usuario se logearse satisfactoriamente utilizando JWT para verificar el acceso.

2. Publicacion de Mensajes
Lo utilizo como **_consumer_** para que el usuario prosiga con la publicacion de en la web utilizando JWT para verificar el acceso.



### Arquitectura de AWS: 


 Con la ayuda de **_AWS_** puedo implementar **_Kafka_** con **_Zookeeper_** en el Cluster **_MKS_** y para que los microservicios que estan subidos a Docker en **_EC2_** puedan funcionar con Kafka.
 
```
         +-----------------------------------------------------+
         |                      AMAZON EC2                     |
         |                                                     |
         |   +----------------+            +----------------+  |
         |   |                |            |                |  |
         |   |  DOCKER        |            |  DOCKER        |  |
         |   |   CONTAINER 1  |            |   CONTAINER 2  |  |
         |   |                |            |                |  |
         |   |  - publicación |            | - Autenticación|  |
         |   |  de mensajes   |            |                |  |
         |   |                |            |                |  |
         |   +-------+--------+            +-------+--------+  |
         +-----------------------------------------------------+
                                  |
           +----------------------+----------------------+
           |                                             |
           |                     AMAZON MSK              |
           |                                             |
           |  - Apache Kafka                             |
           |  - ZooKeeper                                |
           |                                             |
           +----------------------+----------------------+



```
## Kafka con AWS
Para el procesamiento asíncrono de mensajes usare AWS MSK que proporciona el servicio para Apache Kafka completamente administrado, seguro y con alta disponibilidad que usa el punto de enlace privado para Consumir/Producir mensajes en los microservicios.
 
### Proceso
1. Utilizo MSK de AWS

   - Creo un Cluster en MSK AWS
   - Veo la informacion del Cliente 
   - Guardo el punto de enlace privado (PEP)
2. Programo en kafka en Java

   - Configuro `Application.Properties`
   ```
   ```
   - En `KafkaConfig`, defino BootstrpService pegando el PEP
 
  
## JWT 

### Creo los DTO
1. `LoginRequest.java`
2. `RegisterRequest.java`
3. `MessageResponse.java`

### Creo las Entities
1. `User.java`
   - **_id_**
   - **_username_**
   - **_password_**
   - **_email_**
2. `Rol.java`
   - **_id_**
   - **_name_**
   - **_description_**

### Configuro los roles
```
```
### Defino el Token y encripto el password.
```
```

## Testing

### Test para los Controller
Utilizare MockSpring
### Test para el Repositorio
Utilizare @JPADataTest
### Test para los Servicios 
Utilizare Junit 

## Dependencias y Configuracion

* Java 17
* Spring 2.5.5
* Spring boot devtools
* Spring Web
* Spring Security
* Spring Data JPA
* Spring PostgreSql
* Spring Kafka
* Lombok
* Jwt
```
```
* Swagger 
```
```
* JUnit
```
```
* Aws
```
```

## Licencia






