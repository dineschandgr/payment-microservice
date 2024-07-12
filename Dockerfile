FROM openjdk:21
WORKDIR /app
COPY ./target/payment-service-0.0.1-SNAPSHOT.jar /app
EXPOSE 8764
CMD ["java", "-jar", "payment-service-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","payment-service-0.0.1-SNAPSHOT.jar"]