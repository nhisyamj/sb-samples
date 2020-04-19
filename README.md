# sb-samples

This project consists of sample implementation of Spring Boot features

## Pre-requisites

- Java 11
- Maven 3

## 1. Spring Boot with Log4j2.


### Reference Documentation
This project is about implementing Log4j2 in Spring Boot App.

#### log level available:
- ALL, DEBUG, INFO, WARN, ERROR, FATAL, OFF, TRACE
[Source 1](https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm)
[Source 2](https://stackoverflow.com/questions/7745885/log4j-logging-hierarchy-order)

#### required file need to be created and configured: 
- log4j2.xml or log4j2-spring.xml

it will print through Log4j2 (over SLF4J)

### Usage

```
$ mvn -am -pl app-lombok-log4j2 spring-boot:run
```

Logs folder will be created at the root. It contains the log file. The file name based on what's configured in log4j2.xml.

---

## 1. Spring Boot with Lombok Log4j2 Annotation.


### Reference Documentation
This project is about implementing Log4j2 using Lombok annotation in Spring Boot App.

#### log level available:
- ALL, DEBUG, INFO, WARN, ERROR, FATAL, OFF, TRACE
[Source 1](https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm)
[Source 2](https://stackoverflow.com/questions/7745885/log4j-logging-hierarchy-order)

#### required file need to be created and configured: 
- log4j2.xml or log4j2-spring.xml

it will print through Log4j2 (over SLF4J)

### Usage

```
$ mvn -am -pl app-lombok-log4j2 spring-boot:run
```

Logs folder will be created at the root. It contains the log file. The file name based on what's configured in log4j2.xml.

---

## 2. Spring Boot with MongoDB

This project is for testing Spring Boot with MongoDB.

### Usage

Bring up MongoDB and its client
```
$ docker-compose -f infra/sb-mongo/docker-compose.yml up -d

$ mvn -am -pl app-mongo spring-boot:run
```

mongo client now can be accessed at http://localhost:8081

---