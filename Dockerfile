FROM openjdk:11
RUN mkdir -p "app"
WORKDIR app
COPY ./* /app
CMD ["./gradlew", "build"]
COPY ./build/libs/boongboard-0.0.1-SNAPSHOT.jar app.jar