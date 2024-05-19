FROM openjdk:21
VOLUME /tmp
ADD ./target/ms-reservation-0.0.1-SNAPSHOT.jar ms-reservation.jar
ENTRYPOINT ["java","-jar","/ms-reservation.jar"]