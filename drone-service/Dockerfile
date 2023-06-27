FROM adoptopenjdk/openjdk11:alpine
ADD target/drone-service-0.0.1-SNAPSHOT.jar drone-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/drone-service.jar"]