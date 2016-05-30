# GOSSAMER - A Grails HipChat Application

**WORK IN PROGRESS. PLEASE DON'T REFER TO THIS YET**

The aim of this application is to provide a basic framework for developing a
sample HipChat application in Grails 3.1.x complete with JWT authentication.

## Dependencies
* Java 8
* Grails 3.1.6
* Database - Any

Note: This is  REST only app and doesnt have any HTML or JS assets.

## Running
* If Grails 3.1.6 is installed then ``> grails run-app``
* If just gradle is installed then ``> gradle bootRun``
* Default user is "user" and password "password" with role ADMIN.
* Database is defaulted to h2, but the application supports Postgres.
