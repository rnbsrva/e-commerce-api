#!/usr/bin/env bash

wait-for-it $PG_HOST:$PG_PORT -t 60
wait-for-it $REDIS_HOST:6379 -t 60
wait-for-it $ELASTICSEARCH_HOST:9200 -t 180

jq '.[].database = env.PG_DATABASE' schema.json | sponge schema.json


bootstrap --config ./schema.json
pgsync --config ./schema.json -d