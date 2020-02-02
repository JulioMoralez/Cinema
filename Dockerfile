FROM openjdk:8-jdk-alpine

EXPOSE 8080

ADD target/cinema-1.0.jar cinema.jar
ENTRYPOINT ["java", "-jar", "cinema.jar"]