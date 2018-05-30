##
# Copyright (C) Mickael Jeanroy - All Rights Reserved.
# Unauthorized copying of this file, via any medium is strictly prohibited.
# Proprietary and confidential.
##

# Greatly inspiried from: https://github.com/johnpapa/angular-cli-docker-ootb

FROM node:8.9-alpine as angular-built
WORKDIR /app/wc18
COPY package.json package.json
COPY package-lock.json package-lock.json
RUN npm install --silent
COPY . .
RUN npm run dist

FROM nginx:alpine
LABEL author="Mickael Jeanroy"
COPY --from=angular-built /app/wc18/dist /usr/share/nginx/html
EXPOSE 80 443
CMD [ "nginx", "-g", "daemon off;" ]
