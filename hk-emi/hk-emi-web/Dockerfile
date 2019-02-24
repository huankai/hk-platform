FROM docker.io/java:8-jre-alpine

COPY target/hk-emi-web-2.1.2.RELEASE.jar /app/hk-emi-web-2.1.2.RELEASE.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","hk-emi-web-2.1.2.RELEASE.jar"]
