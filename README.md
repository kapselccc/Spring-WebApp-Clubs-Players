# Web Application implemented in Spring
This is a simple web application that allows to add clubs and players. 

## How to run app
1. Go to the main folder of the project containing `docker-compose.yml` file
2. Open terminal
3. Run command below in the terminal
```
docker-compose up -d
```
4. Webapp is running on port 80 on localhost [`http://localhost:80`](http://localhost:80)
   
## Overview
### Architecture
The application is implemented in microservices architecture. Every microservice
runs on a seperate docker container. Containers are connected via private
network. Port 80 on localhost is a entrypoint to the view of the app.

### Backend
Backend consists of Club microservice, Player microservice and Gateway. All
three are implemented using Spring Framework. Player and Club microservices are
REST APIs that allow to add, delete and update players and clubs. The shared
entrypoint for HTTP requsts to backend is Gateway. Every request goes to the
gateway which then passes them to paricular microservice. 

### Frontend
Frontend is implemented in Node.js and consists of 8 webpages that allows to:
* see list of clubs
* add club
* edit club
* see list of players of a particular club
* add player to the club
* edit player