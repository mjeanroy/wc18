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
