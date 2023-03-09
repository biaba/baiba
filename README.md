# Functionality

The Spring Security application using PostgreSQL with following functionalities:

 - Non registered users can login/register
 - Registered users with default 'user' role can see list of books
 - Registered users with 'admin' role can add book
 - Books are displayed in order - latest at top

User access:
username: user
password: user

Admin access:
username: admin
password: admin

# Used technologies and tools:
- Postgres DB (configured to run as docker)
- pgAdmin interface to access DB (configured to run as docker)
- Liquibase to seed data and create tables

Tests include:
- unit tests for user and role services

# Instructions to start application
- java 11 JDK required
- docker required
- git clone
- to start db and pgAdmin:
  -- docker run --name postgres16 -e POSTGRES_PASSWORD=admin -p 5432:5432 --net postgres-net -v db-data:/var/lib/postgresql/data postgres
  -- docker run -p 8081:80 -e PGADMIN_DEFAULT_PASSWORD=admin -e PGADMIN_DEFAULT_EMAIL=admin@admin.com --net postgres-net --name postgres-interface dpage/pgadmin4
  - for application to run, db should be created:
    1. Open localhost:8081 and login with username: admin@admin.com, password: admin
    2. add server with following configuration (see images in README_IMAGES folder within project)
    3. create database - books
- run mvn clean install and run application
- go to localhost:8083 and enjoy

# To create and run .war locally
- clone project
- run:  mvn clean package spring-boot:repackage
- run: java -jar target/tieto-books.war