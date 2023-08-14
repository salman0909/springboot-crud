# Use the official OpenJDK as the base image
FROM openjdk:8
ADD target/springboot-crud-integration.jar springboot-crud-integration.jar
# Specify the command to run your Spring Boot application
ENTRYPOINT ["java","-jar","/springboot-crud-integration.jar"]
EXPOSE 8000