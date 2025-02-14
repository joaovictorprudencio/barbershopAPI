FROM maven:3.9.9-openjdk-21 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM openjdk:21-jre-slim

COPY --from=build /app/target/myapp.jar /app/myapp.jar

WORKDIR /app

CMD ["java", "-jar", "myapp.jar"]