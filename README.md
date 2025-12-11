# CSCI 472 - Resturaunt Reservation System

This project is a simple web application designed as a table reservation system for an imaginary, unnamed restaurant.

## Description

The application is hosted locally to emulate online availability, and has features for login accounts, admin table / reservation / account management, and reservation creation for any signed-in account. The application features CRUD functionality and data constraints, restricting reservation dates and times, account login information, table enumeration, etc.

It is implemented by JSP & Java servlet technology, designed with the Model-View-Controller architecture, and deployed locally with Apache Tomcat. Build automation is handled by Apache Ant, and project is initially built in IntellIJ IDEA. Information is stored locally- persistently- on the host machine, written directly to .csv files, thus having no reliance on database technologies.

## Getting Started

### Dependencies

Apache Ant ver. 1.10.+  
Apache Tomcat ver. 10.0.+  
Both $CATALINA_HOME / $ANT_HOME environment variables set  

IntelliJ IDEA

### Installing

* 
    Open the 'demo' directory as a project in IntellIJ,
    Ensure a Tomcat plugin is installed (SmartTomcat),
    Provided 'db' files (dbAccounts, tbTables) can be used as default information. These will have to be placed under Tomcat's running directory, otherwise are inaccessible by the program.

### Executing program

* Deploy the project locally in IntellIJ
* Open browser and go to url: 'https://localhost:8080/demo'

Ant 'build.xml' tasks are as follows;
```
ant clean : Cleans the "build" directory of the project.
ant compile': Compiles Java source files under demo/Core, demo/Database, and demo/Tests.
ant test : Runs JUnit test cases built under build/Tests. Creates Jacoco coverage documentation under build.
ant report : Compiles coverage documentation into a browser-viewable .html file under build/site/jacoco.
```

The following commands are remnants from attempts to deploy the project via Ant. However, while the project's HTML content is successfully deployed to Tomcat, all Java backend code fails.
If you still wish to run these commands, they are as follows;
```
ant start : Wait for it to complete (a CMD window should appear; Tomcat starting)
ant deploy : Will deploy the project to Tomcat
```
- Open browser and navigate to url: https://localhost:8080/demo

## Authors

Tristen Achterberg
[@tristen-A](https://github.com/tristen-A)

## Version History

* See [releases](https://github.com/tristen-A/CSCI_472/releases)
