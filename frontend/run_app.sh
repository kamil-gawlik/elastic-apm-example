#!/usr/bin/env bash

./mvnw package -DskipTests

APM_HOST=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' infrastructure_apm_1)

java -javaagent:../infrastructure/agent/apm-agent.jar \
     -Delastic.apm.service_name=frontend \
     -Delastic.apm.server_urls=http://${APM_HOST}:8200 \
     -Delastic.apm.application_packages=com.kambi \
     -jar ./target/frontend-0.0.1-SNAPSHOT.jar
