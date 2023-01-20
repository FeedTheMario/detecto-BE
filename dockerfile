FROM maven:3.6.1 AS maven
LABEL MAINTAINER="coppolamario4@gmail.com"

WORKDIR /usr/src/app
COPY ../../../Users/e-mario.coppola/IdeaProjects/detecto-BE /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn clean package

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=detecto-backend.jar

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","detecto-backend.jar"]