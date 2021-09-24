Decathlon site testing
=====================

Selenium Java testing Decathlon site, using Java, Selenium, POM, TestNG, Cucumber,
Singleton, dependency injection and extended reports.

Website: https://www.decathlon.in/

Note: Test results may vary as the website tends to change a lot.

Author: Srinivas Prasad K T
Date: 09/24/21

Last updated: 09/24/2021

Testing notes
------------
* Code was implemented using Intellij IDEA.
* Programming Language: Java (Java 8 is being used.)
* Selenium is being used for automation actions.
* Page Object Model and Page Factory has been using as part of Selenium approach.
* Chosen web browser: Chrome (chromedriver.exe used to communicate with Selenium.)
* Created using a BDD approach in order to better understanding of all parties included. Achieved using Cucumber.
* To manage dependencies, build and run it MAVEN project was implemented.
* TestNG was implemented in order to run the BDD approach.
* Cucumber reports was used for reporting purposes.
* Page Object Model is being used to separate locators per page and code re utilization.



How to install and run the project (Windows)
-------------------------------------------
1. Download Java
    a. C:\Program Files\Java\jdk1.8.0_181
    b. Edit Windows environment variables, JAVA_HOME: C:\Program Files\Java\jdk1.8.0_181
    c. Edit Windows environment variables, Path: C:\Program Files\Java\jdk1.8.0_181\bin
    d. Check JRE: java -version in CMD
    e. Check JDK: javac -version in CMD
2. Download MAVEN
    a. C:\Libs\apache-maven-3.5.4
    b. Edit Windows environment variables, Maven document states add M2_HOME only, but some programs still reference Maven folder with
       MAVEN_HOME, so, it’s safer to add both: C:\Libs\apache-maven-3.5.4
    c. Edit Windows environment variables, Path: C:\Libs\apache-maven-3.5.4\bin
    d. Check maven: mvn -version in CMD
    
How to run
----------
1. Windows: Double click in file called 'executable.bat' located in root of the project

2. MAC: open terminal y run 'mvn clean verify'

3. Reports - There are two kind of reports
    A. Cucumber: Check folder 'target\cucumber-report-html\cucumber-reports' and open
        the file 'feature-overview.html' in order to see the results of the test. Note: In order to see
        this report mvn verify has to be executed as it is part of phase in maven file, if you run the
        app from testNG or testRunner file you won't see it.
    B. Extent: Check folder report in order to see Extent report as alternative for cucumber report.

Updates
-------
v1.2:
- Adding support for Java 11 (for var support)
- Updating Selenium to the latest version 3.141.59
- Adding support for testNG.xml to run from testng and not directly from testRunner. The idea is to have listeners
  and pretty cool stuff added.

v1.3
- Adding test listeners and class ngTestListener.java

v1.4
- Updating pom.xml with dependencies for allure and image comparison needed for utils.java
- Note: in order to ru.yandex.qatools.ashot to work and allow webdriver to open you need to
  explicitly add selenium webdriver remote dependency
  
v1.5
- fixes to run it on MAC OS

