FROM openjdk:17-buster
COPY target/books-api-0.0.1-SNAPSHOT.jar books-api-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/books-api-0.0.1-SNAPSHOT.jar"]