# The Football Wall :soccer: <!-- omit in toc -->
The Football Wall is a full-stack project I made to learn or better understand some technologies: various of them are involved in this project:
* **Back-end:**
    * Java:
        * Spring Boot
        * MVC Pattern
        * Builder pattern
        * OpenAPI 3.0 and Swagger
        * Lombok
        * SLF4J and Logback
        * Kafka (**to be**)
    * Database:
        * _SQL_: Oracle (**to be**)
        * _NO SQL_: MongoDB
* **Front-end:**
    * HTML5
    * CSS3
        * Bootstrap
    * Javascript
        * Javascript Vanilla
        * ReactJS (**to be**)
        * Axios
  
<hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">

## Table of contents <!-- omit in toc -->
- [Project description](#project-description)
- [Back-end](#back-end)
  - [APIs](#apis)
  - [Logs](#logs)
    - [Example of log search](#example-of-log-search)
- [Front-end](#front-end)
  - [The FE structure](#the-fe-structure)

<hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgba(60,90,180,0.1);">

## Project description
**The Football Wall** was born from the desire to put together different technologies, in order to invole myself in a full-stack development that would allow me to learn as much as possible.  
I was looking for an idea to start the project, but my little immagination blocked me for a couple of days: I thought that an idea was absolutely necessary before start a work, because without a clear idea I would not have known where to end up.  
After many hours of thinking and after some Google searches I found a page that gave me the inspiration to start. This page is the EA Sports Fifa 21 rating database ([you can see it by clicking here](https://www.ea.com/en-gb/games/fifa/fifa-21/ratings/ratings-database)).  
So, starting from this page, I decided to build a web app that could show some data to the users, retrieving them from a database, using some rest APIs.  
The EA Sports pages give me the inspiration for the data to use, in fact The Football Wall will use the information about football players contained in the aforementioned site.  
All information are "manually" inserted in the DB of this web app and not retrieved directly from the EA Sports page.
Actually the software structure foresees the presence of a front-end that interfaces with the back-end through REST APIs.  
The back-end exposes some functionality that communicate with MongoDB to make the CRUD operations and to do this are used the Mongo connectors.  
In a near future I would like to include Kafka, <u>for purely educational purposes</u>, to try the communication between two microservices and the communication from front-end to DB will be done passing by two microservices in which only one of them has directly connection to Mongo.  
<p align="center">
  <img alt="schemas_as_is_to_be" src="./img/diagrammi_progetto.png" width="450" title="As Is-To Be">
</p>
As you can see in the schemas above, in the TO BE schema the only purpose of MS2 is to communicate with Mongo in writing mode and MS1 communicate with MS2 via Kafka and in reading mode with DB.  
This could appear as a non-sense schema, but as I said before, Kafka will be used only for learning and this connection appears as a good way to make some tests.

## Back-end
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

The logs will be stored for one week and over this time will be automatically deleted.

#### Example of log search  
To search on today logs:
```Shell
clear; grep <string_to_retrieve> ./tfw-BE.log
```
To search on archived logs (change the date to get the right logs):
```Shell
clear; zgrep <string_to_retrieve> ./*2022-02-17*.gz 
```  

<hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgba(60,90,180,0.1);">

## Front-end
The front-end was developed using the "old school" tools: HTML5, CSS3 and JavaScript Vanilla.  
Why I used JavaScript vanilla? Because I didn't know well this programming languagge: I've use it often but with but with a superficial knowledge of it. So I decided to build this project starting from primordial style of coding. It wouldn't have made sense to use more modern frameworks like Angular or React without a good knowledge of JavaScript.  

### The FE structure
The front-end has a structure that allows the user to read or insert data about football players and it's build by using some REST calls that retrieve data from MongoDB and shows them in the browser.  
The home page contains a paginated table with the ranking of the players, ordered by descending total score.
<p align="center">
  <img alt="homepage" src="./img/homepage.png" width="450" title="homepage" style="border: 1px solid #555">
</p>
Each page of the table shows a maximum of 10 players. By clicking on the page number buttons, another page will be showed and by an API call the page will be builded.  
To get a non-paginated table, the user can click on the "Full ranking" button, on the lower right corner of the table: the click on this button will redirect the user to another page in which a table will be builded dynamically retrieving data from db.  
<p align="center">
  <img alt="rankingpage" src="./img/rankingpage.png" width="450" title="rankingpage" style="border: 1px solid #555">
</p>
This table has the possibility to get a custom order that the user can choose. This ordering is implemented in a static way: the table is build by a REST API call and once it was created the data inside the table rows can be manipulated with an alghorithm (<a href="https://www.w3schools.com/howto/howto_js_sort_table.asp" target="blank">click here</a> to see the algorithm used) that allows the user to choose the order of table.  


<p align="center">
  <img alt="playerpage" src="./img/playerpage.png" width="450" title="playerpage" style="border: 1px solid #555">
</p>
<p align="center">
  <img alt="toptenpage" src="./img/toptenpage.png" width="450" title="toptenpage" style="border: 1px solid #555">
</p>
<p align="center">
  <img alt="playeraddpage" src="./img/playeraddpage.png" width="450" title="playeraddpage" style="border: 1px solid #555">
</p>