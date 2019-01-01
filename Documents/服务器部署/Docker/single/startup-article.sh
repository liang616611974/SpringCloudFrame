#!/bin/sh

tag=$1

if [ x"$tag" != x ]; then
	tag=:${tag}
fi

echo 'tag='${tag}

# 启动容器
docker run -d -v /etc/localtime:/etc/localtime -p 8071:8070 --name article docker.gf.com.cn/gfsmart/article${tag}  --server.port=8070 \
#--app.article.private.uploadWaitTime=50 \
#--app.article.private.filePieceSize=10240


sleep 5s
curl http://127.0.0.1:8071/article/test
docker exec -it article bash
