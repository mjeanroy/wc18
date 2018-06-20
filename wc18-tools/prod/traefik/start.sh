#!/bin/bash

##
# The MIT License (MIT)
#
# Copyright (c) 2018 Mickael Jeanroy
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.
##

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
