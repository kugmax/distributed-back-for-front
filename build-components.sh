#!/bin/bash

mvn -f books/pom.xml clean
mvn -f books/pom.xml package

mvn -f authors/pom.xml clean
mvn -f authors/pom.xml package

mvn -f web-sockets/pom.xml clean
mvn -f web-sockets/pom.xml package

mvn -f MobileApp/pom.xml clean
mvn -f MobileApp/pom.xml package

mvn -f FrontApp/pom.xml clean
mvn -f FrontApp/pom.xml package

mvn -f Frontend/pom.xml clean
mvn -f Frontend/pom.xml package

mvn -f Gateway/pom.xml clean
mvn -f Gateway/pom.xml package

mvn -f GatewayMobile/pom.xml clean
mvn -f GatewayMobile/pom.xml package

docker build -t lohika/bff-books-service:1.0 -f books/Dockerfile books
docker build -t lohika/bff-authors-service:1.0 -f authors/Dockerfile authors
docker build -t lohika/bff-web-sockets-service:1.0 -f web-sockets/Dockerfile web-sockets

docker build -t lohika/bff-mobile-app:1.0 -f MobileApp/Dockerfile MobileApp
docker build -t lohika/bff-front-app:1.0 -f FrontApp/Dockerfile FrontApp

docker build -t lohika/bff-frontend-app:1.0 -f Frontend/Dockerfile Frontend
docker build -t lohika/bff-gateway-app:1.0 -f Gateway/Dockerfile Gateway
docker build -t lohika/bff-gateway-mobile-app:1.0 -f GatewayMobile/Dockerfile GatewayMobile



