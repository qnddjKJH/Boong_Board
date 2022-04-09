FROM openjdk:11
VOLUME /app
ENTRYPOINT ["./gradlew", "bootJar"]