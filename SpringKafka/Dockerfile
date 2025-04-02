# Etapa 1: Compilación con Maven
FROM maven:3.9.9-amazoncorretto-21-alpine AS build
WORKDIR /app

# Copiamos el descriptor Maven y descargamos dependencias para aprovechar la cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente y compilamos la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Etapa 2: Ejecución con Amazon Corretto
FROM amazoncorretto:21
WORKDIR /app

# Copiamos el JAR generado desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto (modificar si tu aplicación usa otro puerto)
EXPOSE 8080

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
