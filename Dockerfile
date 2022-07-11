FROM alpine
RUN apk add --no-cache openjdk17
COPY target/Social-network-rest-api-1.jar Social-network-rest-api-1.jar
ENTRYPOINT ["java","-jar","Social-network-rest-api-1.jar"]