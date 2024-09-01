FROM ubuntu:latestc as build
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY ..
LABEL authors="Krygo"

ENTRYPOINT ["java", "-b"]