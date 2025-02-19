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


CMD ["java", "-spring.datasource.url=${SPRING_DATASOURCE_URL}", \
           "-spring.datasource.username=${SPRING_DATASOURCE_USERNAME}", \
           "-spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}", \
           "-api.security.token.secret=${DECODE_JWT}", \
           "-jar", "/app/app.jar"]