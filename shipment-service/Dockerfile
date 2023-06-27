FROM adoptopenjdk/openjdk11:alpine
ADD target/shipment-service-0.0.1-SNAPSHOT.jar shipment-service.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/shipment-service.jar"]

