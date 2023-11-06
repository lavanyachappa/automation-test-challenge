#### Automation Setup
This repository is to setup test automation framework for running automated tests  for frontend and backend applications using Selenium + TestNg + Rest Assured

##### Assumptions
* UI tests are tested on Chrome latest version 119.0.6045.105 because chromedriver used for this project supports this chrome version only

##### Pre-requisites

* Install java and maven

##### TechStack
* Java 11
* Selenium
* TestNG
* RestAssured
* Maven  3.9.5
* Extent Reports    
 
##### Framework Details

1. Hybrid Framework
2. Leveraged POM framework for selenium
3. Java Factory Design Pattern for loose couple, reusuability and robust
4. TestNG data provider for Test Data Driven
5. RestAssured For API tests
6. Extent Report For Reporting

##### Package information
###### src/main/java
1. org.assessment.demo.common  - Classes to access the utilities and properties by the tests
2. org.assessment.demo.restassured - For executing APIs using rest assured jar
3. org.assessment.demo.seleniumframework.core - For Browser Test Support using Selenium
4. org.assessment.demo.utils - Configuraiton.Reporting,Generic Utilities files

######  src/test/java
1. org.assessment.demo.common - Test and Suite Listeners and TestNG Test data Provider and API,UI Base Test Classes
2. org.assessment.demo.library - Actions on Page Objects
3. org.assessment.demo.module - Business Logic is handled by using Library package for reusuabaility and robust.
4. org.assessment.demo.apitests - Contains API automated tests
5. org.assessment.demo.browsertests - Contains Browser automated tests

##### Application
* [Contacts Application](https://thinking-tester-contact-list.herokuapp.com/)
* Login information can be found under config/common.properties 

##### Automated Test Scenarios
###### Browser Tests
* Login to Contacts Application - [Contacts Application](https://thinking-tester-contact-list.herokuapp.com/) and verify Contacts Page Displayed
* Create a new contact and verify it

###### API Tests
* Login to Contacts Application using POST - https://thinking-tester-contact-list.herokuapp.com/users/login
* Read Contacts using GET - https://thinking-tester-contact-list.herokuapp.com/contacts
* Create Contacts using POST - https://thinking-tester-contact-list.herokuapp.com/contacts


##### Steps to Execute Tests from command line**

 1. From command line, clone the repo
 2. Run "mvn test"
 3. TestNG suite xml automation-test-challenge/testsuites/environment/test is executed
 4. Extent Report is generated in path rootdir/extent-reports/extent-report.html.

##### CI/CD Integration

* This repo is designed with interoperability to integrate with  Github/BitBucket/Gitlab or jenkins pipeline to run tests in CI/CD pipeline

##### Outcomes
1. Create a robust Framework to support UI and API tests
2. Reusuability and fast automation Development for E2E usecases
3. Loosely coupled with less Maintenance overhead

