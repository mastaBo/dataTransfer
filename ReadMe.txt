Data Transfer Project based on
Camel Router Spring Project
===========================

To build this project use
    mvn install

To run this project with Maven use
    mvn camel:run

===========================
This project
* grabs the data from specific web-site using list.php API call with parameters
* transforms received list of items with XStream
*  writes the data to external mySQL Database

