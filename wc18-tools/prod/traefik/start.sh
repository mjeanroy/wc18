#!/bin/bash

echo "[debug] Starting traefik reverse proxy"
echo "[debug] Using PWD: ${PWD}"

echo "[debug] Creating acme.json file"
rm -rf ${PWD}/acme.json
touch ${PWD}/acme.json
chmod 600 ${PWD}/acme.json

echo "[debug] Starting docker traefik image"
docker run -d \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v ${PWD}/traefik.toml:/traefik.toml \
  -v ${PWD}/acme.json:/acme.json \
  -p 80:80 \
  -p 443:443 \
  -l traefik.frontend.rule=Host:traefik.mr-robot.sh \
  -l traefik.port=8080 \
  --network wc18_network \
  --name traefik \
  traefik:1.5.4 --docker
