FROM openjdk:11
CMD ["./gradlew", "build"]
COPY ./build/libs/*.jar app.jar