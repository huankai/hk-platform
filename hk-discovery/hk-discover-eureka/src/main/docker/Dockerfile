# eureka Dockerfile
# version 2.0.5.RELEASE

# 基础镜像
FROM openjdk:8-jre-alpine

# 维护者信息
MAINTAINER huangkai

# 添加用户与组
# RUN groupadd -r eureka && useradd -r -g eureka eureka

#指定运行容器的用户名
# USER eureka

VOLUME /data/docker/project/springboot/eureka

# 添加文件
# ADD application.yml application.yml
# ADD hk-discover-eureka-2.0.5.RELEASE.jar eureka-2.0.5.RELEASE.jar

#暴露端口号
EXPOSE 8761

# ENV JAVA_OPTS = ""

#执行命令
ENTRYPOINT ["java","-jar","eureka-2.0.5.RELEASE.jar"]


# ##################################### 以下命令在 shell 窗口执行 #######################

#[huangkai@sjq-20 eureka]$ mkdir -p /data/docker/project/springboot/eureka

#[huangkai@sjq-20 eureka]$ cd /data/docker/project/springboot/eureka

#[huangkai@sjq-20 eureka]$ vim Dockerfile  #，创建Dockerfile将上面的Docker内容添加到此文件中
#将打成的jar包上传到此目录中

#查看此目录内容
#[huangkai@sjq-20 eureka]$ ll
# total 46192
#-rw-rw-r--. 1 huangkai huangkai       22 Oct 29 16:06 application.yml
#-rw-rw-r--. 1 huangkai huangkai      375 Oct 29 14:30 Dockerfile
#-rwxr--r--. 1 huangkai huangkai 47291178 Oct 29 12:15 hk-discover-eureka-2.0.5.RELEASE.jar

# 构建镜像，将当前目录下的所有文件构建到 Dockerfile中的 WORKDIR 目录下，目录不存在会自动创建
#[huangkai@sjq-20 eureka]$ docker build -t eureka:2.0.5 .

#启动镜像
    #[huangkai@sjq-20 eureka]$ docker run --name eureka --restart=always -p 8761:8761 -d -v /data/docker/project/springboot/eureka:/data/docker/project/springboot/eureka 镜像id

# --name 指定容器名称，必须唯一
# --restart 自动启动
# -- 宿主机端口与docker镜像端口映射
# -v  将宿主机的目录挂载到 docker容器中的目录，不管是修改了宿主机挂载目录下的文件或文件夹，都会同步到docker容器中对应的目录中，相反也会同步到宿主机相应的目录中

# 进入容器
#[huangkai@sjq-20 eureka]$ docker exec -it  镜像id /bin/bash
# 如果 /bin/bash 不存在，可以使用 [docker exec -it  镜像id sh]  进入
# 启动 eurkea
#[huangkai@sjq-20 eureka]$ docker start eureka

#查看docker eureka 进程
# [huangkai@sjq-20 eureka]$ docker ps
# CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS              PORTS                               NAMES
# eafe36f06913        457c7e177968             "java -jar hk-disc..."   9 minutes ago       Up 6 minutes        0.0.0.0:8761->8761/tcp              eureka

# 查看docker eureka进程 日志
#[huangkai@sjq-20 eureka]$ docker logs eureka
#2018-10-29 08:04:11.712  INFO 1 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@dfd3711: startup date [Mon Oct 29 08:04:11 GMT 2018]; root of context hierarchy
#2018-10-29 08:04:12.648  INFO 1 --- [           main] f.a.AutowiredAnnotationBeanPostProcessor : JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
#2018-10-29 08:04:12.761  INFO 1 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'configurationPropertiesRebinderAutoConfiguration' of type [org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration$$EnhancerBySpringCGLIB$$d375a233] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)

# 重启 eurkea
#[huangkai@sjq-20 eureka]$ docker restart eureka

# 停止 eurkea
#[huangkai@sjq-20 eureka]$ docker stop eureka

