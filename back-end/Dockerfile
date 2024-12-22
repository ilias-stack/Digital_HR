# Use an official Maven image to build the application
FROM maven:3.8.6-openjdk-17-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code into the container
COPY src ./src

# Package the application into a jar file
RUN mvn clean package -DskipTests

# Use an OpenJDK image for running the application
FROM openjdk:17-oracle

# Set the working directory
WORKDIR /app

# Copy the jar file from the builder stage into the container
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the application will run on
EXPOSE 8085

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
