##
# Copyright (C) Mickael Jeanroy - All Rights Reserved.
# Unauthorized copying of this file, via any medium is strictly prohibited.
# Proprietary and confidential.
##

# --------------------------------------------- #
# First stage: build the application            #
# --------------------------------------------- #

FROM maven:3.5.3-jdk-8-alpine as wc18-back-built
WORKDIR /app/wc18
COPY . .
RUN mvn clean package

# --------------------------------------------- #
# Second stage: deploy the application on jetty #
# --------------------------------------------- #

FROM jetty:9.4.9-jre8-alpine
LABEL author="Mickael Jeanroy"
COPY --from=wc18-back-built /app/wc18/wc18-api/target/wc18-api.war /var/lib/jetty/webapps
RUN mv /var/lib/jetty/webapps/wc18-api.war /var/lib/jetty/webapps/ROOT.war
EXPOSE 8080
CMD ["java", "-jar", "/usr/local/jetty/start.jar"]
