FROM ubuntu:latest
FROM openjdk:21
LABEL authors="arthur"
VOLUME /tmp
EXPOSE 8888
ADD ./target/ms-config-server-0.0.1-SNAPSHOT.jar ms-config-server.jar
ENTRYPOINT ["java","-jar","/ms-config-server.jar"]