# Use an official Maven image to build the application
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and compile the application
COPY src ./src
RUN mvn package -DskipTests

# Use OpenJDK for the runtime image
FROM openjdk:11-jre

# Copy the compiled JAR file from the builder
COPY --from=build /app/target/*.jar app.jar

# Set the command to run the JAR file
ENTRYPOINT ["java","-jar","/app.jar"]