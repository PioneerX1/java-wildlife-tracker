## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

#### By _**Mick Sexton**_

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar, type varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, occurrence timestamp);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

* _Download or Clone java-wildlife-tracker folder_
* _Create database as specified above_
* _Type gradle run in your command line_
* _Open a browser and go to localhost:4567_

## Code Specs

|Behavior - Plain English|Input|Output|
|---|---|---|
|Ranger sees a home page where they can report non-endangered animal sightings, endangered animal sightings, they can a new animal to the system, or view one of the animals' sightings and info in the endangered or non-endangered lists below.|Ranger selects an animal from the list below.|Ranger is directed to page with the animal's details like health and age, as well as a list of previous sightings including their locations, who reported the sighting, and the time and date of the occurrence.|
|Ranger is on home page again.|Ranger selects a non-endangered animal from the select box to report sighting, fills in location and his/her name, and clicks the submit report button.|Ranger is directed to a success page letting them know their sighting has been saved.|
|Ranger is on home page again.|Ranger selects an endangered animal from the select box to report sighting, fills in location and his/her name, and clicks the submit report button.|Ranger is directed to a success page letting them know their sighting has been saved.|
|Ranger is on home page again.|Ranger selects an endangered or non-endangered animal from the select box to report sighting, leaves either or both of the location and name fields black, and clicks the submit report button.|Ranger is directed to an error page alerting them that they did not complete the form and will need to hit back on their browser or the home page button to try again.|
|Ranger is on home page again.|Ranger clicks up top to add a new Animal to the system.|Ranger is directed to a new animal form page.|
|Ranger is on new animal form page.|Ranger types in species of animal, can select whether or not the animal is endangered or not with a checkbox, and select from 2 select boxes to determine approximate health and age, then clicks button to add the animal to the system.|Ranger is directed back to home page with their new animal showing up on either the endangered or non-endangered list below.|
|Ranger is on new animal form page.|Ranger neglects to type in species of animal, can select whether or not the animal is endangered or not with a checkbox, and select from 2 select boxes to determine approximate health and age, then clicks button to add the animal to the system.|Ranger is directed to an error page alerting them that they have an empty field, and can hit home button or back button on browser to try again.|

## Recent Changes Made To Codebase
_Initially, I noticed that creating a sighting for one animal would sometimes automatically create the same sighting for another animal. This was because the database was setup with 2 tables for animals (animals and endangered animals), and therefore had recurring animal id's populating into the sighting's table. I restructured the 2 animal tables into 1 table to practice single table inheritance and added a type (non-endangered or endangered). I kept the health and age attributes for both types since the application asked for those even on the non-endangered animals to begin with. I changed the Animal class to an abstract parent class, had the EndangeredAnimal class extend from it, and did the same for a newly created NonEndangeredAnimal class._
_I added a Timestamp attribute called occurrence to the Sighting class as well as a getOccurrence method that takes the timestamp, Date Formats it to clear way the seconds and leave only the date & time down to minutes._
_I threw Exceptions for incomplete forms and added Try and Catch handlers in App.java to have them redirect to an error page if the user forgot an importance piece of info._
_I noticed one of the tests had a false positive and was passing when it shouldn't have been. This was because the Override Equals method (previously in Animal class) did not compare the id's, only the other attributes. I fixed this and moved this method to both Endangered and NonEndangered Animal classes._
_I refactored a part of the App.java file to use less lines of code in one of the POST routes._
_I reformatted some of the vtl templates so the information was more clear to the ranger. I got rid of id's on the front end (since that is of no use to them), and listed the individual sightings in easier-to-read thumbnail classes._ 

## Known Bugs

_No known issues or bugs at this time_

## Support and contact details

_Please contact Mick Sexton at lacrookedbeat@hotmail.com for any questions, feedback, or concerns._

## Technologies Used

_Technologies used include Java, Atom, Git, Gradle, GitHub, Spark, CSS, J-Unit, Postgres, SQL database, and Velocity Template Engine_

### License

*This software operates under the MIT license.*

Copyright (c) 2017 **_Mick Sexton_**
