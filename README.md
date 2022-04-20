# Nuxeo Platform

### About

This Nuxeo (10.10) source has downloaded from <https://github.com/nuxeo/nuxeo>.

## Pre-install (Mac)

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
## Pre-install (Linux Ubuntu 20.04)
1. Update Ubuntu and reboot
```shell
Login as root
apt update
apt upgrade
reboot
```
2. Install Python2, OpenJDK 8, maven, Node 10.24.1, bower, gulp, grunt, unzip
```shell
apt install python2
apt-get install openjdk-8-openjdk
apt install maven

curl -sL https://deb.nodesource.com/setup_10.x | bash -
sudo apt-get install -y nodejs
npm install -g bower
apt install gulp
apt install grunt
apt install unzip
bower install -g grunt
npm install -g --force grunt-cli
npm install -g --force gulp
```
3. Install Dart SDK (version 1.23.0)
```shell
Download from https://dart.dev/get-dart/archive
Add to PATH
```
4. Install Docker
```shell
Download from https://www.docker.com/products/docker-desktop
```
---
## Compile Nuxeo & Build Docker Image

1) Install Local Maven Repository 
```shell
Create ~/.m2 if not exists
Add git.config/settings.xml to ~/.m2
```

2) Build Nuxeo Source on ./nuxeo directory
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
4) Run Nuxeo with docker compose
```shell
docker compose -f nuxeo-docker-image/run-docker/docker-compose.yml up -d
```