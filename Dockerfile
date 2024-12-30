# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/*.jar app.jar

# Environment variables for MongoDB connection
ENV MONGO_HOST=mongodb
ENV MONGO_PORT=27017
ENV MONGO_DATABASE=employee_allocation

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
