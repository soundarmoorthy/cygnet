# cygnet
[![CircleCI](https://circleci.com/gh/soundarmoorthy/cygnet.svg?style=svg)](https://circleci.com/gh/soundarmoorthy/cygnet)
## Introduction
A java search client that does NLP search using the Stanford CoreNLP engine.

This project is intended to support searching a data set using NLP. This uses Stanford university's core nlp library to achive this. The core nlp library supports annotating a given input text. This library uses it's capabilities to annotate the semantic information in a given search text and then uses the information to find out which part of the data set to search for information. 

This library is written to be focused on US Healthcare information, where we attempt to add annotations for Procedure and Diagnosis codes(ICD-* codes, CPT codes), National Provider Identifiers.

## Development
### Prerequisites

    1. Java 1.8 or later
    2. Maven 3
    3. Optional (IntelliJ IDEA).
    4. x64 Operating sytsem (CoreNLP kbd annotator will fail if memory allocated is less than 6000 MB)

### Compiling

    mvn compile

### Packaging

    mvn package

#### Running the application
* Windows

      set JAVA_HOME=<path to jdk directory without spaces>
      set MAVEN_OPTS="-Xmx8192m"
      mvn spring-boot:run
* Linux / Mac

      export JAVA_HOME=<path to jdk directory without spaces>
      export MAVEN_OPTS='-Xmx8192m' //Or anything greater than 6000
      mvn spring-boot:run

#### Viewing output

    Goto browser and run "http://localhost:9080"

### Running from IntelliJ IDEA
1. [Intellij IDEA](https://www.jetbrains.com/idea/download/#section=windows) -> Import Project -> Open pom.xml in root folder
2. The main class should be in.soundararajan.cygnet.CygnetBoot. Use this information to setup debugging. Refer [here](https://www.jetbrains.com/help/idea/run-debug-configuration-application.html) for more detailed information.
3. For more information on importing maven pom projects into Intellij refer [here](https://www.jetbrains.com/help/idea/maven-support.html)

### Continuous Integration
TBD

### Demo
TBD

## Defining Data Set

TBD

## Caveats
* Make sure that you are passing -Xmx8192m to command line. The kbd annotator initialization sequence throws OOM on account of inadquete heap space if not provided when running the application
* This application starts the application server in port 9080. If you wish to change it, in SearchHttpServer.java update the port variable to a desired value.
* Don't forget to set the JAVA_HOME and MAVEN_OPTS variable. They will lead to build errors and runtime errors
