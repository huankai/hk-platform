FROM anapsix/alpine-java
#指定作者
MAINTAINER huangkai huankai@139.com

COPY target/hk-spring-cloud-gateway-2.1.8.RELEASE.jar /app/hk-spring-cloud-gateway-2.1.8.RELEASE.jar

# 指定用户，可以是用户名，也可以是用户id
USER 1000

# 指定工作目录
WORKDIR /app

# 只是声明了容器应该打开的端口并没有实际上将它打开
# 需要使用-p或者-P中指定要映射的端口，容器端口才能映射出去
EXPOSE 8771

# 执行命令，配置JVM 初始内存(-Xms) ，最大内存(-Xmx)
# -Duser.home 指定用户家目录，这里只是一个虚拟目录，在容器中的 1000 用户并不存在，对应的是宿主机中的 uid 为 1000的用户
ENTRYPOINT ["java","-XX:MetaspaceSize=256m","-XX:MaxMetaspaceSize=256m","-Xms1024m","-Xmx1024m","-Xmn256m","-XX:+UseConcMarkSweepGC","-Duser.home=/app","-jar","hk-spring-cloud-gateway-2.1.8.RELEASE.jar"]


