This Ant script provided is incomplete.
Ant script tasks are as follows;

--- 'ant clean': Cleans the "build" directory of the project.  
--- 'ant compile': Compiles Java source files under demo/Core, demo/Database, and demo/Tests.  
--- 'ant test': Runs JUnit test cases built under build/Tests. Creates Jacoco coverage documentation under build.  
--- 'ant report': Compiles coverage documentation into a browser-viewable .html file under build/site/jacoco.  

This project is originally built in IntellIJ. IntellIJ project structure has been maintained for ease of development, but should not interfere with Ant automation.
However, despite extensive attempts and research to try and automate Tomcat deployment with Ant, I was not successful in implementing this function.
Currently, the task 'ant deploy' produces a contextless error of refused connection to the Tomcat server, though appears to successfully deploy the project to a .war file.

I tried. I really did. I am sure you were busy, but I needed help.

Given this, to run the project;

- Open the 'demo' directory as a project in IntellIJ,
- Ensure a tomcat plugin is installed (SmartTomcat),
- Deploy the project locally in IntellIJ
- Provided 'db' files (dbAccounts, tbTables) can be used as default information. These will have to be placed under Tomcat's running directory, otherwise are inaccessible by the program. This was yet another facet of programming that, despite extensive research and attempts to circumvent, I was not able to relocate. If these provided files are not placed under Tomcat's running directory, the database files will be automatically created, but will be empty- and as such you will be unable to log in to access program functionalities.

This is the best I can do. Its not good enough, its not up to class or industry standards, but I have nothing else left to try. I'm not smart or skilled enough, like everyone else.
