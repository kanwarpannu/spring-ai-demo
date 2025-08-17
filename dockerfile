FROM ollama/ollama:latest AS ollama-base

FROM ollama-base
RUN mkdir /opt/app
COPY ./target/ai-demo-0.0.1-SNAPSHOT.jar /opt/app/app.jar

RUN apt-get update && \
    apt-get install -y --no-install-recommends wget curl ca-certificates  && \
    apt-get install -y --no-install-recommends openjdk-21-jdk

COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

EXPOSE 8080 11434

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]


# CMD ["java", "-jar", "/opt/app/app.jar"]