FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/cwitter-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENV DB_HOST=db
ENV DB_PORT=5432
ENV DB_NAME=cwitter
ENV DB_USER=cwitter
ENV DB_PASSWORD=cwitter
CMD java -jar cwitter-0.0.1-SNAPSHOT.jar \
    --spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME} \
    --spring.datasource.username=${DB_USER} \
    --spring.datasource.password=${DB_PASSWORD}