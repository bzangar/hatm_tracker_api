FROM openjdk:25-ea-24-jdk-slim-bullseye
WORKDIR /app
COPY target/hatm_tracker_api-1.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
