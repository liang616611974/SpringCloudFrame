#!/bin/sh

cont_name=$1

if [ x"$cont_name" = x ]; then
	echo "容器名称参数没有输入"
	exit 1
fi

echo "container_name="${cont_name}

docker stop $cont_name
docker rm -v $cont_name
#删除所有未运行的容器（已经运行的删除不了，未运行的就一起被删除了）
docker rm $(sudo docker ps -a -q)
# 删除过时的镜像
docker rmi $(docker images -q -f dangling=true)

