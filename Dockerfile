FROM openjdk:8
EXPOSE 8080
ADD target/cen4802-assignment-7.jar cen4802-assignment-7.jar
ENTRYPOINT ["java", "-jar","cen4802-assignment-7.jar"]

