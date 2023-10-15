FROM maven:3-eclipse-temurin-17 as builder

COPY pom.xml /opt/
COPY src /opt/src
WORKDIR /opt
# Add define for skipping tests
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17

COPY --from=builder /opt/target/out.jar /opt/

CMD java -jar /opt/out.jar
EXPOSE 8080
