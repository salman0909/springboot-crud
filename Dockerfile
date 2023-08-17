# Use the official OpenJDK as the base image
FROM openjdk:8
ADD target/springboot-crud.jar springboot-crud.jar
# Specify the command to run your Spring Boot application
ENTRYPOINT ["java","-jar","/springboot-crud.jar"]
EXPOSE 8081

#H2-Database
EXPOSE 9092
