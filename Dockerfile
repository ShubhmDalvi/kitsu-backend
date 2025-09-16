# Stage 1: Build the application
# Use a valid Maven image that includes a JDK. eclipse-temurin provides reliable OpenJDK builds.
FROM maven:3.9.6-eclipse-temurin-17 AS build
# WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final, smaller runtime image
# Use a lightweight OpenJDK image to reduce the final image size.
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]