FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ./target/ms-book-api-0.0.1.jar ms-book-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/ms-book-api.jar"]