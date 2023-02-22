# Java-Mars-CHALLENGE
Tech Returner Mars Rover Challenge
What are required:
==================
A program is required to move rovers around the surface of Mars over a plateau which will be a square or rectangular grid in this task. Rovers should navigate the Plateau so they can use their special cameras and robot arms  to collect samples back to Planet Earth.
The Plateau is divided into a grid. A Rover’s position is represented by x and y co-ordinates and the letters N, S, W, E to represent North, South, West, East respectively. To move a rover, a string of letters is sent to the Rover. A ‘L’ letter will spin the Rover 90 degree left; ‘R’ letter will spin the Rover right, and ‘M’ letter moves the Rover forward by one grid.

How I have designed the solution 
================================
** The following included additional features after publishing the Repo on 14Feb and attending Mars Rover seminar **
For carrying this Rover mission, I designed a Console controller, that will take the input from the Users:
1.	To define the range of a rectangular plateau for surfing. The command can also indicate if an special material is known in the plateau, which required to be searched by the rover
2.	User can select to mobilize multiple (more than 2) Rovers, including different types of Rovers for operation, with input of the coordinate and the facing directions of each on the plateau. Currently there are two types of Rovers for choices - 1) Mars Rover 2) BumbleBee 
3.	User to input the navigation commands of mobilized Rovers. Each rover will move based on input instruction. Each will detect if the plateau boundary is reached, or if each other rover is on the path, and accordingly stop at the last grid point. The position of each rover will be reported to the User.
4.	Each rover can probe for the special material, and if identified, alert is reported to console, and the mission will stop (moving).

What I developed for this repo:
=============================
I started design with an initial UML, and developed astract class for Rover, Plateau interface and console UI in the first version of Repo. 
After attending Mars Rover Coding seminar and reviewing the SOLID principles against my code, I carried out refactoring and broken down classes into smaller classes. The new version of the programs comprise the following:

a) Individual class for each command - Right, Left and Move were created based on a common ICommand Interface. These command classes are decoupled from the console and Rover classes, and can be requested as objects for each instruction to rovers. This uses the Command design Pattern. Each command can be identified and made easy for future maintenance. new command can be easily added. 

b) A SpaceVehicle Astract class was developed. A Mars Rover class extends from the Space Vehicle to inherit the attributes (Rover's position, direction) and method (navigation). This allow other types of Space vechicles to be deployed easily, by extending from the Space Vehicle. For initial testing, I develooed a 'BumbleBee' transformer vehicle. It inherits attributes and methods same as Mars Rover. It can add its own method in future e.g. to clear a obstacle/hazard on the plateau.

c) The Space Vehicle class navigation method has implemented Open/Close SOLID principle, to allow any future commands to be added without re-opening this navigation code.

d) Similarly, a Plateau interface was developed and a RectanglePlateau demonstrates one implementation of the Plateau. The Plateau interface defines methods of set-in-use(), clearUse() and isSafeToMove() which must be implemented by any form of plateau.

e) The codes have been refactored into smaller classes and grouped into different folders (e.g. Commands folders, Vehicle folder, Plateau folder) in order that they future maintenance and development to scale up system, can be easily performed. Separate Enum files were developed to faciliate future addition/modification. 

Throughout the development, I have used the TDD approach and created the Rovertest class, which include specific test cases and also parameterized test cases. Two CSV files are created for the test cases of moving one rover and moving two rovers respectively. 

How to Run the Program:
======================
The program can be run using the RoverTest class, as well as by using Console class.

** Using RoverTest Class:
Just Run the MarsRoverTest class, and all the test cases with specific test data and test data from CSVs will pass. The test cases include:
-	Unit test of each method for one rover and two rover, to process string of L,R,M movement command
-	Move a rover around the full plateau boundary with returning to start point
-	Testing two rovers in operation such that each rover will stop if the other rover is in immediate front of the next step.
-	Testing probing Target such that rover report message to the caller.
The above test cases are identified by description in the Test Case

** Using Console User Interface:
Run the Console.main() program. The Console will ask for user input as follows:
-	1) Input the plateau range using the top-right rectangle/square coordinate. For example, input “5 5”. The console will allow defining maximum size of “100 100”. 
-	The Commander can also input with a ‘P’ appended to the coordinate, to indicate this plateau has special material e.g. “5 5 P”. In this case, the program will generate a Random location for which the rovers need to search to accomplish mission.
-	2) The console will ask user to select a type of Rover and input its name for the operation. For example: "1 Explorer" to select a type 1 rover with name "Explorer". Users can select more than one rovers to get ready... Enter a 'X' if no further rover is required.
- 3) For each rover mobilized, console will ask user to input the landing position e.g. input “0 1 N” to land at (0,1) coordinate with Rover facing North. When user has input the information, the Rover will be 'created' and land on the plateau.
-	4) The console then asks for movement instruction  for each Rover i.e. input any letter string using L,R,M. e.g. “RMMMLMMRM” The rover will move accordingly and report the position reached (if a plateau boundary is reached, or another rover is in front, the rover will stop at its current location).
-	5) The console then asks for the movement instruction for each other Rover, until all Rovers are instructed and have moved.  
-	6) If no rover cannot find the special material after the instruction, the console will repeat to ask for next command instructions for each rover.
-	7) The user can repeat sending commands to all rovers, until the Special material is found (A red alarm message will pop up), or User enter an 'X'to exit.

Thoughts for further work:
=========================
1.Different shapes of plateau can be implemented, such as Triangular shape. Some thoughts work needed to work out method for detecting the boundary of the triangle.
2.Additional methods can be added to BumbleBee Class, to showcase the design feature of polymorphism behavour of Space Vehicle class.
