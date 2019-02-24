FROM docker.io/java:8-jre-alpine

#指定作者
MAINTAINER huangkai huankai@139.com

# 定义变量
ARG SERVER_NAME=hk-springboot-admin-server-2.1.2.RELEASE.jar

COPY target/$SERVER_NAME /app/$SERVER_NAME

# 指定工作目录
WORKDIR /app

# 只是声明了容器应该打开的端口并没有实际上将它打开
# 需要使用-p或者-P中指定要映射的端口，容器端口才能映射出去
EXPOSE [8780]

# 执行命令
ENTRYPOINT ["java","-jar","$SERVER_NAME"]
