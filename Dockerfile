   # Build stage
   FROM maven:3.8-openjdk-17 AS build
   WORKDIR /app
   COPY pom.xml .
   COPY src ./src
   RUN mvn clean package -DskipTests


   # Run stage
   FROM openjdk:17
   WORKDIR /app
   # Instead of hardcoding the exact JAR name, use a pattern
   COPY --from=build /app/target/*.jar app.jar
   EXPOSE 8080
   ENTRYPOINT ["java", "-jar", "app.jar"]