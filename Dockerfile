FROM openjdk:11
WORKDIR app
CMD ["./gradlew", "build"]
COPY ./build/libs/*.jar app.jar