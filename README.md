# How to deploy to Heroku Server

## Setup Standard Environment
### Get source code
Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).
```sh
$ git clone https://github.com/benkinmat/udemy-springboot.git
$ cd udemy-springboot
$ mvn install
$ heroku local:start
```
Your app should now be running on [localhost:5000](http://localhost:5000/).
### Config default port for tomcat
In order to binding port from heroku server to default configuration port, we set up default port to application.properties
The following change for pom.xml
```xml
server.port=${PORT:5000}
```
### Config Procfile for finding target jar file
Add following in Procfile
```file
web: java $JAVA_OPTS -jar target/*.jar --p $PORT
```
### Specific java 1.8 runtime
Add following in system.properties
```file
java.runtime.version=1.8
```
### Deploy to heroku
#### If no application
```sh
$ heroku create
$ git push heroku master
$ heroku open
```
#### If have exists application
```sh
$ heroku git:remote -a app-name
$ git push heroku master
$ heroku open
```
### Access live web
Use the following web for access service
https://<app-name>.herokuapp.com
example: https://world-contribution.herokuapp.com/
