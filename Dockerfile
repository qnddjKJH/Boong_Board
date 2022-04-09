FROM openjdk:11
WORKDIR app
CMD ["./gradlew", "clean", "build"]
COPY ./build/libs/*.jar app.jar