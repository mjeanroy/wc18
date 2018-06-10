##
# Copyright (C) Mickael Jeanroy - All Rights Reserved.
# Unauthorized copying of this file, via any medium is strictly prohibited.
# Proprietary and confidential.
##

# Greatly inspiried from: https://github.com/johnpapa/angular-cli-docker-ootb

# --------------------------------------------- #
# First stage: build the application            #
# --------------------------------------------- #

FROM node:8.9-alpine as wc18-front-built
WORKDIR /app/wc18
COPY package.json package.json
COPY package-lock.json package-lock.json
RUN npm install
COPY . .
RUN npm run dist

# --------------------------------------------- #
# Second stage: create the production image.    #
# --------------------------------------------- #

FROM nginx:alpine
LABEL author="Mickael Jeanroy"
RUN apk update && apk upgrade
COPY --from=wc18-front-built /app/wc18/dist/wc18-front /usr/share/nginx/html
EXPOSE 80 443
CMD [ "nginx", "-g", "daemon off;" ]
