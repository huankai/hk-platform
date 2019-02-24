FROM docker.io/java:8-jre-alpine

COPY target/hk-fs-web-2.1.2.RELEASE.jar /app/hk-fs-web-2.1.2.RELEASE.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","hk-fs-web-2.1.2.RELEASE.jar"]
