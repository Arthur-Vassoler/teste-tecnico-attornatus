FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

WORKDIR /app

COPY app/attornatus-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
