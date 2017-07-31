# Build tool


### Step - 1. Installed and Environment variables

* if you do not have Java installed, install the [Java Software Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (SDK)

* download and setup [apache-maven](https://maven.apache.org/download.cgi)

* download and setup [tomcat](http://tomcat.apache.org/)

* add path in environment variables

* open console and input command `mvn -version`. Should be output something:

```java
Apache Maven 3.3.3 (7994120775791599e205a5524ec3e0dfe41d4a06; 2015-04-22T17:27:37+05:30)
Maven home: C:\Program Files\Apache Software Foundation\apache-maven-3.3.3
Java version: 1.7.0_75, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.7.0_75\jre
Default locale: en_US, platform encoding: Cp1252
```
		
You are now all set to use Apache Maven for your projects.


### Step - 2. Add configs files

* copy-past settings from file "tomcat-users.txt" in your `%tomcat-home%\conf\tomcat-users.xml` 

* copy-past settings from file "server.txt" in your `%tomcat-home%\conf\server.xml`

* copy-past settings from file "maven_settings.txt" in your `%apache-maven-3.X.X%\conf\settings.xml`


### Step - 3. Command line 

* open console in `%tomcat-home%\bin\` and input command `startup.bat` or `catalina.bat run`

* open console in `\workspace\%name_project%\` and input command `mvn tomcat7:deploy`.

  Should be output **BUILD SUCCESS**.
	
	
#### `BASE COMMAND IN CONSOLE:`
* stopped server
		 
	`shutdown.bat` or shortcut "Ctrl+C"

* checking active profiles created by a pom.xml
		 
	`mvn help:active-profiles`

* deploy by profile
		 
	`mvn tomcat7:deploy -PMentor`
		
* delete all artefacts in the 'target' directory"

	`mvn clean`
		
* delete and create new target directory with new war 

	`mvn clean install`
		

