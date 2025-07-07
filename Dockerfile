FROM eclipse-temurin:22-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8443

ENTRYPOINT ["java", "-jar", "app.jar"]