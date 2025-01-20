# Use Amazon Corretto as the base image (JDK 17)
FROM amazoncorretto:17

# Define an argument for the JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file into the Docker image
COPY ${JAR_FILE} application.jar

# Set the entry point to run the JAR file using Java
ENTRYPOINT ["java", "-jar", "/application.jar"]