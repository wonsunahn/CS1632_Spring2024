- [CS 1632 - Software Quality Assurance](#cs-1632---software-quality-assurance)
  * [Description](#description)
  * [Running the Program](#running-the-program)
    + [Using VSCode](#using-vscode)
    + [Using Commandline](#using-commandline)
  * [Testing the Program](#testing-the-program)
    + [Using VSCode](#using-vscode-1)
    + [Using Commandline](#using-commandline-1)
  * [Software Developement Life Cycle using Test Driven Development](#software-developement-life-cycle-using-test-driven-development)
    + [Verifying Your Test Cases](#verifying-your-test-cases)
  * [Measuring Code Coverage](#measuring-code-coverage)
  * [Additional Requirements](#additional-requirements)
- [Grading](#grading)
- [Submission](#submission)
- [GradeScope Feedback](#gradescope-feedback)
- [Groupwork Plan](#groupwork-plan)
- [Resources](#resources)

# CS 1632 - Software Quality Assurance
Spring Semester 2024

* DUE: February 13, 2024 before start of class

**GitHub Classroom Link:** https://classroom.github.com/a/UEy8eUyF

## Description

In this deliverable, we will build a game called CoffeeMakerQuest.
CoffeeMakerQuest is an old school text-based adventure game where the player
searches a house for coffee, cream, and sugar to make a cup of coffee for
herself.  Just like for Exercise 2, you will practice Test Driven Development
so that you always code under the cover of unit testing.  You will also do an
integration test at the end.

You will modify the classes in the source tree in the following ways:

* CoffeeMakerQuest.java - The CoffeeMakerQuest interface with a createInstance method with which to create CoffeeMakerQuest objects of different types.  **Fill in** the part where the method creates a mock CoffeeMakerQuest type.
* CoffeeMakerQuestImpl.java - An implementation of the CoffeeMakerQuest interface.  **Fill in** the class with member variables and method implementations.  When a newline is required as part of a string, take care to use the pre-defined platform-independent newline variable.
* Game - Contains the main method of the game.  This class is already complete.
* InstanceType.java - This is a Java enumeration for instance types, and you don't need to touch.
* Item.java - This is another Java enumeration for item types, and you don't need to touch.
* Player.java - The Player interface with a createInstance method with which to create Players of different types.  **Fill in** the part where the method creates a mock Player type.
* PlayerImpl.java - An implementation of the Player interface.  **Fill in** the class with member variables and method implementations.  When a newline is required as part of a string, take care to use the pre-defined platform-independent newline variable.
* Room.java - The Room interface with a createInstance method with which to create Rooms of different types.  **Fill in** the part where the method creates a mock Room type.
* RoomImpl.java - An implementation of the Room interface.  **Fill in** the class with member variables and method implementations.  When a newline is required as part of a string, take care to use the pre-defined platform-independent newline variable.
* RoomsJSONParser.java - Class that generates a list of rooms by reading in the rooms.config JSON file.  This class is already complete.

* CoffeeMakerQuestUnitTest.java - The JUnit class that unit tests CoffeeMakerQuest objects.
* PlayerUnitTest.java - The JUnit class that unit tests Player objects.
* RoomUnitTest.java - The JUnit class that unit tests Room objects.
* GameIntegrationTest.java - The JUnit class that integration tests the entire CoffeeMakerQuest game.

All source code locations where you need to add code is marked with "// TODO"
comments.  These comments conveniently show up in the Problems pane of your
VSCode IDE.

## Running the Program

### Using VSCode

You can run the program using the VSCode "Run and Debug" extension on the left
menu (the one that looks like a play icon with a bug attached to it).  Once you
click on it, you will see a dropdown menu on the topside.  

1. To launch the solution version of the program, choose "Launch GameSolution"
and then press the green play button.  

   After you launch the program, you can start playing the game:

   ```
   Coffee Maker Quest 1.0

   You see a Small room.
   It has a Quaint sofa.
   A Magenta door leads North.

    INSTRUCTIONS (N,S,L,I,D,H) >
   ...
   ```

1. To launch the current implementation of the program, choose "Launch Game"
and then press the green play button.  You will get the below output:

   ```
   Please make sure that the rooms.config file has doors at all interconnected rooms.
   ```

   The game fails to even initialize correctly.  It will work as expected once you are done.

### Using Commandline

You can also run the program on the commandline using Maven, as usual.

1. To launch the solution version of the program:

   ```
   java -jar coffeemaker-solution-1.0.0.jar
   ```

1. To launch the current implementation of the program, first compile:

   ```
   mvn test-compile
   ```

   Then, invoke the 'exec' phase, which is configured to invoke the Game main method in pom.xml:

   ```
   mvn exec:java 
   ```

## Testing the Program

Again, you can use either VSCode or the commandline to test your program.

### Using VSCode

You can run the program using the VSCode "Testing" extension on the left menu
(the one that looks like a flask icon).  Remember, the "Testing" extension
solely invokes the JUnit test classes and does not invoke third party Maven
testing plugins such as Jacoco.  You need to invoke the Maven test phase on the
commandline as explained below, or using the Maven Lifecycle menu (available
from the Explorer, below Java Projects.

### Using Commandline

You can invoke the Maven 'test' phase as follows:

   ```
   mvn test
   ```

You will notice that the build fails at this point due to Jacoco, because this
time a coverage threshold of 90% is set for the CoffeeMakerQuestImpl,
PlayerImpl and RoomImpl classes.

## Software Developement Life Cycle using Test Driven Development

Again, please apply the TDD RGR loop when developing this software.

Then, the logical order with which to write the code is the following:

1. RoomUnitTest.java - Write the unit tests for Room
1. RoomImpl.java - Write the implementation for Room 
1. PlayerUnitTest.java - Write the unit tests for Player
1. PlayerImpl.java - Write the implementation for Player
1. CoffeeMakerQuestUnitTest.java - Write the unit tests for CoffeeMakerQuest
1. CoffeeMakerQuestImpl.java - Write the implementation for CoffeeMakerQuest
1. GameIntegrationTest.java - Write integration tests for the CoffeeMakerQuest game.

In the @Before setUp() method of each test class, you are asked to create
various objects that comprise the test fixture, as part of your TODOs.  Again,
you have a choice between creating real objects or mock objects, depending on
the testing situation.  You have to make the right decision yourself.  If you
are creating a mock object, you will have to fill in the TODO code for creating
that mock object in either the Room.java, Plyaer.java, or CoffeeMakerQuest.java
interfaces.  If you feel like you don't need a mock object for a particular
interface, it is okay to leave that part unfilled.

### Verifying Your Test Cases

Just like for Exercise 2, I have provided a solution version of the software
and also a buggy version of the software in the coffeemaker-solution-1.0.0.jar
file.  The JAR file includes: RoomSolution, RoomBuggy, PlayerSolution,
PlayerBuggy, CoffeeMakerQuestSolution, CoffeeMakerQuestBuggy, GameSolution, and
GameBuggy classes.

You can create solution or buggy versions of a particular object in ways similar to Exercise 2:

```
p = Player.createInstance(InstanceType.SOLUTION);
```
```
p = Player.createInstance(InstanceType.BUGGY);
```

You can do the same for the other objects.  Now when you are writing
GameIntegrationTest.java, you don't create a Game object.  Instead, you call
the static main method of Game, so a different strategy needs to be used.
Instead of calling Game.main, you can call GameSolution.main or GameBuggy.main
to invoke the solution and buggy versions respectively.

For all your test cases, all of them should pass for the solution version
(obviously) and also all of them should fail for the buggy version (for various
reasons).

Again, after you are done writing the test cases, please don't forget to revert
back to the IMPL InstanceType and the Game.main method, to be able to test your
own code for the green phase of the RGR loop.

## Measuring Code Coverage

We are using Jacoco (**Ja**va **Co**de **Co**verage tool) again for test
coverage measurement.  As before, the coverage statistics are generated as part
of the Maven test phase at:

```
target/site/jacoco/
```

Remember, if any of your JUnit tests fail, Jacoco will not generate the report.
If you want to force Jacoco to produce the report even with test failures, do:

```
mvn jacoco:report
```

Just like for Exercise 2, I want you to provide to me screenshots of coverage
for individual classes that you have tested.  Please provide with me 4
screenshots of the 4 classes in the following order:

1. CoffeeMakerQuestImpl.java
2. PlayerImpl.java
3. RoomImpl.java
4. Game.java

## Additional Requirements

* For this program, no requirements are given as the requirement is that you
  mimic the output of the given **coffeemaker-solution.1.0.0.jar** file.  It is
incumbent upon you to thoroughly test the solution to discover expected
behavior and mimic that behavior.  Part of GradeScope grading will be about how
thoroughly you did this.  If GradeScope gives you a failure because your output
is different from the solution output, it will show you where the difference is
between brackets [].  GradeScope itself uses JUnit assertEquals behind the
scenes to test your program and showing the difference in brackets is a JUnit
assertEquals feature.

* Code coverage of the class CoffeeMakerQuestImpl, PlayerImpl, and RoomImpl
  classes must be at a minimum of **90%**.  If coverage falls below that
number, add more unit tests to the respective unit test classes.  View the
detailed line-by-line Jacoco coverage report for each method to see which lines
you are missing and come up with test cases that are able to hit those lines.

* When you generate or test strings in your code and multiple lines are
  involved, please make it a habit of using the **newline** variable that is
defined in all relevant classes, instead of hard-coding "\n" or "\r\n".  The
newline variable is initialized using the System.lineSeparator() API, which
will return the correct newline string for the current system ("\n" for
Mac/Linux systems and "\r\n" for Windows systems).  This is also the newline
that is used by all your System.out.println calls.  If you hard-code either
"\n" or "\r\n", there is a danger that it will be different from the system
newline and this will result in a mix of different newline strings in the
output.  This obviously complicates testing your software, not to speak of
other systems that may need to read an parse your output.

* You are expected to apply **DRY (Don't Repeat Yourself)** when coding.  This
  is an important aspect of writing testable and maintainable code (we will
discuss more aspects of this is a future chapter).  For example, the Player
class has the method **getInventoryString** that prints out the inventory
contents based on the current items.  You are required to use that method and
not implement a similar method of your own when processing the "I" command in
CoffeeMakerQuestImpl.  Similarly, there is a private helper method named
**getHelpString** within CoffeeMakerQuestImpl that you are asked to test and
implement.  You are required to use that method when processing the "H"
command.

* As part of the GameIntegrationTest.java @Before setUp() method, you need to
write the code to hijack System.out, just like you did for Exercise 2.  The
part that is new is that, this time, you need to hijack System.in as well
because you need to provide user input to the game.  Just like for System.out,
you want to make a backup of the original System.in (stdin) in the @Before
setUp() method and restore it in the @After tearDown() method (already done for
you).  The part that you need to fill in is the code to provide user input
appropriate for each test case.  You can do that using the following code
snippet:

  ```
  String someInputString = "String you want to provide to System.in";
  System.setIn(new ByteArrayInputStream(someInputString.getBytes()));
  ```

  After you do this, subsequent reads to System.in, such as the
scanner.nextLine() calls in Game.java, will consume lines in the provided
string line by line.

* As just mentioned, **getHelpString** is a private method and you will need to
  use **Java Reflection** to test this method.  You already practiced doing
this in Exercise 2.

* Coding style is also important for software quality in the long run (even
  though they are not technically defects as we learned).  In particular, a
uniform naming convention greatly improves the readability of your code.  A
widely used convention is called
[lowerCamelCase](https://en.wikipedia.org/wiki/Naming_convention_(programming)#Java)
convention.  That is the convention that was [first adopted when Sun
Microsystems first created the Java
language](https://www.oracle.com/technetwork/java/codeconventions-135099.html).
This is still the convention at the biggest companies using Java like
[Oracle](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html)
and [Google](https://google.github.io/styleguide/javaguide.html#s5-naming).
Please make sure you follow the lower camel case convention for all your
variables and methods for this project.  There is less agreement on other
formatting issues like indentation and line wrapping, but try to maintain a
uniform convention whatever you choose.

# Grading

TBD.  Most of the grading will be done by the autograder with a smaller
percentage going to the coverage report.  I will announce it soon!

# Submission

Each group will do one submissions to GradeScope as usual.

The submission is done in two parts:

1. Submit your GitHub Classroom Deliverable 2 repository to GradeScope at the
   **Deliverable 2 GitHub** link.  Once you submit, GradeScope will run the
autograder to grade you and give feedback.  If you get deductions, fix your
code based on the feedback and resubmit.  Repeat until you don't get
deductions.

1.  Please use the [ReportTemplate.docx](ReportTemplate.docx) file provided in
    this directory to write a short report.  A PDF version of the file is at
[ReportTemplate.pdf](ReportTemplate.pdf).  On the first page introduction,
please describe the division of work between group members and also any
difficulties you faced while using JUnit.  On the second page, paste a
screenshot of code coverage stats **after** having completed the coding.
Please refer to [Exercise 2](/exercises/2#measuring-code-coverage) on how to
create the screenshot.  Submit to GradeScope at the **Deliverable 2 Coverage**
link.  

# GradeScope Feedback

TBD.

# Groupwork Plan

Just like for Exercise 2, I recommend that you divide the list of methods to
implement / test into two halves and working on one half each.  Please document
how you divided the work in your report.

# Resources

These links are the same ones posted at the end of the slides:

* JUnit User manual:  
https://junit.org/junit5/docs/current/user-guide/  
The Writing Tests section is probably the most useful.

* JUnit Reference Javadoc:  
http://junit.sourceforge.net/javadoc/  
For looking up methods only, not a user guide.

* Mockito User Manual:  
https://javadoc.io/static/org.mockito/mockito-core/3.2.4/org/mockito/Mockito.html  
Most useful is the sections about verification and stubbing.

* Jacoco User Manual:  
https://www.jacoco.org/userdoc/index.html
