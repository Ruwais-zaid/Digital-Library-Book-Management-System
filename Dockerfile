# official OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim
# Set working directory inside the container
WORKDIR /app

# Copy built JAR file into the container
COPY target/*.jar app.jar


# Expose port
EXPOSE 8080


#Entry Point to run application
ENTRYPOINT ["java", "-jar", "app.jar"]