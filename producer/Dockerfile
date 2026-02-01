FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy JAR (matches your build output)
COPY target/producer-*.jar app.jar

# Copy script from producer directory (must exist here)
COPY produce-events.sh /app/scripts/produce-events.sh
RUN chmod +x /app/scripts/produce-events.sh

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
