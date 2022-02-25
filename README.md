# The Football Wall :soccer:
The Football Wall is a full-stack project I made to learn or better understand some technologies: various of them are involved in this project:
* **Back-end:**
    * Java:
        * Spring Boot
        * MVC Pattern
        * Builder pattern
        * OpenAPI 3.0 and Swagger
        * Lombok
        * SLF4J and Logback
    * Database:
        * _SQL_: Oracle (?)
        * _NO SQL_: MongoDB
* **Front-end:**
    * HTML5
    * CSS3
        * Bootstrap
    * Javascript
        * ReactJS  -> DA TOGLIERE
        * Axios

- - - -

### Project description
**The Football Wall** was born from the desire to put together different technologies, in order to invole myself in a full-stack development that would allow me to learn as much as possible.  
I was looking for an idea to start the project, but my little immagination blocked me for a couple of days: I thought that an idea was absolutely necessary before start a work, because without a clear idea I would not have known where to end up.  
After many hours of thinking and after some Google searches I found a page that gave me the inspiration to start. This page is the EA Sports Fifa 21 rating database ([you can see it by clicking here](https://www.ea.com/en-gb/games/fifa/fifa-21/ratings/ratings-database)).  
So, starting from this page, I decided to build a web app that could show some data to the users, retrieving them from a database, using some rest APIs.  
The EA Sports pages give me the inspiration for the data to use, in fact The Football Wall will use the information about football players contained in the aforementioned site.  
All information are "manually" inserted in the DB of this web app and not retrieved directly from the EA Sports page.
Actually the software structure foresees the presence of a front-end that interfaces with the back-end through REST APIs.  
The back-end exposes some functionality that communicate with MongoDB to make the CRUD operations and to do this are used the Mongo connectors.  
In a near future I would like to include Kafka, <u>for purely educational purposes</u>, to try the communication between two microservices and the communication from front-end to DB will be done passing by two microservices in which only one of them has directly connection to Mongo.  
![alt schema](./img/diagrammi_progetto.png)  
As you can see in the schemas above, in the TO BE schema the only purpose of MS2 is to communicate with Mongo in writing mode and MS1 communicate with MS2 via Kafka and in reading mode with DB.  
This could appear as a non-sense schema, but as I said before, Kafka will be used only for learning and this connection appears as a good way to make some tests.

### APIs
As already mentioned in the previous paragraph, the information relating to a player is kept within a MongoDB database and is accessed, entered or manipulated through REST APIs.  

Method  | API Path             | Description
------- | ---------------------| -------------------------
GET     | `/api/getPlayer/all` | Get a list of all football players and their skills
GET     | `/api/getPlayer`     | Get data about the spcified football player and his skills
POST    | `/api/addPlayer`     | Add new player



### Logs
The logs are sored under a folder called Logs in which you'll find a daily file (tfw-BE.log) and a series of compressed (gz) files.  
The logging policy store files for a max-size of 1MB.  
  
If you need to search from log, you can use the bash commands `grep` (for *.log files) and `zgrep` (for *.gz compressed files) to do it.  

#### Example of search  
To search on today logs:
```Shell
clear; grep <string_to_retrieve> ./tfw-BE.log
```
To search on archived logs (change the date to get the right logs):
```Shell
clear; zgrep <string_to_retrieve> ./*2022-02-17*.gz 
```