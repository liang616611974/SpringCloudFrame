#!/bin/sh

dataPath=`docker inspect  -f '{{json .Mounts}}' article | jq .[0] | jq .Source`

dataPath=${dataPath//\"/}

echo $dataPath

tail -300 ${dataPath}/log/smart-gf-article/smart-gf-article.log

