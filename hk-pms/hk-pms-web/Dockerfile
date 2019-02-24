FROM docker.io/java:8-jre-alpine

COPY target/hk-pms-web-2.1.2.RELEASE.jar /app/hk-pms-web-2.1.2.RELEASE.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","hk-pms-web-2.1.2.RELEASE.jar"]
