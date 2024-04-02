# Use the official OpenJDK as the base image
#FROM openjdk:8
#ADD target/springboot-crud.jar springboot-crud.jar
# Specify the command to run your Spring Boot application
#ENTRYPOINT ["java","-jar","/springboot-crud.jar"]
#EXPOSE 8081

#H2-Database
#EXPOSE 9092

# Stage 1: Build frontend
FROM node:14 AS frontend-build
WORKDIR /app/frontend
COPY src/main/resources/META-INF /app/frontend
RUN npm install
RUN npm run build

# Stage 2: Build Spring Boot application
FROM openjdk:8 AS springboot-build
COPY target/springboot-crud.jar /app/springboot-crud.jar

# Stage 3: Final image
FROM openjdk:8
COPY --from=springboot-build /app/springboot-crud.jar /springboot-crud.jar
COPY --from=frontend-build /app/frontend/dist /frontend
ENTRYPOINT ["java","-jar","/springboot-crud.jar"]
EXPOSE 8081
