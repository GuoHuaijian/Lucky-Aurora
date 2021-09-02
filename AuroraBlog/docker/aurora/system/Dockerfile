# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER aurora

# 挂载目录
VOLUME /home/aurora
# 创建目录
RUN mkdir -p /home/aurora
# 指定路径
WORKDIR /home/aurora
# 复制jar文件到路径
COPY ./jar/aurora-system.jar /home/aurora/aurora-system.jar
# 启动认证服务
ENTRYPOINT ["java","-jar","aurora-system.jar"]
