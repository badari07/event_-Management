# Use OpenJDK as the base image
# Use OpenJDK 23 as the base image
FROM eclipse-temurin:23-jdk

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/event-management-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
