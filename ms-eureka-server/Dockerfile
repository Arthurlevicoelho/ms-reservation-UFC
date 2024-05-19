FROM ubuntu:latest
FROM openjdk:21
LABEL authors="arthur"
VOLUME /tmp
EXPOSE 8761
ADD ./target/ms-eureka-server-0.0.1-SNAPSHOT.jar ms-eureka-server.jar
ENTRYPOINT ["java","-jar","/ms-eureka-server.jar"]