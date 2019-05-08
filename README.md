[![Build Status](https://travis-ci.org/pradeepk6/identitye2e.svg?branch=master)](https://travis-ci.org/pradeepk6/identitye2e)

About:

Showcase of design,code,document,project-structure,build and test using Java, Cucumber and selenium-webdriver

built with:

    Java 8
    cucumber    
    Selenium WebDriver 
    Gradle

highlights:


how to run:

    need Java8 and Gradle to run.
    test task runs unit tests
    a task named 'acceptanceTest' has been created under group verification which runs end to end tests
    download suitable version of chrome driver into <projecthome>/drivers directory 
    browser and driver proerties are set in the task 'acceptanceTest' build.gradle    
    the code to add and delete test data is in the task 'acceptanceTest' in build.gradle file 
    reports are generated into build/cucumber-reports

tested on:

    windows 10 and chrome driver
    
    
