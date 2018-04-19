# cygnet
## Introduction
A java search client that does NLP search using the Stanford CoreNLP engine.

This project is intended to support searching a data set using NLP. This uses Stanford university's core nlp library to achive this. The core nlp library supports annotating a given input text. This library uses it's capabilities to annotate the semantic information in a given search text and then uses the information to find out which part of the data set to search for information. 

This library is written to be focused on US Healthcare information, where we attempt to add annotations for Procedure and Diagnosis codes(ICD-* codes, CPT codes), National Provider Identifiers.


## Development
### Prerequisites
1. Java 1.8 or later
2. Maven 3
3. Optional (IntelliJ IDEA).

### Running from command line 
TBD

### Running from IntelliJ IDEA
1. Intellij IDEA -> Import Project -> Open pom.xml in root folder
2. Create a configuration for 'Application' and point to main class. The main class should be in.soundararajan.cygnet.SearchHttpServer. If this doesn't work lookup the variable ${this.group.id} defined [here](https://github.com/soundarmoorthy/cygnet/blob/master/pom.xml).

### Continuous Integration
TBD

### Demo
TBD

## Defining Data Set

TBD

## Caveats
* Make sure that you are passing -Xmx8192m to command line. The kbd annotator initialization sequence throws OOM on account of inadquete heap space if not provided when running the application
* This application starts the application server in port 9080. If you wish to change it, in SearchHttpServer.java update the port variable to a desired value.
