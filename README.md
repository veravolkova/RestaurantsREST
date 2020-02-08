## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
REST API endpoint that allows searching nearby restaurants (closer than 3km) by name, descriptions and tags. 

Example query:
/restaurants/search?longitude=24.941325&latitude=60.1699388&q=risotto

Example Postman queries:

http://localhost:8080/api/restaurants
http://localhost:8080/api/restaurants/search/findByNameOrDescriptionOrTagsNameNearby?longitude=24.941325&latitude=60.1699388&q=risotto
	
## Technologies
* Java, Spring
* H2 database
* REST service
* Testing
	
## Setup
To run this project (Eclipse IDE example):

```
1. Import as an existing Maven project

2. Run RestaurantsApplication file (src/main/java --> com.example.restaurants)

3. Tests files are run as jUnit tests 
  (Eclipse IDE: right click --> run as jUnit Test)

```

Access to database:

```
http://localhost:8080/api/h2-console
Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:testdb
User Name: sa
password: blank
```
