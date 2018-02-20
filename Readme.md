#Cashpool
######Invia Code Challenge

Cashpool allows you to enjoy your trip in peace without having to constantly fret about who pays for what. Just enter your expenses and sit back as Cashpool makes all the calculations for you and splits the expenses amongst the group.


####Build

To build the application, you need maven installed or use the [maven wrapper](https://github.com/takari/maven-wrapper). Once installed, open command line in the root folder of the application which has the pom.xml file and run

`mvn clean package`

This will run the entire build process including 
- Compilation
- Static Code Analysis (Checkstyle)
- Units tests
- Integration tests
- Code coverage reporting tool


####Running the application

Once build is successful, you can start the application by either running the jar from the `target` folder. Since this application uses Jasypt for encryption, make sure you have the following environment variable configured before you run

    java -Djasypt.encryptor.password=vFeSEwTEW -jar cashpool-application-*.jar
    
Or, by using the spring boot maven plugin
    
    mvn spring-boot:run -Djasypt.encryptor.password=vFeSEwTEW
    

#Stack
 - A Spring MVC application using Spring boot 
 - Spring Data, JPA with Hibernate, H2DB
 - Templates with Freemarker
 - Frontend UI using Jquery/Bootstrap

#Features

####UX
- Jquery/Bootstrap Spinner
- A custom URL shortcode generator that generates progressively larger shortcodes as number of trip records increase.
- Google map static images based on location

####Code Quality
- Static code analysis using Checkstyle and findbugs
- Code coverage using cobertura
- Unit tests using Junit 

####Security
- Using Jasypt to enable encryptable properties

####Improvements
- Add a EntityDto mapping library to reduce the boilerplate code

####Contact


To view the code coverage report, open Intellij and browse to Analyze > Show Coverage Data and import the `target\jacoco.exec` file. You should be able to see the class. method and line level metrics.
For any queries, contact tapan.nallan@jda.com