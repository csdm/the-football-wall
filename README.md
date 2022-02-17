# spring-react-the-football-wall
A full-stack project using Java Spring Boot and ReactJS

### Logs
The logs are sored under a folder called Logs in which you'll find a daily file (tfw.log) and a series of compressed (gz) files.  
The logging policy store files for a max-size of 10MB.

Example of search  
clear; zgrep <string_to_retrieve> ./*2022-02-17*.gz <path/*date*.gz> --> search on old logs
clear; grep <string_to_retrieve> ./tfw-BE.log <path/tfw-BE.log> --> search on today logs