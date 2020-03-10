FROM maven:3.11
WORKDIR /usr/src
RUN mvn dependency::go-offline
RUN mvn clean package
ADD /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]