#!/bin/bash

echo "[debug] Starting traefik reverse proxy"
echo "[debug] Using PWD: ${PWD}"

docker run -d \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v ${PWD}/traefik.toml:/traefik.toml \
  -v ${PWD}/acme.json:/acme.json \
  -p 80:80 \
  -p 443:443 \
  -l traefik.frontend.rule=Host:traefik.wc18.mr-robot.sh \
  -l traefik.port=8080 \
  --network wc18_network \
  --name traefik \
  traefik:1.6.3-alpine --docker
