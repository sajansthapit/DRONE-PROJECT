FROM adoptopenjdk/openjdk11:alpine
ADD target/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/notification-service.jar"]
