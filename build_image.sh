#/bin/bash

docker build -f nuxeo-docker-image/build-os/Dockerfile -t wcl/nuxeo-ubuntu .
docker build -f nuxeo-docker-image/build-wcl-image/Dockerfile -t wcl/nuxeo .
