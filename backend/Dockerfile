# Stage 1: Build and test the Spring Boot app
FROM maven:3.9.6-eclipse-temurin-21 AS backend-builder

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

# Install packages (build the JAR)
RUN mvn clean package

# Run tests
RUN mvn test

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=backend-builder /app/target/GymManagementApplication.jar app.jar

# Copy environment config (optional)
COPY .env.docker .env

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Use OpenJDK 17 as the base image
#FROM openjdk:21

# Set the working directory inside the container
#WORKDIR /app

# Copy the JAR file from the target folder of your project
#COPY target/GymManagementApplication.jar /app/GymManagementApplication.jar

# Expose the port your Spring Boot app will run on
#EXPOSE 8080

# Command to run your Spring Boot application
#ENTRYPOINT ["java", "-jar", "/app/GymManagementApplication.jar"]
