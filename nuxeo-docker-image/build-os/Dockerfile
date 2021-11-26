FROM       openjdk:11.0-jre-slim-buster
MAINTAINER WCL <support@wclsolution.com>

# Add needed convert tools
RUN apt-get update && apt-get install -y --no-install-recommends \
    perl \
    locales \
    pwgen \
    imagemagick \
    ffmpeg2theora \
    ufraw \
    poppler-utils \
    libwpd-tools \
    exiftool \
    ghostscript \
    libreoffice \
    ffmpeg \
    x264 \
    netcat \
    wget \
    unzip \
    procps \
    ccextractor \
 && rm -rf /var/lib/apt/lists/*

COPY nuxeo-docker-image/build-os/config/policy.xml /etc/ImageMagick-6/policy.xml

# Remove setuid/setgid binaries from images for security
RUN find / -perm 6000 -type f -exec chmod a-s {} \; || true
