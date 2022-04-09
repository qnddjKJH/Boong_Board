FROM openjdk:11
WORKDIR app
CMD ["./gradlew", "clean", "build"]
COPY ./build/libs/boongboard-0.0.1-SNAPSHOT.jar ./app.jar