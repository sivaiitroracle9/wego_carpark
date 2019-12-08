FROM openjdk:8

RUN mkdir -p /wego/wego_carpark_system
COPY ./target/application-0.0.1-SNAPSHOT.jar /wego/carpark-availability/application-0.0.1-SNAPSHOT.jar
RUN chmod +x /wego/wego_carpark_system/application-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /wego/wego_carpark_system/application-0.0.1-SNAPSHOT.jar
