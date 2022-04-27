# vim:set ft=dockerfile:
FROM       wcl/nuxeo-ubuntu
MAINTAINER WCL <support@wclsolution.com>

ENV NUXEO_USER nuxeo
ENV NUXEO_HOME /opt/nuxeo/server
ENV HOME /opt/nuxeo/server
ENV NUXEO_LOG /var/log/nuxeo
ARG NUXEO_BUILD_VERSION=10.10-HF57-SNAPSHOT
ARG NUXEO_VERSION=master
#ARG NUXEO_DIST_URL=http://community.nuxeo.com/static/latest-snapshot/nuxeo-server-tomcat,SNAPSHOT.zip
ARG NUXEO_MD5=noMD5check

# Create Nuxeo user
RUN useradd -m -d /home/$NUXEO_USER -u 1000 -s /bin/bash $NUXEO_USER

COPY nuxeo/nuxeo-distribution/nuxeo-server-tomcat/target/nuxeo-server-tomcat-${NUXEO_BUILD_VERSION}.zip /tmp/nuxeo-distribution-tomcat.zip

# Add distribution
RUN if [ $NUXEO_VERSION != "master" ]; then echo "$NUXEO_MD5 /tmp/nuxeo-distribution-tomcat.zip" | md5sum -c -; fi \
    && mkdir -p /tmp/nuxeo-distribution $(dirname $NUXEO_HOME) \
    && unzip -q -d /tmp/nuxeo-distribution /tmp/nuxeo-distribution-tomcat.zip \
    && DISTDIR=$(/bin/ls /tmp/nuxeo-distribution | head -n 1) \
    && mv /tmp/nuxeo-distribution/$DISTDIR $NUXEO_HOME \
    && sed -i -e "s/^org.nuxeo.distribution.package.*/org.nuxeo.distribution.package=docker/" $NUXEO_HOME/templates/common/config/distribution.properties \
    && rm -rf /tmp/nuxeo-distribution* \
    && chmod +x $NUXEO_HOME/bin/*ctl $NUXEO_HOME/bin/*.sh \
    # For arbitrary user in the admin group
    && chmod g+rwX $NUXEO_HOME/bin/*ctl $NUXEO_HOME/bin/*.sh \
    && chown -R 1000:0 $NUXEO_HOME && chmod -R g+rwX $NUXEO_HOME

# Add Template
COPY nuxeo-docker-image/build-wcl-image/docker-template $NUXEO_HOME/templates/docker
COPY nuxeo-docker-image/build-wcl-image/nuxeo.conf /etc/nuxeo/nuxeo.conf.template
ENV NUXEO_CONF /etc/nuxeo/nuxeo.conf

# Protecting writeable directories to support arbitrary User IDs for OpenShift
# https://docs.openshift.com/container-platform/3.4/creating_images/guidelines.html
RUN chown -R 1000:0 /etc/nuxeo && chmod g+rwX /etc/nuxeo && rm -f $NUXEO_HOME/bin/nuxeo.conf \
    && mkdir -p /var/lib/nuxeo/data \
    && chown -R 1000:0 /var/lib/nuxeo/data && chmod -R g+rwX /var/lib/nuxeo/data \
    && mkdir -p /var/log/nuxeo \
    && chown -R 1000:0 /var/log/nuxeo && chmod -R g+rwX /var/log/nuxeo \
    && mkdir -p /var/run/nuxeo \
    && chown -R 1000:0 /var/run/nuxeo && chmod -R g+rwX /var/run/nuxeo \
    && mkdir -p /docker-entrypoint-initnuxeo.d \
    && chown -R 1000:0 /docker-entrypoint-initnuxeo.d && chmod -R g+rwX /docker-entrypoint-initnuxeo.d  \
    && chmod g=u /etc/passwd

ENV PATH $NUXEO_HOME/bin:$PATH

# COPY wait-for
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/wait-for.sh /
RUN chmod +x /wait-for.sh

COPY nuxeo-docker-image/build-wcl-image/docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

# MySQL JDBC Driver update
RUN rm $NUXEO_HOME/templates/mysql/lib/mysql-connector-java-8.0.13.jar
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/mysql-connector-java-8.0.27.jar $NUXEO_HOME/templates/mysql/lib
# WEB UI
COPY --chown=1000:0 nuxeo/addons/nuxeo-web-ui/target/nuxeo-web-ui-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
# Nuxeo Drive Server
COPY --chown=1000:0 nuxeo/addons/nuxeo-drive-server/nuxeo-drive-core/target/nuxeo-drive-core-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-drive-server/nuxeo-drive-elasticsearch/target/nuxeo-drive-elasticsearch-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-drive-server/nuxeo-drive-mongodb/target/nuxeo-drive-mongodb-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-drive-server/nuxeo-drive-operations/target/nuxeo-drive-operations-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-drive-server/nuxeo-drive-web-ui/target/nuxeo-drive-web-ui-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-drive-server/nuxeo-drive-rest-api/target/nuxeo-drive-rest-api-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/templates/drive $NUXEO_HOME/templates/drive
# DAM
COPY --chown=1000:0 nuxeo/addons/nuxeo-dam/nuxeo-dam/target/nuxeo-dam-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-platform-audio/nuxeo-platform-audio-core/target/nuxeo-platform-audio-core-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/nuxeo-features/nuxeo-platform-imaging/nuxeo-platform-imaging-core/target/nuxeo-platform-imaging-core-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/nuxeo-features/nuxeo-platform-imaging/nuxeo-platform-imaging-convert/target/nuxeo-platform-imaging-convert-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/nuxeo-features/nuxeo-platform-imaging/nuxeo-platform-imaging-api/target/nuxeo-platform-imaging-api-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-platform-video/nuxeo-platform-video-api/target/nuxeo-platform-video-api-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-platform-video/nuxeo-platform-video-convert/target/nuxeo-platform-video-convert-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-platform-video/nuxeo-platform-video-core/target/nuxeo-platform-video-core-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-dam/nuxeo-dam-web-ui/target/nuxeo-dam-web-ui-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/jaudiotagger-2.0.3.jar $NUXEO_HOME/nxserver/lib
# EasyShare
COPY --chown=1000:0 nuxeo/addons/nuxeo-easyshare/nuxeo-easyshare-core/target/nuxeo-easyshare-core-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/nuxeo-apt-extensions-1.0.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/nuxeo-webengine-apt-1.2.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-easyshare/nuxeo-easyshare-web-ui/target/nuxeo-easyshare-web-ui-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
# Retention
COPY --chown=1000:0 nuxeo/nuxeo-services/nuxeo-platform-actions-core/target/nuxeo-platform-actions-core-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-retention/nuxeo-retention-core/target/nuxeo-retention-core-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-retention/nuxeo-retention-web-ui/target/nuxeo-retention-web-ui-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/templates/retention $NUXEO_HOME/templates/retention
# WOPI
COPY --chown=1000:0 nuxeo/addons/nuxeo-wopi/nuxeo-wopi/target/nuxeo-wopi-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo/addons/nuxeo-wopi/nuxeo-wopi-web-ui/target/nuxeo-wopi-web-ui-${NUXEO_BUILD_VERSION}.jar $NUXEO_HOME/nxserver/bundlesre
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/jackson-dataformat-xml-2.9.10.jar $NUXEO_HOME/nxserver/lib
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/jackson-jaxrs-base-2.9.10.jar $NUXEO_HOME/nxserver/lib
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/jackson-jaxrs-json-provider-2.9.10.jar $NUXEO_HOME/nxserver/lib
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/jackson-module-jaxb-annotations-2.9.10.jar $NUXEO_HOME/nxserver/lib
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/jutf7-1.0.0.jar $NUXEO_HOME/nxserver/bundles
# WCL Custom Document Type

# WCL Security Keyword Policy
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/docpal-sam-master-SNAPSHOT.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/docpal-security-keyword-policy-core-1.0-SNAPSHOT.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/docpal-user-security-keywords-master-SNAPSHOT.jar $NUXEO_HOME/nxserver/bundles
COPY --chown=1000:0 nuxeo-docker-image/build-wcl-image/lib/commons-lang3-3.12.0.jar $NUXEO_HOME/nxserver/lib

#COPY --chown=1000:0 $NUXEO_HOME/nxserver/bundles
#COPY --chown=1000:0 $NUXEO_HOME/nxserver/bundles

# COPY --chown=1000:0 $NUXEO_HOME/nxserver/bundles

USER 1000 
# RUN cat /etc/nuxeo/nuxeo.conf.template > $NUXEO_CONF
# RUN echo "nuxeo.log.dir=$NUXEO_LOG" >> $NUXEO_CONF
# RUN echo "nuxeo.pid.dir=/var/run/nuxeo" >> $NUXEO_CONF
# RUN echo "nuxeo.data.dir=/var/lib/nuxeo/data" >> $NUXEO_CONF
# RUN rm $NUXEO_CONF

WORKDIR $NUXEO_HOME
#ENTRYPOINT ["/docker-entrypoint.sh"]
EXPOSE 8080
#CMD ["nuxeoctl","console"]
ENTRYPOINT /wait-for.sh mysql:3306 -t 300 && /wait-for.sh elasticsearch:9200 -t 300 && /docker-entrypoint.sh nuxeoctl console
