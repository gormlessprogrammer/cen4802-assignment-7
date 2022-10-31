FROM openjdk:8
EXPOSE 8080
ADD target/docker-lilly-assignment-seven.jar docker-lilly-assignment-seven.jar
ENTRYPOINT ["java", "-jar","docker-lilly-assignment-seven.jar"]

