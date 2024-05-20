# Microservices with Spring Boot - Reservation :white_check_mark:

<p align="center">
  <a href="">
    <img src="" alt="Version">
  </a>
  <a href="https://github.com/mupezzuol/spring-microservice-loja/stargazers">
    <img src="https:/" alt="Stars">
  </a>
  <a href="">
    <img src="" alt="Forks">
  </a>
  <a href="">
    <img src="" alt="License">
  </a>
</p>

Microservices with Spring and best of all with _`MIT license`_:heart_eyes:, so that we can use these projects as a study or as a basis for new projects, even sharing new ideas:bulb:. Feel free to contribute to these projects right here.:heart:

## Index :pushpin:
- [About the project](#about)
- [How to run](#run)
- [Projects and repositories](#prjrepo)
- [License](#license)

## About the project <a name="about"></a> :link:

I developed this project using _`Spring Boot (3.2.2)`_, so it adopts an architecture based on microservices using all the power of Spring Cloud and its technologies. When we are working with Spring, we have several advantages for gaining technologies and solutions already ready to be implemented, so we made use of some of them.

#### Breaking the domain into services

- I broke the domain of the solution into three projects _`(reservation, room, user)`_, so in our APIs, we use some technologies and solutions to build a robust, secure, traceable and scalable architecture.

#### Spring Cloud Netflix Eureka

- We use _`Netflix Eureka`_ as a _`Service Discovery`_ solution, it is very simple and easy to implement.

#### Spring Cloud Config

- The properties configurations of the projects were all exported and configured through the _`Config Server`_ microservice.

#### Spring Cloud OpenFeign

- Used _`Spring Feign`_ to make calls between microservices in a simple way for its customers, it is a project that was inspired by Retrofit, JAXRS-2.0 and WebSocket. With it we are also able to use the _`Client Side Load Balancer`_ because Feign is integrated with the _`Spring Cloud LoadBalancer`_, which in turn is also integrated with Eureka.

#### Resilience4j

- Used _`Resilience4j`_ that implements the _`Circuit Breaker`_ standard, which very quickly is a _`failover`_ for calls between microservices, that is, if a microservice is down, a _`fallback`_ method is called and that flood of failures is avoided.

#### Spring Cloud Gateway

- Used _`Spring Cloud Gateway`_ as an _`API Gateway`_ because its implementation and its high integration with Netflix Eureka are very simple. Spring Cloud Gateway uses Eureka to know the instances of microservices and, using the Spring Cloud LoadBalancer, is able to load balance user requests.

#### Spring Cloud with Spring Boot

Building distributed systems don't need to be complicated and error-prone. Spring Cloud offers a simple and accessible programming model to the most common distributed system patterns, helping developers build resilient, reliable, and coordinated applications. Spring Cloud is built on top of Spring Boot, making it easy for developers to get started and become productive quickly.

![Spring Cloud](img/spring-cloud.png)

## How to run <a name="run"></a> :wrench:

Follow the instructions below to build and execute the project in a simple and easy way, but feel free to run the way you want.:relaxed:

#### What you need to have installed

- Java 21;
- Maven;
- PostgreSQL(recommended to use Docker);
- Use Postman or curl to perform tests.

#### Database

- We use the _`PostgreSQL`_ database for the all microservice.
- Using _`Docker`_ it is very simple to use the databases, see below:

`PostgreSQL`
```docker
docker run -p 5432:5432 --name ms-room-pg14 --network reservation-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_ms_room postgres:14-alpine
docker run -p 5433:5432 --name ms-reservation-pg14 --network reservation-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_ms_reservation postgres:14-alpine
docker run -p 5434:5432 --name ms-user-pg14 --network reservation-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_ms_user postgres:14-alpine
```

- Docker Official Image: [PostgreSQL](https://hub.docker.com/_/postgres) 

#### Build and Run

- Following the order of the microservices below, execute the command via terminal in their respective microservices:

```sh
mvn clean install spring-boot:run -Dmaven.test.skip=true
```

## Projects and repositories <a name="prjrepo"></a> :file_folder:

#### Applications :computer:

- [spring-microservice-room](https://github.com/Arthurlevicoelho/ms-reservation-UFC/tree/main/ms-room) - Microservice related to the application of the room.
- [spring-microservice-reservation](https://github.com/Arthurlevicoelho/ms-reservation-UFC/tree/main/ms-reservation) - Microservice related to reservation application.
- [spring-microservice-user](https://github.com/Arthurlevicoelho/ms-reservation-UFC/tree/main/ms-user) - Microservice related to the application of the user. 

#### Spring Cloud and Config Server :notebook_with_decorative_cover:

- [spring-microservice-config-server](https://github.com/Arthurlevicoelho/ms-reservation-UFC/tree/main/ms-config-server) - Microservice for spring cloud configuration.
- [spring-microservice-config-server-repo](https://github.com/Arthurlevicoelho/microservice-config-reservation) - Microservice related to the config server repository.

#### Service Registration and Discovery :mag_right:

- [spring-microservice-eureka-server](https://github.com/Arthurlevicoelho/ms-reservation-UFC/tree/main/ms-eureka-server) - Microservice related to Netflix Eureka server application. 

#### API Gateway :traffic_light:

- [spring-microservice-gateway](https://github.com/Arthurlevicoelho/ms-reservation-UFC/tree/main/ms-gateway) - Microservice related to Spring Cloug Gateway server application.

## License <a name="license"></a> :clipboard:

Feel free to contribute, we continue with an _`MIT license`_. :heart_eyes:[here](https://github.com/Arthurlevicoelho/ms-reservation-UFC?tab=MIT-1-ov-file) 
