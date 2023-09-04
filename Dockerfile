FROM openjdk:17-alpine
COPY ./target/tech_proof.jar /usr/app/

WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT java -jar tech_proof.jar ${FILE_PATH}
