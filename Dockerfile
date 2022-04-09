FROM openjdk:11
CMD ["./gradlew", "build"]
ARG JAR_FILE=app/build/libs/*.jar
COPY ${JAR_FILE} app.jar