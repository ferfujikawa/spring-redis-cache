FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY mvnw ./
COPY .mvn ./.mvn

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080

WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar

CMD ["java", "-jar", "app.jar"]
