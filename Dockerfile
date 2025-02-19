
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app


COPY pom.xml .


RUN mvn dependency:go-offline


COPY src ./src


RUN mvn clean install -DskipTests


FROM openjdk:21

WORKDIR /app

      SPRING_DATASOURCE_URL: ${SPRING_DATASOURCE_URL}
      SPRING_DATASOURCE_USERNAME: ${SPRING_DATASOURCE_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
      DECODE_JWT: ${DECODE_JWT}

COPY --from=build /app/target/barbershop-0.0.1-SNAPSHOT.jar /app/app.jar


EXPOSE 8080


CMD ["java", "-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", \
           "-Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME}", \
           "-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}", \
           "-Ddecode.jwt=${DECODE_JWT}", \
           "-jar", "/app/app.jar"]