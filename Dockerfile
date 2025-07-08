FROM eclipse-temurin:22-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]