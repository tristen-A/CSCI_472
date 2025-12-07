Though it is self explanatory, ensure that the $CATALINA_HOME and $ANT_HOME environment variables are set.

Ant script tasks are as follows;

--- 'ant clean': Cleans the "build" directory of the project.  
--- 'ant compile': Compiles Java source files under demo/Core, demo/Database, and demo/Tests.  
--- 'ant test': Runs JUnit test cases built under build/Tests. Creates Jacoco coverage documentation under build.  
--- 'ant report': Compiles coverage documentation into a browser-viewable .html file under build/site/jacoco.  

This project is originally built in IntellIJ. IntellIJ project structure has been maintained for ease of development, but should not interfere with Ant automation.
However, despite extensive attempts and research to try and automate Tomcat deployment with Ant, I was not successful in implementing this function.
Currently, the task 'ant deploy' has- as of twenty minutes to the deadline- gotten the application to deploy its HTML content. However, its Java classes- servlets and otherwise- fail to run. This can be achieved as such;
- Run 'ant start', and wait for it to complete (a CMD window should appear; Tomcat starting)
- Run 'ant deploy'
- Open browser and go to url: 'https://localhost:8080/demo'

I really did try, but this is as far as I could get without help.

Given this, to properly run the project;

- Open the 'demo' directory as a project in IntellIJ,
- Ensure a tomcat plugin is installed (SmartTomcat),
- Deploy the project locally in IntellIJ
- Provided 'db' files (dbAccounts, tbTables) can be used as default information. These will have to be placed under Tomcat's running directory, otherwise are inaccessible by the program. This was yet another facet of programming that, despite extensive research and attempts to circumvent, I was not able to relocate. If these provided files are not placed under Tomcat's running directory, the database files will be automatically created, but will be empty- and as such you will be unable to log in to access program functionalities.

This is the best I can do. Its not good enough, its not up to class or industry standards, but I have nothing else left to try.
