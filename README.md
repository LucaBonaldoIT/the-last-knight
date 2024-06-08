Instructions On Executing The Software

In order to execute the software the user must have copied the repository from the official GitHub repository.

Then, using IntelliJ IDE open the project folder, go to File and then Project Structure, add the dependencies clicking on “+“,  then “from Maven” and type “org.glassfish:javax.json:1.1.4” then “org.junit.jupiter:junit-jupiter:5.9.0” and “junit:junit:4.13.2” 

At this point the user can simply execute the main function to play the game.
For cloud saving, the user is required to install Python (>=3.10) and the required packages in requirements.txt. Then launch with FastAPI che main.py contained in the server folder, in the source folder.
The cloud saving is seamless.

![game-pic](https://github.com/LucaBonaldoIT/the-last-knight/assets/123835828/2613c6ba-ed54-4385-bdeb-c259086474a3)


Execution Environment

Tests have been run on Windows 10, Windows 11, Ubuntu LTS 22, Mac OS X El Capitan, and for those system the compatibility is 100%.

Other systems have not been tested, but if a Java JDK is available there should not be any problems.

Java Constraints

We tested the software on various Java version, follows the compatibility report:

•	Java SE 17: in this Java version the software was developed and therefore the compatibility is 100%.
•	Java SE 11: there seems to be no major problem or incompatibility
•	Java SE 22: there seems to be no major problem or incompatibility

Java Libraries

The whole game is runs entirely on built-in libraries, except for JUnit and Javax for the support of JSON writing and loading.

The graphical interface is based on Swing, on which a facade was tailored for a easier control of the render
