FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="subhashchd"
ADD target/empmgt-0.0.1-SNAPSHOT.jar final-empmgp.jar

ENTRYPOINT ["java","-jar","final-empmgp.jar"]
