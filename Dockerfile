# Container image that runs your code
#FROM openjdk:8-jdk
#MAINTAINER Mathias Devos (myemail@gmail.com)
#RUN apt-get update
#RUN apt-get install -y maven
#COPY pom.xml /usr/local/service/pom.xml
#COPY src /usr/local/service/src
#WORKDIR /usr/local/service
#RUN mvn package
#CMD ["java", "-cp", "target/docker-service-1.0-SNAPSHOT.jar", "org.ea.service.App"]

FROM maven:3.5-jdk-8 as BUILD
COPY src /usr/src/myapp/src
COPY pom.xml /usr/src/myapp
RUN mvn -f /usr/src/myapp/pom.xml clean package

FROM tomcat:7.0
COPY --from=BUILD /usr/src/myapp/target/*.war /usr/local/tomcat/webapps/ROOT.war
#ENV TZ=America/Los_Angeles
#EXPOSE 8080