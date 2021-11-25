# Nuxeo Platform

## Installation

1) Build Nuxeo Source on ./nuxeo directory
```shell
cd nuxeo
mvn install -Paddons,distrib -DskipTests
```
3) Create Docker Image, wcl/nuxeo
```shell
./build_image.sh
```
OR
```shell
docker build -f nuxeo-docker-image/build-os/Dockerfile -t wcl/nuxeo-ubuntu .
docker build -f nuxeo-docker-image/build-wcl-image/Dockerfile -t wcl/nuxeo .
```
3) Run Nuxeo with docker compose
```shell
docker-compose -f nuxeo-docker-image/build-wcl-image/docker-compose.yml up -d
```