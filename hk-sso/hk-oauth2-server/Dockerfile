FROM docker.io/java:8-jre-alpine

COPY target/hk-oauth2-server-2.1.2.RELEASE.jar /app/hk-oauth2-server-2.1.2.RELEASE.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","hk-oauth2-server-2.1.2.RELEASE.jar"]
