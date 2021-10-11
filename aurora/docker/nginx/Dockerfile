# 基础镜像
FROM nginx
# author
MAINTAINER aurora

# 挂载目录
VOLUME /home/aurora/projects/aurora-ui
# 创建目录
RUN mkdir -p /home/aurora/projects/aurora-ui
# 指定路径
WORKDIR /home/aurora/projects/aurora-ui
# 复制conf文件到路径
COPY ./conf/nginx.conf /etc/nginx/nginx.conf
# 复制html文件到路径
COPY ./html/dist /home/aurora/projects/aurora-ui
