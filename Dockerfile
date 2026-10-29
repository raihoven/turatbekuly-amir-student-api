# ---- Stage 1: build (JDK + Gradle wrapper) ----
FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace
COPY gradlew settings.gradle build.gradle ./
COPY gradle gradle
COPY src src
RUN chmod +x gradlew && ./gradlew --no-daemon bootJar -x test

# ---- Stage 2: runtime (JRE only) ----
FROM eclipse-temurin:17-jre
LABEL maintainer="Turatbekuly Amir"
WORKDIR /app
COPY --from=build /workspace/build/libs/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
