FROM maven:3.6.1-jdk-8-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean install -Dspring.profiles.active=prod -DskipTests

FROM openjdk:9
COPY --from=build /usr/src/app/target/ekwal-sharez-api-0.0.1-SNAPSHOT.jar /usr/app/ekwal-sharez-api-0.0.1-SNAPSHOT.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","/usr/app/ekwal-sharez-api-0.0.1-SNAPSHOT.jar","--spring.profiles.active=dev"]