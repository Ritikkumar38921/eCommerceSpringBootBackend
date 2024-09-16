FROM eclipse-temurin:17-jdk-focal

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Install Maven
RUN apt-get update && apt-get install -y maven

# Verify Maven installation
RUN mvn --version

# Build the application and skip tests
RUN mvn clean package -DskipTests

# Expose the port the app runs on
EXPOSE 5000

# Run the jar file
ENTRYPOINT ["java","-jar","target/product-0.0.1-SNAPSHOT.jar"]