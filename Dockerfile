# Use a lightweight Java 17 runtime
FROM openjdk:17-jdk-slim

# Copy the built jar file into the container
# NOTE: This assumes you have run 'mvn package'
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]