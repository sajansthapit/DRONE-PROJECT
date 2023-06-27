FROM adoptopenjdk/openjdk11:alpine
ADD target/medication-service-0.0.1-SNAPSHOT.jar medication-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/medication-service.jar"]
