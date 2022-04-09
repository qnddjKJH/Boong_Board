FROM openjdk:11
CMD ["./gradlew", "build"]
COPY ./build/libs/boongboard-0.0.1-SNAPSHOT.jar app.jar