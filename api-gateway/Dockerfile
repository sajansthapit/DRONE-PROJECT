FROM adoptopenjdk/openjdk11:alpine
ADD target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/api-gateway.jar"]