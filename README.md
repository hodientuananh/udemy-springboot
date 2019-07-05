# How to deploy to Google App Engine

## Setup Standard Environment
### Exclude tomcat web server
For using the standard environment of google app engine, firstly is disable the tomcat servlet embedded in spring boot project since google
app engine use Jetty as web server.
The following change for pom.xml
```xml
...
<packaging>war</packaging>
...
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
      <exclusion>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
      </exclusion>
    </exclusions>
  </dependency>
 
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <scope>provided</scope>
  </dependency>
 
  <!-- Exclude any jul-to-slf4j -->
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jul-to-slf4j</artifactId>
    <scope>provided</scope>
  </dependency>
..
```
### Add app engine configuration
Add `appengine-web.xml` to directory `src/main/webapp/WEB-INF`
```xml
<appengine-web-app xmlns="http://appengine.google.com/ns/1.0">
  <threadsafe>true</threadsafe>
  <runtime>java8</runtime>
  <system-properties>
    <property name="java.util.logging.config.file" value="WEB-INF/classes/logging.properties"/>
  </system-properties>
</appengine-web-app>
```
### Add app engine maven plugin for requirement
Add to `pom.xml` following code
```xml
<build>
  <plugins>
    ...
    <plugin>
      <groupId>com.google.cloud.tools</groupId>
      <artifactId>appengine-maven-plugin</artifactId>
      <version>1.3.2</version>
      <configuration>
        <version>1</version>
      </configuration>
    </plugin>
  </plugins>
</build>
```
### Add `SpringBootServletInitializer` implementation
```java
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyGoogleAppEnginePlanetApplication.class);
    }
}
```
### Pull source code from release branch
In the App engine of desired project, choose google console, then pull the source code from github
```git
git clone https://github.com/benkinmat/udemy-springboot.git
```
Checkout the release branch
```git
git checkout release/google_1.0.0
```
If your code have not exist this branch, you must pull the new source code
```git
git pull
```
### Deploy the app engine
To make it real, use command
```
mvn -DskipTests appengine:deploy
```
### Access live web
Use the following web for access service
http://<project-name>.appspot.com
example: http://world-contribution.appspot.com
