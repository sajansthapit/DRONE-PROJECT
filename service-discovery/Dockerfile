FROM adoptopenjdk/openjdk11:alpine
ADD target/service-discovery-0.0.1-SNAPSHOT.jar service-discovery.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/service-discovery.jar"]
