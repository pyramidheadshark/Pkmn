FROM maven:latest AS build

COPY pom.xml pom.xml
COPY src src

RUN mvn clean package

FROM openjdk:11-jre-slim
ARG JAR_FILE=/target/*.jar
COPY --from=build ${JAR_FILE} /app/pkmn.jar
ENTRYPOINT ["java","-jar","/app/pkmn.jar"]