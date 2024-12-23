# Use the official OpenJDK 17 image as a base image
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven && apt-get clean

# Copy the Maven wrapper files and the pom.xml to the container
COPY pom.xml .

# Download dependencies (to cache Maven dependencies layer)
RUN mvn dependency:go-offline

# Copy the entire source code to the container
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Add a sleep to give MySQL time to start
CMD ["sh", "-c", "sleep 30 && java -jar target/back-end-0.0.1-SNAPSHOT.jar"]

# Expose the application port
EXPOSE 8085
