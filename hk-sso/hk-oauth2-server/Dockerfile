FROM anapsix/alpine-java

#指定作者
MAINTAINER huangkai huankai@139.com

COPY target/hk-oauth2-server-2.1.8.RELEASE.jar /app/hk-oauth2-server-2.1.8.RELEASE.jar

# 指定用户，可以是用户名，也可以是用户id
USER 1000

# 指定工作目录
WORKDIR /app

# 只是声明了容器应该打开的端口并没有实际上将它打开
# 需要使用-p或者-P中指定要映射的端口，容器端口才能映射出去
EXPOSE 7100

# 执行命令，配置JVM 初始内存(-Xms) ，最大内存(-Xmx)
# -Duser.home 指定用户家目录，这里只是一个虚拟目录，在容器中的 1000 用户并不存在，对应的是宿主机中的 uid 为 1000的用户
# https://www.cnblogs.com/parryyang/p/5750146.html

#
# -Xmx 设置JVM最大可用内存
# -Xms 设置JVM促使内存.此值可以设置与-Xmx相同,以避免每次垃圾回收完成后JVM重新分配内存
# -Xmn 设置年轻代大小，整个JVM内存大小=年轻代大小 + 年老代大小 + 持久代大小。持久代一般固定大小为64m，所以增大年轻代后，将会减小年老代大小。此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。
# -XX:+DisableExplicitGC //忽略手动调用GC, System.gc()的调用就会变成一个空调用，完全不触发GC
# -XX:+UseConcMarkSweepGC //并发标记清除（CMS）收集器
#

ENTRYPOINT ["java", "-server","-XX:MetaspaceSize=256m","-XX:MaxMetaspaceSize=256m","-XX:+DisableExplicitGC","-XX:+UseConcMarkSweepGC","-Xms1024m","-Xmx1024m","-Xmn256m","-Duser.home=/app","-jar","hk-oauth2-server-2.1.8.RELEASE.jar"]


