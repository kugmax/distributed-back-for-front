FROM openjdk:12-alpine
ADD target/Frontend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9092
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
