FROM java:8-jre-alpine

RUN mkdir -p /opt/claro-api
ADD target/claro-0.0.1-SNAPSHOT.jar opt/claro-api

WORKDIR /opt/claro-api

CMD java -jar claro-0.0.1-SNAPSHOT.jar