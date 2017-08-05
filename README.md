# Web Crawler


## The Application
Java application to display top JS libraries used in sites. 
The application does the following
* Seeks the command line input from user for a search value
* Searches google with the given search value
* Spawns multiple crawlers and looks for JS libraries used
* Aggregates and displays top JS libraries used

## Search Components
GoogleSearch class is used to initiate a search to google, using google search API. The class is configured to query for only 10 results.  
SearchResults and SearchItem classes are used to map the results returned from Google.

## JS libraries Search
JSLibrarySearcher is used for searching for script tags and trying to identify the libraries used.

## Crawler
CrawlerTask connects to a url, and initiates the search
CrawlerManager initializes the threads and spawns off the CrawlerTasks. CrawlerManager is configured to spawn 10 simultaneous threads


## Dependencies and 3rd party libraries
mvn dependency:tree result  

 +- com.google.code.gson:gson:jar:2.8.1:compile  
 +- junit:junit:jar:4.12:test  
 \- org.hamcrest:hamcrest-library:jar:1.3:test  
    \- org.hamcrest:hamcrest-core:jar:1.3:test  

GSON library is used for parsing JSON results from google search APIs.  
Junit for unit test cases  

## Executing the application
com.gurupv.simple.command.CommandLineExecutor contains the main method to start the application.  
The entire project can be built, compiled and executed using Maven.  
Maven executor plugin is also added. If required, mvn exec:exec can be used to run the application using Maven. 
The entire code base can also be imported into eclipse as a maven project.

## Test cases
Test cases are added to test each individual components
* Tests for Google Search component
* Tests for JS library pattern search
* Test for Crawler Task

