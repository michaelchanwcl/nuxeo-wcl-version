# Nuxeo Platform

### About

This Nuxeo (10.10) source has downloaded from <https://github.com/nuxeo/nuxeo>.

## Pre-install

1) Install openjdk ver 8, maven, npm, bower, gulp-cli, grunt, node version 10
```shell
brew install openjdk@8
brew install maven
brew install npm
brew install bower
brew install gulp-cli
brew install grunt
brew install node@10
```
2) Add Node into PATH
```shell
echo 'export PATH="/usr/local/opt/node@10/bin:$PATH"' >> ~/.bash_profile
```
3) Install Dart SDK (version 1.23.0)
```text
Download from https://dart.dev/get-dart/archive
Add to PATH
```

4) Install xcode select
```shell
xcode-select --install
```
5) Install docker
```text
Download from https://www.docker.com/products/docker-desktop
```
---
## Compile Nuxeo & Build Docker Image

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