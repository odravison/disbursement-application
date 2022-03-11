# Image from Dockerhub
FROM maven:3.8.4-openjdk-17-slim
WORKDIR /usr/src/java-code/disbursement-web-application
COPY . /usr/src/java-code/disbursement-web-application/

ENV DATABASE_HOST=postgresql_service
ENV DATABASE_USERNAME=sequra
ENV DATABASE_PASSWORD=sequrachallenge123
ENV DATABASE_PORT=5432

# There's no need to run tests since that in real world we do run pipelines to assure the quality by running test
RUN mvn package -DskipTests

WORKDIR /config
COPY ./src/main/resources/*.yaml .

WORKDIR /usr/src/java-app
RUN pwd
RUN ls
RUN cp /usr/src/java-code/disbursement-web-application/target/*.jar ./app.jar
EXPOSE 9000
EXPOSE 8000
ENTRYPOINT [ "sh", "-c", "java -jar -Dspring.profiles.active=prod ./app.jar --spring.config.location=/config/" ]