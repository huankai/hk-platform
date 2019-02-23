FROM docker.io/java:8-jre-alpine

COPY target/hk-config-server-2.1.2.RELEASE.jar /app/hk-config-server-2.1.2.RELEASE.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","hk-config-server-2.1.2.RELEASE.jar"]