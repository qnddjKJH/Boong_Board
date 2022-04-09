FROM openjdk:11
CMD ["./gradlew", "build"]
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar