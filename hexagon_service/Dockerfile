
FROM adoptopenjdk:11-jre-hotspot-bionic
LABEL description="_description_"
USER root

# Project setup
ENV PROJECT _projectName_
ENV TZ UTC
ENV PORT 9090

EXPOSE $PORT

# Machine setup
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Project install
USER 1000
ADD --chown=1000:1000 build/distributions/$PROJECT*.tar /opt
WORKDIR /opt/$PROJECT*
ENTRYPOINT /opt/$PROJECT*/bin/$PROJECT
