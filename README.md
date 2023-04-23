# Microservicios con Kafka, JWT y AWS

El proyecto consiste en desarrollar un sistema de autenticación y procesamiento asíncrono de mensajes en un foro web utilizando las tecnologías Jwt y Kafka. El objetivo es permitir que los usuarios autenticados puedan publicar mensajes que se procesen de manera asíncrona mediante Kafka, y que se envíen notificaciones por correo electrónico a los autores de los mensajes. El proyecto implica el diseño y la implementación de la arquitectura del sistema, así como la integración de las tecnologías Jwt y Kafka. El resultado final será un sistema eficiente y escalable para gestionar los mensajes en el foro web.



## Arquitectura
### Estructura de MICROSERVICIOS:
1. **Servicio de Autenticación**
```
├── config
│   ├── SwaggerConfig.java
│   ├── KafkaProducerConfig.java
│   └── KafkaConsumerConfig.java
├── controler
│   └── AuthController.java
├── entity
│   ├── Role.java
│   └── User.java
├── repository
│   ├── RoleRepository.java
│   └── UserRepository.java
├── security
│   ├── config
│   │   └── SecurityConfig.java
│   ├── jwt
│   │   ├── JetAuthEntryPoint.java
│   │   ├── JwtRequestFilter.java
│   │   └── JwtTokenUtil.java
│   ├── payload
│   │   ├── JwtResponse.java
│   │   ├── LoginRequest.java
│   │   ├── MessageResponse.java
│   │   └── RegisterRequest.java
│   └── service
│       └── UserDetailsServiceImpl.java
├── service
│   ├── impl
│   │   ├── RoleServiceImpl.java
│   │   └── UserServiceImpl.java
│   ├── AuthService.java
│   ├── RoleService.java
│   └── UserService.java
── test
    └── controllers
       └── AuthControllerTest.java


```
2. **Servicio de Gestion de Mensajes en el Foro**

```
├── config
│   ├── SwaggerConfig.java
│   ├── KafkaProducerConfig.java
│   └── KafkaConsumerConfig.java
├── controler
│   └── MessageController.java
├── dto
│   └── MessageRequest.java
├── entity
│   ├── Message.java
│   └── User.java
├── repository
│   ├── MessageRepository.java
│   └── UserRepository.java
├── service
│   ├── impl
│   │   ├── MessageServiceImpl.java
│   │   └── UserServiceImpl.java
│   ├── MessageService.java
│   └── UserService.java
── test
    └── controllers
       └── MessageController.java


```

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

   - Creo un Cluster
   - Veo la informacion del Cliente 
   - Guardo el punto de enlace privado (PEP)
2. Utilizo EC2 de AWS

   1. Creo una Instancia y me conecto
  
   2. Instalo docker
   ```
   sudo yum update

   sudo yum search docker

   sudo yum info docker

   sudo yum install docker

   sudo usermod -a -G docker ec2-user
   ```
   3. Instalo Java
   ```
   sudo amazon-linux-extras enable corretto8

   sudo yum install java-1.8.0-amazon-corretto

   sudo yum install java-1.8.0-amazon-corretto-devel

   java -version
   ```
   4. Agrego en una Imgen el Microservicio
   ```
   wget https://docker-notify.s3.eu-west-3.amazonaws.com/"NameJavaFile".zip && unzip "NameJavaFile".zip 

   Limpio y entro: rm -rf customers.zip && cd "NameJavaFile"/

   Super Usuario: sudo su

   Creo una Imagen: docker build -t "NameImageDocker"

   Veo las imagenes: docker images

   Corro la imagen:  docker run -it -p "PortSrv":"PortDocker" "NameImageDocker"

   ```

3. Programo en kafka en Java

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






