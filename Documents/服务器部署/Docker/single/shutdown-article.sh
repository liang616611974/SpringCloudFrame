#!/bin/sh

docker stop article
docker rm -v article
#删除所有未运行的容器（已经运行的删除不了，未运行的就一起被删除了）
docker rm $(sudo docker ps -a -q)
# 删除过时的镜像
docker rmi $(docker images -q -f dangling=true)

