FROM docker.io/java:8-jre-alpine

COPY target/hk-cas-server-2.1.2.RELEASE.jar /app/hk-cas-server-2.1.2.RELEASE.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","hk-cas-server-2.1.2.RELEASE.jar"]
