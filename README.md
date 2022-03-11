# disbursement-application

This system is used to calculate disbursements that must be paid to merchants whom use Sequra's payment service as
payment solutions in their markets.

### If I got time, what I would like to do?

on database:
1. Create indexes for database columns that seems need enhance performance;
2. Create a scheduled trigger job in database to run disbursements calculation;

on web rest UI:
1. Add HATEOAS to improve navigation among the API's endpoints;
2. Add pagination into json results;
3. Add ExceptionHandler to all endpoint routes;

on application in general:
1. Add logs to improve debugs process;
2. Turn all entities auditable by using Spring Auditing;

### Requirements

There is two ways to run this project
1. Using docker and docker-compose (easier):

   1.1. Must have installed docker and docker-compose;


2. By running via maven:

   2.1. Must have maven installed;

   2.3. Must have Postegresql 10 installed;

## How to RUN

First you need to download this repository to your computer.

### Running by docker and docker-compose
1. After downloaded open terminal and go inside the project folder
2. Run: `docker-compose -f docker-compose.yml up -d`;
3. Wait postgres container and disbursement-app container be started then you can use application;

PS: You can pass environment variables as well like `DATABASE_HOST=postgresql_service DATABASE_USERNAME=sequra DATABASE_PASSWORD=sequrachallenge123 DATABASE_PORT=5432 docker-compose up -d`


### Running by maven
1. Setup database called `disbursements` to be listening on `5432` with owner `sequra` and password `sequrachallenge123`;
2. Open terminal into project's folder;
3. Run: `mvn spring-boot:run -Dspring-boot.run.profiles=prod`;
4. Application will run without problems;

PS: You can pass environment variables as well, like `DATABASE_HOST=postgresql_service DATABASE_USERNAME=sequra DATABASE_PASSWORD=sequrachallenge123 DATABASE_PORT=5432 mvn spring-boot:run -Dspring-boot.run.profiles=prod`

### Strategy planned, but not executed

This system was planned to be built in three part at least:
1. **Domain module**: This module would be able to hold all business logic, validations rules, business models,
   required attributes, would be able to decide if the entity must be persisted or not, in general based on business rules
   and validations;
2. **Infrastructure Module**: This module would be able to do everything that domain-module suppose to validate.
   This module would contain the **HOW TO DO** what, the ways to do such things, as connect to a database, communicate
   with a external API, how to persiste an object;
3. **Web-application Module**: Basically this module could be the bridge between user and system by delivering
   a WEB UI (such a REST API, html pages, CLI, etc.). This module should use all above modules to reach his objective.

Time wasn't enough to do all that I wanted for.






