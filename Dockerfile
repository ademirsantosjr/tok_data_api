FROM maven:3-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21

WORKDIR /app

COPY --from=builder /app/target/cadastro-0.0.1-SNAPSHOT.jar ./app.jar

CMD ["java", "-jar", "app.jar"]

