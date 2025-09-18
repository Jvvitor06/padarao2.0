# ------------------------------
# Etapa 1: Build da aplicação com Maven
# ------------------------------
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

# Copia pom e código-fonte
COPY pom.xml .
COPY src ./src

# Baixa dependências offline (opcional)
RUN mvn dependency:go-offline

# Build do projeto sem executar testes
RUN mvn clean package -DskipTests

# ------------------------------
# Etapa 2: Imagem final para rodar o JAR
# ------------------------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado na etapa anterior (usa wildcard para não depender do nome exato)
COPY --from=builder /app/target/*.jar app.jar

# Define porta padrão e expõe
ENV PORT=8080
EXPOSE 8080

# Habilita UTF-8 e define perfil padrão
ENV LANG=C.UTF-8
ENV SPRING_PROFILES_ACTIVE=default

# Comando para iniciar o app garantindo que Spring Boot use a porta do Render
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
