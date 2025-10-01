# ------------------------------
# Etapa 1: Build da aplicação com Maven
# ------------------------------
FROM maven:3.9.2-openjdk-21 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

# ------------------------------
# Etapa 2: Imagem final para rodar o JAR
# ------------------------------
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENV LANG=C.UTF-8
ENV SPRING_PROFILES_ACTIVE=default

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
