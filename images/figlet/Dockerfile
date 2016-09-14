FROM       docker-integration:latest
MAINTAINER Matthias Stock <mail@mstock.de>

RUN apt-get update && apt-get install -y --no-install-recommends figlet
RUN mkdir -p /var/docker-integration

COPY config.json /var/docker-integration
COPY figlet-writer.sh /var/docker-integration
ENV PATH="/var/docker-integration:${PATH}"
