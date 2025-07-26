FROM eclipse-temurin:17-jre-alpine

# Timezone
ENV TZ=Africa/Nairobi
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Rudementary
WORKDIR /app
# Application name same as deployment name
LABEL name="consumer-microloan-service" \
description="Service for credit-score" \
maintainer="mpesa-integrations-user"
MAINTAINER "Maintainer of the project"

#Port the default 
EXPOSE 6060


# Add App
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/service.jar

RUN addgroup -S mpesa-integrations-user && adduser -S mpesa-integrations-user -G mpesa-integrations-user

RUN chown -R mpesa-integrations-user:mpesa-integrations-user /app

USER mpesa-integrations-user

ENTRYPOINT ["java","-jar","/app/service.jar"]
