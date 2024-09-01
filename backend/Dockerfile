FROM openjdk:21-jdk-oracle
VOLUME /tmp
COPY out/artifacts/zti_jar/*.jar app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]
EXPOSE 8080