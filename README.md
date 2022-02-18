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
        * ReactJS
            * Axios

- - - -

### Project description

### APIs

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