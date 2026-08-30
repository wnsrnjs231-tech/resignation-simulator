# ==================================================
# 1. Spring Boot 프로젝트 빌드
# ==================================================

FROM gradle:8-jdk17 AS build

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew clean build -x test


# ==================================================
# 2. Spring Boot 실행
# ==================================================

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]