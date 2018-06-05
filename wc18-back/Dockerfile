##
# Copyright (C) Mickael Jeanroy - All Rights Reserved.
# Unauthorized copying of this file, via any medium is strictly prohibited.
# Proprietary and confidential.
##

# ------------------------------------ #
# First stage: build the application   #
# ------------------------------------ #

FROM maven:3.5.3-jdk-8-alpine as wc18-back-built
WORKDIR /app/wc18
COPY . .
RUN mvn clean package
