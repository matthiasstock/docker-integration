# docker-integration
A prototypical implementation of a containerized legacy software wrapper.

## What It Does
The docker-integration base image acts as a basis for your software you want to
integrate with other systems using a unified interface. It is intended to remove
the need of writing boilerplate code.

## What It Contains
- Maven project for docker-integration
  - uses Spring Boot and Spring MVC
  - builds a docker base image
- a figlet image as an example

## Getting Started
    mvn package docker:build
    docker build -t docker-integration/figlet images/figlet
    MSYS_NO_PATHCONV=1 docker run -d -p 8080:8080 -v /my-hosts-working-directory:/var/docker-integration/work docker-integration/figlet
    curl -s -H "Content-Type: application/json" -X POST -d '{"input":"awesome"}' "http://<your_ip>:8080/task"

Then check your host's volume for a .out.txt file.

## What It Cannot Do Yet
In lack of a real world scenario, the provided interface is pretty basic.
Despite this, a messaging interface would cover more use cases.
