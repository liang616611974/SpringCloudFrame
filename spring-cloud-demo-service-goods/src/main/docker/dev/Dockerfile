# 居于java镜像
FROM java

# 将本地文件夹挂载到当前的容器
VOLUME /app

# 创建所需的目录
RUN mkdir /app-work

# 指定工作目录
WORKDIR /app-work

# 指定当前用户
#USER root

# 复制文件到容器
COPY spring-cloud-demo-service-goods-1.0-SNAPSHOT.jar app.jar

# 声明要暴露的接口
EXPOSE 8091

# 指定语言环境
ENV LANG C.UTF-8

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.library.path=/app-work","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
# 命令的参数
CMD ["--server.port=8091"]
