# Stage 1: Build com Maven
FROM maven:3.9-amazoncorretto-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn ./.mvn
COPY mvnw.cmd .

# Executa o build do Maven
RUN ./mvnw clean package

# Stage 2: Runtime
FROM openjdk:17-jdk

WORKDIR /app

# Copia o JAR do stage de build
COPY --from=build /app/target/comparador-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Configurações para desenvolvimento
ENV SPRING_DEVTOOLS_REMOTE_SECRET=mysecret
ENV SPRING_DEVTOOLS_RESTART_ENABLED=true

# Comando para rodar com suporte a devtools
ENTRYPOINT ["java", "-jar", "-Dspring.devtools.restart.enabled=true", "-Dspring-boot.run.fork=false", "app.jar"]

# Para construir e rodar:
# docker build -t comparador-app .
# docker run -p 8080:8080 comparador-app
