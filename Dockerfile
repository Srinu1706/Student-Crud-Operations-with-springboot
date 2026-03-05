                         # Stage 1: Build the application
                         FROM eclipse-temurin:17-jdk-alpine AS build
                         WORKDIR /app

                         # Copy maven executable and pom file
                         COPY .mvn/ .mvn
                         COPY mvnw pom.xml ./
                         RUN chmod +x mvnw

                         # Copy the source code
                         COPY src ./src

                         # Build the application (skipping tests for speed)
                         RUN ./mvnw clean package -DskipTests

                         # Stage 2: Run the application
                         FROM eclipse-temurin:17-jre-alpine
                         WORKDIR /app

                         # Copy only the built JAR from the build stage
                         COPY --from=build /app/target/*.jar app.jar

                         EXPOSE 8080
                         ENTRYPOINT ["java", "-jar", "app.jar"]