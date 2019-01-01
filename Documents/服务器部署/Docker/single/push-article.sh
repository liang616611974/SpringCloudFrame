#!/bin/sh

tag=$1

if [ x"$tag" = x ]; then
	echo "docker-tag镜像标签没有输入"
	exit 1
fi

echo "tag="${tag}

docker push docker.gf.com.cn/gfsmart/article:${tag}

curl https://docker.gf.com.cn/tags?image=gfsmart/article
