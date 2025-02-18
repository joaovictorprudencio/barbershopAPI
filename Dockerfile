FROM maven:3.9.4-eclipse-temurin-21 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN  mvn clean install -DskipTests

FROM openjdk:21

COPY --from=build /app/target/barbershop-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar"]