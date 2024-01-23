- [CS 1632 - Software Quality Assurance](#cs-1632---software-quality-assurance)
  * [Before You Begin](#before-you-begin)
    + [The POM Maven build configuration](#the-pom-maven-build-configuration)
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
- [Submission](#submission)
- [GradeScope Feedback](#gradescope-feedback)
- [Groupwork Plan](#groupwork-plan)
- [Resources](#resources)

# CS 1632 - Software Quality Assurance
Spring Semester 2024 - Exercise 2

* DUE: February 4 (Sunday), 2024 11:59 PM

**GitHub Classroom Link:** https://classroom.github.com/a/bdFh7_iu

## Before You Begin

If you have not done so already, please [install Apache
Maven](/exercises/0/README.md#install-apache-maven) and [install
VSCode](/exercises/0/README.md#install-vscode) as instructed in the [Java
Assessment Exercise](/exercises/0/README.md).

Also, if you are working with a partner, you may also want to familiarize
yourself with the Live Share feature on VSCode:
https://code.visualstudio.com/learn/collaboration/live-share

### The POM Maven build configuration

Before we begin, let's take a moment to learn a little bit about the Maven
build framework.  The Maven build framework automates building, testing,
running, packaging, and deploying your code.  The VSCode IDE also internally
uses a Maven extension to piggyback on the Maven build system for Maven
projects.

All Maven projects have a file named [pom.xml](pom.xml) file which describes
the POM (Project Object Model).  Everything about the Maven build and test
process is governed by this file.

We learned that enforcing a uniform set of preconditions is key to reproducible
testing.  For non-trivial Java projects, those preconditions include external
packages that the project is dependent upon given in the form of Jar files in
the case of Java.  Those external packages will be transitively dependent on
yet other packages and "Jarmageddon" quickly ensues as the dependency tree
becomes large and complicated. "Jar Hell" follows, where versions of
dependencies on one system are not equivalent to the versions on another.  

The POM file ensures that the Java runtime version and all dependent package
versions are uniform across the development / testing / deployment life cycles,
by making this information explicit in the project file.  For example, the
[pom.xml](pom.xml) file for this project lists the Mockito and JUnit frameworks
as dependencies:

```
  <dependencies>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.2</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>5.7.0</version>
    </dependency>
    <dependency>
    ...
  </dependencies>
```

These dependencies (and all other transitively dependent packages) are
automatically downloaded from [Maven Central](https://search.maven.org/), and
stored in a local cache (a hidden ".m2" folder in your home folder) so that
they don't have to be downloaded every time.  You can visit the pages for
[mockito-core](https://mvnrepository.com/artifact/org.mockito/mockito-core/5.7.0)
and [junit](https://search.maven.org/artifact/junit/junit/4.13.2) for
yourself to download the Jar files manually.

In addition to dependency management, the POM file allows you to insert
arbitrary third party plugins during the build process.  In the provided
[pom.xml](pom.xml) file, you will see the jacoco-maven-plugin and the
exec-maven-plugin, among others:

```
  <build>
    <plugins>
      <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>${jacoco-maven-plugin.version}</version>
        ...
      </plugin>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>3.0.0</version>
        <configuration>
          <mainClass>edu.pitt.cs.RentACatImpl</mainClass>
        </configuration>
      </plugin>
      ...
    </plugins>
  </build>
```

The jacoco-maven-plugin gives you the ability to measure test coverage and the
exec-maven-plugin gives you the ability to run Java programs (in this case the
RentACatImpl class).

## Description

In this exercise, we will actually build the Rent-A-Cat rental system that we
discussed in the lecture.  We will complete the implementation under cover of
unit testing by writing the test infrastructure in concert with the code.

For this exercise, you will modify the classes in the source tree in the
following ways:

* Cat.java - The Cat interface with a createInstance method with which to create Cats of different types.  **Fill in** the part where the method creates a mock Cat type.
* CatImpl.java - An implementation of the Cat interface.  **Fill in** the class with member variables and method implementations.
* InstanceType.java - This is a Java enumeration for instance types, and you don't need to touch.
* RentACat.java - The RentACat interface with a createInstance method with which to create RentACats of different types.  **Fill in** the part where the method creates a mock RentACat type.
* RentACatImpl.java - An implementation of the RentACat interface.  **Fill in** the methods that are still incomplete.

* CatUnitTest.java - The JUnit class that unit tests Cat objects.
* RentACatUnitTest.java - The JUnit class that unit tests RentACat objects.
* RentACatIntegrationTest.java - The JUnit class that integration tests the entire Rent-A-Cat system.

All source code locations where you need to add code is marked with "// TODO"
comments.  These comments conveniently show up in the Problems pane of your
VSCode IDE.

## Running the Program

Please refer to the [Deliverable 1 README](../deliverables/1#rent-a-cat-system)
for details on how to operate the Rent-A-Cat system.

### Using VSCode

You can run the program using the VSCode "Run and Debug" extension on the left
menu (the one that looks like a play icon with a bug attached to it).  Once you
click on it, you will see a dropdown menu on the topside.  

1. To launch the solution version of the program, choose "Launch
   RentACatSolution" and then press the green play button.  

   After you luanch the program, try listing the cats available for rent:

   ```
   Option [1,2,3,4,5] > 1
   Cats for Rent
   ID 1. Jennyanydots
   ID 2. Old Deuteronomy
   ID 3. Mistoffelees
   Option [1,2,3,4,5] > 5
   Closing up shop for the day!
   ```

1. To launch the current implementation of the program, choose "Launch
RentACatImpl" and then press the green play button.  

   ```
   Option [1,2,3,4,5] > 1
   Cats for Rent
   WRITE CODE FOR THISOption [1,2,3,4,5] > 5
   Closing up shop for the day!
   ```

   That's not what you expected!  That is because the Rent-A-Cat system is
incomplete.  It should work as expected after you are done.

If you are curious about how the "Run and Debug" extension works, it is
configured by the .vscode/launch.json file in the same folder.

### Using Commandline

You can also run the program on the commandline using Maven.

1. To launch the solution version of the program, you simply need to invoke the
solution jar file included in the folder:

   ```
   java -jar rentacat-solution-1.0.0.jar
   ```

1. To launch the current implementation of the program, you first need to
compile the program using the 'test-compile' phase on Maven:

   ```
   mvn test-compile
   ```

   If the compilation is successful, all soure codes under src/ are compiled to
class files under target/classes.  Make sure you invoke the 'test-compile'
phase and not the 'compile' phase.  The former will compile both your
implementation classes under the src/main folder and your test classes under
the src/test folder.  The latter will only compile your implementation classes.

   Next, invoke the 'exec' phase, which is configured to invoked RentACatImpl in pom.xml:

   ```
   mvn exec:java 
   ```

## Testing the Program

Again, you can use either VSCode or the commandline to test your program.

### Using VSCode

You can run the program using the VSCode "Testing" extension on the left menu
(the one that looks like a flask icon).  Once you click on it, you will see
options to run the entire test suite, an individual JUnit test class, or an
individual JUnit test method. 

The "Testing" extension solely invokes the JUnit test classes and does not
invoke third party Maven testing plugins listed in the pom.xml file, such as
Jacoco.  For that, you will have to invoke Maven directly as explained below.

### Using Commandline

You can invoke the 'test' phase in Maven:

   ```
   mvn test
   ```

   The Maven framework looks for any JUnit test classes under src/test/, and
invokes them one by one.  You should get a result that looks like this:

```
...
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.pitt.cs.CatUnitTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.081 s -- in edu.pitt.cs.CatUnitTest
[INFO] Running edu.pitt.cs.RentACatIntegrationTest
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in edu.pitt.cs.RentACatIntegrationTest
[INFO] Running edu.pitt.cs.RentACatUnitTest
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in edu.pitt.cs.RentACatUnitTest
[INFO] 
[INFO] Results:
[INFO]
[INFO] Tests run: 27, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jacoco:0.8.11:report (post-unit-test) @ rentacat ---
[INFO] Loading execution data file C:\Users\mrabb\Documents\github\cs1632\CS1632_RentACat\target\jacoco.exec
[INFO] Analyzed bundle 'rentacat' with 5 classes
[INFO] 
[INFO] --- jacoco:0.8.11:check (check-unit-test) @ rentacat ---
[INFO] Loading execution data file C:\Users\mrabb\Documents\github\cs1632\CS1632_RentACat\target\jacoco.exec
[INFO] Analyzed bundle 'rentacat' with 5 classes
[WARNING] Rule violated for class edu.pitt.cs.RentACatImpl: instructions covered ratio is 0.00, but expected minimum is 0.20
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.047 s
[INFO] Finished at: 2024-01-23T09:34:07-05:00
[INFO] ------------------------------------------------------------------------
...
```

Note that out of the 27 tests run, 0 tests were failures.  Apparently, all
tests passed!  So are we done?  Far from it!  The reason that there are no
failures is because all test cases are currently empty.  Pay attention to the
following line in the output:

```
[WARNING] Rule violated for class edu.pitt.cs.RentACatImpl: instructions covered ratio is 0.00, but expected minimum is 0.20
```

It is saying that the test phase expected a minimum of 20% instruction coverage
for the RentACatImpl class, but the tests achieved 0%.  Hence that is why it
says 'BUILD FAILURE' in the end.  We were only able to cover 0% exactly
becauase all test cases are empty.  You can see for yourself in all the test
classes under the src/test/ folder that all test cases have just // TODO
comments in them.  The 20% coverage threshold is configured in the pom.xml file
in the Jacoco plugin section:

```
   ...
   <configuration>
     <dataFile>${project.build.directory}/jacoco.exec</dataFile>
     <rules>
       <rule>
	 <element>CLASS</element>
	 <limits>
	   <limit>
	     <counter>INSTRUCTION</counter>
	     <value>COVEREDRATIO</value>
	     <minimum>20%</minimum>
	   </limit>
	 </limits>
	 <includes>
	    <include>edu.pitt.cs.RentACatImpl</include>
	 </includes>
       </rule>
     </rules>
   </configuration>
   ...
```

Jacoco is short for the **Ja**va **Co**de **Co**verage tool.  The documentation
on how to configure like the above is given at:
https://www.eclemma.org/jacoco/trunk/doc/check-mojo.html We will talk more
about Jacoco later in the [Measuring Code Coverage](#measuring-code-coverage)
section.

## Software Developement Life Cycle using Test Driven Development

Now we know how to run the program and test the program, it is time to get to
work in completing the Rent-A-Cat system.

We will try to apply the Test Driven Development (TDD) model and the
Red-Green-Refactor (RGR) loop.  Try writing the test case(s) FIRST before
writing the code for a feature.  This way, you will always have 100% test
coverage for the code you have written and are writing.  Hence, if you break
any part of it in the course of adding a feature or refactoring your code, you
will know immediately.  Otherwise, if you test at the very end, it will be much
harder to find the defect and fix it.

Then, the logical order with which to write the code is the following:

1. CatUnitTest.java - Write the unit tests for Cat (Red: most tests will initially fail).
1. CatImpl.java - Write the implementation for Cat (Green: all tests should pass now).  Refactor as needed.
1. RentACatUnitTest.java - Write the unit tests for RentACat (Red: most tests will initially fail).
1. RentACatImpl.java - Write the implementation for RentACat (Green: all tests should pass now).  Refactor as needed.
1. RentACatIntegrationTest.java - Write integration tests for the Rent-A-Cat system (Hopefully everything works together).  Fortunately for you, you will be able to reuse a lot of the code you already wrote for RentACatUnitTest.java since many tests are going to look the same regardless of whether it is a unit test or integration test, with a few exceptions.

When writing the JUnit test cases, please pay close attention to the Javadoc
comment above each test method that describes the preconditions, execution
steps, and postconditions for that test case.  Also, please note that all or
part of the preconditions may be fulfilled by the test fixture built in the
@Before setUp() method in every JUnit test class.

In the @Before setUp() method of each test class, you are asked to create Cat
objects and RentACat objects that comprise the test fixture, as part of your
TODOs.  You have a choice between creating real objects or mock objects,
depending on the testing situation.  I will leave it up to you to make the
correct decision based on the lectures.  If you are creating a mock object, you
will have to fill in the TODO code for creating that mock object in either the
Cat.java or RentACat.java interfaces.

Another thing you need to do in the @Before setUp() method is to hijack the
system output for testing purposes.  Please refer to the
[textbook](../../software-quality-assurance-textbook.pdf) chapter 14.6 on
Testing System Output.  In short, you need to first back up the original system
output which is going to stdout (the standard output to your console).  Then
you need to replace it with a ByteArrayOutputStream variable named "out".  Now
all prints to System.out will be stored in the "out" buffer, which can be
converted to a String for testing purposes using out.toString().  In the @After
tearDown() method, you will restore stdout to system output.  Now, this may
complicate print debugging since all your debugging messages will go to "out"
instead of being printed to your console.  For debugging purposes, you can use
System.err.println rather than System.out.println, which uses the stderr
stream, which still goes to the console.  In VSCode, stderr is routed to a
special console called the Debug Console that is available as a tab on the
bottom pane.

### Verifying Your Test Cases

While you are still in the Red phase of the RGR loop, it is hard to have
confidence in your test code if you are a novice JUnit tester, especially since
your test is most likely failing.  To ease development, I have provided a
solution version of the software and also a buggy version of the software in
the rentacat-solution-1.0.0.jar file.  The JAR file includes the CatSolution
and RentACatSolution classes along with CatBuggy and RentACatBuggy classes.
Being a JAR file, the source code of those classes are not available to you
(for obvious reasons), but you can still invoke them.

In order to create solution versions of the Cat and RentACat classes do the
following in the @Before setUp() method:

```
c1 = Cat.createInstance(InstanceType.SOLUTION, 1, "Jennyanydots");
```
```
r = RentACat.createInstance(InstanceType.SOLUTION);
```

In order to create buggy versions, do the following:

```
c1 = Cat.createInstance(InstanceType.BUGGY, 1, "Jennyanydots");
```
```
r = RentACat.createInstance(InstanceType.BUGGY);
```

If you implemented your test case correctly, it should always pass for the
solution object and it should almost always fail for the buggy object.  There
are only 3 exceptions where the buggy object passes and they are:
RentACatIntegrationTest.testGetCatNullNumCats0(),
RentACatUnitTest.testGetCatNullNumCats0(), and
RentACatUnitTest.testGetCatNumCats3().

After you are done writing the test cases, please don't forget to revert back
to the IMPL InstanceType, to be able to test your own code for the green phase.

## Measuring Code Coverage

Code coverage is a metric that measures what percentage of the code base a
particular test run covered.  There are several ways to measure code coverage,
but the most widespread method is to measure the percentage of code lines
covered.  Typically a code coverage of above 80\% or 90\% is targeted in
software organizations.  I will require that level of coverage for the
Deliverable.  Since this is just an exercise, the minimum coverage is set to be
20%, which you should be able to achieve easily.

Jacoco (**Ja**va **Co**de **Co**verage tool), is one of the most popular code
coverage measurement tools among Java developers, and that's what we will use
in this class.  Jacoco has already been integrated into the test phase of our
Maven project, so you should already have coverage statistics generated from
your last 'mvn test' run at:

```
target/site/jacoco/
```

Now, if any of your JUnit tests failed, Jacoco will not generate the report.
I recommend that you makes your tests pass before running it.  If you want
to force Jacoco to produce the report even with test failures, do:

```
mvn jacoco:report
```

The statistics are generated XML (jacoco.xml), CSV (jacoco.csv), and HTML
(index.html) formats.  The XML and CSV formats are designed to be easily
readable by later stages of the testing pipeline that automatically generate
reports or send notifications to developers.  The HTML format is meant for
human cosumption.  Try opening index.html and drill down to either the CatImpl
class or the RentACatImpl class, which are the classes under test which we are
interested in measuring code coverage for.  If you have implemented all the
test cases, it should look similar to the following screenshots:

<img alt="Code Coverage Jacoco" src=code_coverage_cat.png width=700>

<img alt="Code Coverage Jacoco" src=code_coverage_rentacat.png width=700>

# Submission

Each pairwise group will do one submission to GradeScope as usual.  The
submitting member must use the "View or edit group" link at the top-right
corner of the assignment page after submission to add his/her partner.  

The submission this time is divided into two parts:

1.  Submit the repository created by GitHub Classroom for your team to
GradeScope at the **Exercise 2 GitHub** link.  Once you submit, GradeScope will
run the autograder to grade you and give feedback.  If you get deductions, fix
your code based on the feedback and resubmit.  Repeat until you don't get
deductions.

1. Create screenshots of code coverage stats as shown above.  Paste the
screenshots in the provided ReportTemplate.docx file, covert the docx to a PDF
file and submit to GradeScope at the **Exercise 2 Coverage** link.  

# GradeScope Feedback

The GradeScope autograder divided into largely 7 phases.  The first 6 phases are dedicated to testing your JUnit test classes:

1. CatUnitTest on CatSolution 
1. CatUnitTest on CatBuggy: 
1. RentACatUnitTest on RentACatSolution
1. RentACatUnitTest on RentACatBuggy
1. RentACatIntegrationTest on RentACatSolution
1. RentACatIntegrationTest on RentACatBuggy

You will notice that each test class is run against first the solution version
and then the buggy version.  The expectation is that they should all pass the
solution version and fail the buggy version with the exception of the
aforementioned 3 cases.

The last phase is RentACatIntegrationTestSolution on RentACatImpl, and the
purpose of this phase is to test the correctness of your implementation using
the solution version of the RentACatIntegrationTest against your code.

If you see test failures, read the feedback given by the autograder, fix your
code, and retry.

After the deadline, I will leave more detailed feedback on the "Feedback on
source code" question on GradeScope.  We will also check your code coverage
screenshot submission and give feedback.

# Groupwork Plan

One way you can divide up the work is the following for Cat and RentACAt:

[Cat]

Both partners writes the test for CatUnitTest and the code for CatImpl.  Then
compare the code to check each others' mistakes.  This part is relatively
simple and should provide a solid foundation from which to attempt RentACat.

[RentACat]

Both partners write the @Before setUp() method (including the mock creation
code) --- compare and verify.  Then divide the testing and writing of methods
in RentACat according to the following:

* Partner 1:
  * getCat
  * rentCat
* Partner 2:
  * listCats
  * renameCat
  * returnCat

To avoid merge conflicts on GitHub while working on the same file, I suggest
that you use the Live Share feature in VSCode to work on the same shared copy
of code, when you are working concurrently in real time (as in the classroom).
After you are done with the coding session, don't forget to commit and push the
code to the GitHub repository so that both partners have access to it later.
Outside of the classroom when you are not working concurrently, you will mostly
collaborate through GitHub.  Push frequently and also pull frequently from your
GitHub repository whenever you are done finishing a method to merge changes as
you go along.  Please communicate frequently and help each other out!

# Resources

These links are the same ones posted at the end of the slides:

* JUnit User Manual:  
https://junit.org/junit4/

* JUnit Reference Javadoc:  
http://junit.sourceforge.net/javadoc/  
For looking up methods only, not a user guide.

* Mockito User Manual:  
https://javadoc.io/static/org.mockito/mockito-core/3.2.4/org/mockito/Mockito.html  
Most useful is the sections about verification and stubbing.

* Jacoco User Manual:  
https://www.jacoco.org/userdoc/index.html
