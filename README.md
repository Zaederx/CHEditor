# CHEditor
 Web Technology - CW3 - CHEditor
 # iVoteHub - Web Technology Course Work 2
## [See Youtub Video Walkthrough](https://youtu.be/CC4o43OOnWE)

<img width="1440" alt="image" src="https://user-images.githubusercontent.com/38586415/130472286-bc390e79-5221-4991-8b40-62f320b188d5.png">
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/38586415/130472519-8e5e9d6b-27e0-4984-be43-d6c85e7cc3e9.png">


## Tables of Contents
* [Background](#background)
* [Technologies](#technologies)
* [Requirements to run the project](#requirements-to-run-the-project)
* [Setup](#setup)
* [Running the project](#running-the-project)
* [Project Shortcommings](#shortcommings)
* [Additional Notes](#additional-notes)
* [Images](#images)

## Background
This was for a university web technology assignment. We were asked to create a 'Class Hierarchy Editor'(CHEditor). The idea is to allow you to visualise class hierachy structures. 


## Technologies
- Java 
- Gradle
- Spring Boot + Spring Security
- Hibernate
- MySQL (orignially - now using H2Database for portability)
- Thymeleaf

## Requirements to run the project
You will need to have java 11 or higher installed and on your classpath in order to build and run this gradle project.

Optionally you can use a local installation of gradle 6.5.1 to build and run this, but there is a gradle wrapper provided for portability [see Running The Project](#running-the-project).


## Setup
The project was originally designed to work with MySQL which reqiured MySQL server setup and configuration but has been changed to use H2Database insteac for greater portability. 

Now you can simply pull the repository and the application using the gradle wrapper.


## Running the Project
From the terminal, cd the project directory. Once inside the directory: 

On Mac and Linux
```
./gradlew bootRun
```

On Windows
```
./gradlew.bat bootRun
```

Once the boot process is complete, you can then view the website from https://localhost:8090/home (if you are using default configurations). 
Note: Occassionaly these project may not work with you local version of gradle, so it's important to use the wrapper.


## Project Shortcommings - (8TH July 2021 Update - Now uses AJAX)
I created this before I learnt learn about separating the JS from HTML into their own files, so all js is written in script tags at the end of the HTML files. Were I to make this project now, I'd have them in separate files.

## Additional Notes
The project website uses a self-signed certificate so may not work on Safari, in which case I recommend opening it in FireFox or Google Chrome which which after displaying a warning allows you the option to proceed to open the page.



