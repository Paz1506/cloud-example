[![Build Status](https://travis-ci.org/Paz1506/cloud-example.svg?branch=master)](https://travis-ci.org/Paz1506/cloud-example)
[![codebeat badge](https://codebeat.co/badges/fde06c30-2c9c-4b85-996d-1fdadc2d8419)](https://codebeat.co/projects/github-com-paz1506-cloud-example-master)


<h2>Spring cloud example</h2>

The example of an application for working with the microservice architecture based on the Spring Boot / Cloud & Netflix stack:
* Config server & clients
* Eureka discovery server & clients
* Zuul gateway
* Feign client
* Spring OAuth2 authorization server

> **Note:** Configuration files for config-service [are here](https://github.com/Paz1506/spring-cloud-config)

<h3>API</h3>

The list of available API endpoints can be seen in Swagger for services:
* autos: http://localhost:8077/swagger-ui.html
* models: http://localhost:8078/swagger-ui.html

<h3>Example</h3>

0) Start all microservices: config server must be started by the first.

1) Get OAuth token (user or admin):

> **CURL:** curl.exe -X POST -u my-client:my-secret -d "username=admin&password=123456&grant_type=password" http://localhost:8055/oauth/token**

2) Send a request with the access token:

> **CURL:** curl.exe -X GET --header "Authorization: Bearer 6b90765e-8d51-4d27-9d98-ab21d8527751"  http://localhost:8078/models/dto/00000000-0000-0000-0000-000000000000
