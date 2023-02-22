# Java-Mars-CHALLENGE
Tech Returner Mars Rover Challenge
What are required:
==================
A program is required to move rovers around the surface of Mars over a plateau which will be a square or rectangular grid in this task. Rovers should navigate the Plateau so they can use their special cameras and robot arms  to collect samples back to Planet Earth.
The Plateau is divided into a grid. A Rover’s position is represented by x and y co-ordinates and the letters N, S, W, E to represent North, South, West, East respectively. To move a rover, a string of letters is sent to the Rover. A ‘L’ letter will spin the Rover 90 degree left; ‘R’ letter will spin the Rover right, and ‘M’ letter moves the Rover forward by one grid.

How I design the solution 
==========================
For carrying this Rover mission, I designed a Console controller, that will take the input from the Commander:
1.	To define the range of rectangular plateau for surfing. The command can also indicate if an special material is known in the plateau, which required to be searched by the rover
2.	Two Rovers (M1 and M2) can be launched by the Commander, with input of the coordinate and the facing directions of each on the plateau.
3.	Navigation commands can be input to move the Rovers. Each rover will move based on input instruction. Each will detect if the plateau boundary is reached, or if each other rover is on the path, and accordingly stop at the last grid point.
4.	Each rover can probe for the special material, and if identified, alert is reported to console, and the mission will stop (moving).
5.	An option is available for the Commander to launch another Space Vehicle, being the Bumble Bee (Transformer)  to assist in the operation. The Bumble Bee accept movement instruction from Commander but has its own move capability-  it can move two times the distance for each instruction.

What I developed in this repo:
=============================
With an initial UML,I have designed an abstract Space Vehicle class for which I created the Mars Rover class  and BumbleBee Class as subclass to inherit. A Rectangular plateau class is also created and it implements an interface of Plateau. The Plateau interface defines method signature which can be implemented by other form of plateau in future. I then created a Console controller class which performs user interface, to create the plateau instance, and to use the Rover class to perform the function. 

I have used the TDD approach and created the Rovertest class, which include specific test cases and also parameterized test cases. Two CSV files are created for the test cases of moving one rover and moving two rovers respectively. 

How to Run the Program:
======================
The program can be run using the RoverTest class, as well as by using Console class.

Using RoverTest Class:
Just Run the MarsRoverTest class, and all the test cases with specific test data and test data from CSVs will pass. The test cases include:
-	Unit test of each method for one rover and two rover, to process string of L,R,M movement command
-	Move a rover around the full plateau boundary with returning to start point
-	Testing two rovers in operation such that each rover will stop if the other rover is in immediate front of the next step.
-	Testing probing Target such that rover report message to the caller.
The above test cases are identified by description in the Test Case

Using Console User Interface:
Run the Console.main() program. The Console will ask for user input as follows:
-	1) Input the plateau range using the top-right rectangle/square coordinate. For example, input “5 5”. The console will allow defining maximum size of “100 100”. 
-	The Commander can also input with a ‘P’ appended to the coordinate, to indicate this plateau has special material e.g. “5 5 P”. In this case, the program will generate a Random location for which the rovers need to search to accomplish mission.
-	2) The console will ask input of the landing position of Rover 1 e.g. input “0 1 N” to land at (0,1) coordinate with Rover facing North.
-	3). Next the console ask same question for Rover 2 i.e. landing position.
-	4) The console then asks for movement instruction  for Rover 1 i.e. input any letter string using L,R,M. e.g. “RMMMLMMRM” The rover will move accordingly and report the position reached (if a plateau boundary is reached, or another rover is in front, the rover will stop at its current location).
-	5) The console then asks for movement instruction for Rover 2. 
-	6) If both rover cannot find the special material after the instruction, the console will ask for input of next instructions for each rover.
-	7) At this point, the console will have a question if commander wants to launch BumbleBee for help. Enter ‘B’ to select Bumble Bee, or any ‘X’ key to exit the mission, or any other key to repeat instructions to both rovers.
-	8)When Bumble Bee is selected, the console asks for input of landing position and movement command, in same way for Rovers.

Thoughts for further work:
=========================
1.	More refactoring can be done to the codes, and maybe further breakdown into different smaller classes for facilitating future maintenance.
2.	Different shapes of plateau can be implemented, such as Triangular shape. Some thoughts work needed to work out method for detecting the boundary of the triangle.
3.	Enum class can be used to define the constants for ‘N’, ‘E’, ‘W’,’S’ when I have more  time. 
