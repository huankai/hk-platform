FROM docker.io/java:8-jre-alpine

COPY target/hk-discover-eureka-2.1.2.RELEASE.jar /app/hk-discover-eureka-2.1.2.RELEASE.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","hk-discover-eureka-2.1.2.RELEASE.jar"]
