#!/bin/sh

docker-compose -f docker-compose-base.yml up -d
docker-compose -f docker-compose-service.yml up -d

