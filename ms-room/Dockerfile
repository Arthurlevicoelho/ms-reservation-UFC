FROM openjdk:21
VOLUME /tmp
ADD ./target/ms-room-0.0.1-SNAPSHOT.jar ms-room.jar
ENTRYPOINT ["java","-jar","/ms-room.jar"]