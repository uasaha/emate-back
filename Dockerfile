FROM eclipse-temurin:17
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} back.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/back.jar"]