# Container image that runs your code
FROM openjdk:8-jdk
MAINTAINER Mathias Devos (myemail@gmail.com)
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
WORKDIR /usr/local/service
RUN mvn package
CMD ["java", "-cp", "target/docker-service-1.0-SNAPSHOT.jar", "org.ea.service.App"]