FROM openjdk:17-buster
WORKDIR /app
COPY target/books-api-0.0.1-SNAPSHOT.jar /app/books-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/books-api-0.0.1-SNAPSHOT.jar"]