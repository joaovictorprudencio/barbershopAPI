# Etapa 1: Construção do JAR
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app


COPY pom.xml .


RUN mvn dependency:go-offline


COPY src ./src


RUN mvn clean install -DskipTests


FROM openjdk:21

WORKDIR /app


COPY --from=build /app/target/barbershop-0.0.1-SNAPSHOT.jar /app/app.jar


EXPOSE 8080



CMD ["java", "-jar", "/app/app.jar"]