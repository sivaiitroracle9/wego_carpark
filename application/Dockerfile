FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/sivaiitroracle9/wego_carpark.git

FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY --from=0 /app/wego_carpark/application /app
RUN mvn clean install -DskipTests=true
RUN ls -ltr /app/target/*

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app/target/application-0.0.1-SNAPSHOT.jar
