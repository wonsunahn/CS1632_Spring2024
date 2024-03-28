- [CS 1632 - Software Quality Assurance](#cs-1632---software-quality-assurance)
  * [Overview](#overview)
  * [Background](#background)
  * [Compiling and Running](#compiling-and-running)
    + [Luck Mode](#luck-mode)
    + [Skill Mode](#skill-mode)
    + [Text UI Mode](#text-ui-mode)
  * [What to do](#what-to-do)
    + [Task 1: Write Unit Tests for BeanTest to drive BeanImpl development](#task-1-write-unit-tests-for-beantest-to-drive-beanimpl-development)
    + [Task 2: Write Integration Tests for BeanCounterLogicTest to drive BeanCounterLogic development](#task-2-write-integration-tests-for-beancounterlogictest-to-drive-beancounterlogic-development)
    + [Task 3: Generate Paths for Different Machine Configurations in JPFJUnitTest.java](#task-3-generate-paths-for-different-machine-configurations-in-jpfjunittestjava)
    + [Task 4: Write Property-based tests in JPFJUnitTest.java](#task-4-write-property-based-tests-in-jpfjunittestjava)
    + [Task 5: Add an Extra Property-based Test to JPFJUnitTest.java](#task-5-add-an-extra-property-based-test-to-jpfjunittestjava)
    + [Task 6: Linting and Auditing](#task-6-linting-and-auditing)
    + [Task 7: Manual System Testing](#task-7-manual-system-testing)
- [Grading](#grading)
- [Submission](#submission)
- [GradeScope Feedback](#gradescope-feedback)
- [Resources](#resources)

# CS 1632 - Software Quality Assurance

Spring Semester 2024

DUE: April 26 (Friday), 2024 11:59 PM

Due to the grading deadline, there is no late submission.  Please work with your partner to complete the project.

**GitHub Classroom Link:** TBD

## Overview

For this final deliverable, you will develop a full-fledged GUI program
(with the help of some skeleton code), along with various tests.  Don't
worry if you don't know GUI programming --- that part has already been done
for you.  In addition, there is a [Text UI mode](#text-ui-mode) that you can
use to manual test and debug your program.

All the projects so far have used some form of dynamic testing.  In this
project, we will focus grading on static testing.  You will be graded
upon is static testing techniques such as: linting, pattern-based bug finding,
and model checking.  

But you are still expected to use all the techniques we have learned so far:
test-driven development (TDD), automated unit testing, code coverage, and
manual testing to name a few to come up with good quality software.  The
GradeScope autograder will do extensive testing to test various features of
the software.  If any of the tests fail, that means you have not thoroughly
tested your software.

Please make sure you have Java 8 running on your machine, just like for
Exercise 5.

## Background

The bean counter is a device for statistics experiments devised by English
scientist Sir Francis Galton. It consists of an upright board with evenly
spaced pegs in a triangular form.  Beans are dropped from an opening at the top
of the board. Every time a bean hits a peg, it has a 50% chance of falling to
the left or to the right.  In this way, each bean takes a random path and
eventually falls into one of the slots at the bottom of the board.  After all
the beans fall through, the number of beans in each slot is counted.

See the following link for a more detailed description of the machine:
https://en.wikipedia.org/wiki/Bean_machine.

The bean counter had two contributions to statistics by demonstrating the following:
1. When the sample size is large enough, a [binomial distribution](https://en.wikipedia.org/wiki/Binomial_distribution) approaches a [bell curve](https://en.wikipedia.org/wiki/Normal_distribution).
2. It also demonstrated a phenomenon named [regression to the mean](https://en.wikipedia.org/wiki/Regression_toward_the_mean).

Regression to the mean had been (and still is) a source of numerous scientific
misconceptions.  People make conjectures all the time about all types of things
and provide reasons for it.

1. Why is my favorite sports team performing in a mediocre way when it won the championships last year?  Because my favorite player was traded.
2. Why did the crime rate in my city fall down to the national average?  Due to better policing.
3. Why did a student who did exceptionally well on the midterms perform just about average on the finals?  Because the student slacked off.

People always look for reasons for changes in data.  But often the reason
cannot be explained, because there was no reason for the change to begin with.
The change in data can just be due to a statistical anomaly called "regression
to the mean".  For example, an answer to question 3 can simply be that the
student was exceptionally lucky during the midterms (she guessed all multiple
choices and she got them all correct).  In the finals, her luck just wore off
and she got what she deserved.  This is called regression to the mean.
When a data point is on the extremes of the bell curve, it is often not because
there is anything special about that data point, it is because the laws of
probability worked in favor of it (or against it, depending on context) for
that particular trial.  If that's the case, chances are that the data point
will move to the mean in the next trial.

Now if the exceptional score was due to skill, then the regression would not
happen unless there was a regression in skill.  The problem is, it is very hard
to tell whether something was due to luck or skill just by looking at the
results, hence the numerous misconceptions.

## Compiling and Running

The program simulates a bean machine with 10 slots at the bottom (0-9).

Let's first compile the program by invoking Maven compile:

```
mvn compile
```

The program is executed with two commandline arguments:

```
$ java -cp target/classes edu.pitt.cs.BeanCounterGUI
Usage: java BeanCounterGUI <number of beans> <luck | skill>
Example: java BeanCounterGUI 500 luck 
```

The second argument "luck" or "skill" decides whether individual beans will use
luck or skill in navigating the bean machine.  

Let's do some exploratory testing.  You could use the class files that you have
just compiled, but the app doesn't do much at this point because the internal
logic has not yet been implemented (by you).  Instead, let's use a reference
implementation (that I wrote) named BeanCounterSolution.jar.

### Luck Mode

In luck mode, the bean counter operates conventionally as originally built by
Galton: a bean has an equal chance of going left or right on a peg.  So where
the bean lands at the bottom is purely due to luck.  Hence, you would expect
the beans to be heavily susceptible to regression to the mean.  Try the following:

1. Run BeanCounterSolution.jar in luck mode:
```
java -jar BeanCounterSolution.jar 500 luck
```
2. Press the "Fast" button to fast-forward to the end.
3. Note the average (should be close to 4.5 = 0 + 9 / 2).
4. Press the "Upper Half" button to just take the upper half of the sample.
5. Note the average (now it should be much higher since it's the upper half).
6. Press the "Repeat" button to scoop all the beans and bring them to the top.
7. Press the "Fast" button to restart the machine.
8. Note the average is again close to 4.5.

You have just observed regression to the mean.  You took the upper half of the
class, but when they were put through the exam again, they scored just about
average.  Did they slack off in the second exam?  No, they were just no better
than the other students to begin with.

To run your own (currently incomplete) code, you will be doing:

```
java -cp target/classes edu.pitt.cs.BeanCounterGUI 500 luck
```

### Skill Mode

In skill mode, the beans choose direction based on pure skill.  Each bean is
assigned a skill level from 0-9 on creation according to a bell curve with
average 4.5 and standard deviation 1.5.  A skill level of 9 means the bean
always makes the "right" choices (pun intended).  That means the bean will
always go right when a peg is encountered, resulting it falling into the
rightmost 9th slot. A skill level of 0 means that the bean will always go left,
resulting it falling into the leftmost 0th slot. For the in-between skill
levels, the bean will first go right then left. For example, for a skill level
of 7, the bean will go right 7 times then go left twice.  So where the bean
lands at the bottom would be determined entirely by the skill level of the
bean.  Try the following:

1. Run BeanCounterSolution.jar in skill mode:
```
java -jar BeanCounterSolution.jar 500 skill
```
2. Press the "Fast" button to fast-forward to the end.
3. Note the average (should be close to 4.5 = 0 + 9 / 2).
4. Press the "Upper Half" button to just take the upper half of the sample.
5. Note the average (now it should be much higher since it's the upper half).
6. Press the "Repeat" button to scoop all the beans and bring them to the top.
7. Press the "Fast" button to restart the machine.
8. Note the average is exactly the same as the average noted in Step 5.

You will observe that the average does not change at all when you repeat the
experiment with the upper half of the samples.  There is no regression to the
mean because the results are pre-determined by skill level.  In this case, the
student performed well on the first exam because they were actually skilled!

Also, you will notice the slots filling sequentially one by one in the repeat
run.  This is a side-effect of the slots at the bottom being collected one by
one when the repeat button is pressed.  All the beans in one slot have the same
skill level so the beans naturally get sorted out as a result of the
collection.

To run your own (currently incomplete) code, you will be doing:
```
java -cp target/classes edu.pitt.cs.BeanCounterGUI 500 skill
```

### Text UI Mode

You will notice that BeanCounterLogicImpl.java has an alternate main()
method.  This main() method is used to provide a rudimentary text user
interface.  You can invoke it by doing:

```
java -cp BeanCounterSolution.jar edu.pitt.cs.BeanCounterLogicSolution 10 500 luck debug
```

This is the usage information that gets printed when you don't pass any arguments:

```
Usage: java BeanCounterLogic slot_count bean_count <luck | skill> [debug]
Example: java BeanCounterLogic 10 400 luck
Example: java BeanCounterLogic 20 1000 skill debug
```

The last optional debug enables verbose output that prints the state of the
bean counter at each step.  This makes debugging easier as it is able to test
your bean counter logic in isolation from the GUI.  It also allows you to
adjust the slot count, something which is not possible with the GUI.

To run your own (currently incomplete) code, you will be doing:
```
java -cp target/classes edu.pitt.cs.BeanCounterLogicImpl 10 500 luck debug
```

## What to do

Here is a list of files and their contents:

* BeanCounterLogicImpl.java - The core logic of the bean machine.  Maintains a
  list of beans that fall one step in the machine whenever advanceStep() is
called.  Maps beans into a logical coordinate system.  Also contains a main
method which implements the Text UI of the program. (**modify**)

* BeanImpl.java - The Bean implementation.  Maintains the x-coordinate of the
  bean, as well as how many right moves are remaining if in skill mode.
Governs the movement of that particular bean when choose() is called, depending
on whether the bean is a skilled bean or a lucky bean.  The Random number
generator that gives randomness to the movement is injected in the BeanImpl
constructor for easier testing. (**modify**)

* JPFJUnitTest.java - An integration test for the BeanCounterLogicImpl class
  run on top of JPF.  You are asked to exhaustively test different machine
configurations (see [Model Checking Using JUnit](#model-checking-using-junit))
and all random outcomes of each machine. (**modify**)

* BeanCounterLogicTest.java - An integration test for the BeanCounterLogicImpl
  class run on the JUnit framework.  An integration test was chosen over a unit
test because, in order to do unit testing, you would have to mock the beans and
the beans are impossible to mock as their states need to change during machine
operation.  In order to do deterministic testing, either a seeded Random number
generator is used (for lucky beans) or a mock Random object is used (for
skilled beans). (**modify**)

* BeanTest.java - A unit test for the Bean class.  To keep the test
  deterministic, a mock Random object is used to control the randomness.
(**modify).

* BeanCounterLogic.java - The public interface of BeanCounterLogic.  The
  createInstance method creates an instance of BeanCounterLogicImpl (your
code), BeanCounterLogicBuggy (a buggy implementation), or
BeanCounterLogicSolution (the solution implementation) depending on
InstanceType.  Do not modify.

* Bean.java - The public interface of Bean.  The createInstance method creates
  an instance of BeanImpl (your code), BeanBuggy (a buggy implementation), or
BeanSolution (the solution implementation) depending on InstanceType.

* BeanCounterGUI.java - Contains the main method for the GUI interface of the
  program.  Creates a MainFrame.

* MainFrame.java - Contains the MainPanel and the ButtonPanel.

* MainPanel.java - The main display of the program where all the bean machine
  animations happen.  Renders the state of BeanCounterLogic on to the window
coordinate system (the "physical" coordinates).  In the process the logical
coordinates of Beans are translated to physical coordinates.

* <Some>Button.java - GUI buttons at the bottom of the main frame, along with
  event handlers when the button is pressed.

* BeanCounterSolution.jar - The solution implementation of bean counter.

* BeanCounterBuggy.jar - A buggy implementation of bean counter.

* jpf-core/runTest.bat and jpf-core/runTest.sh - Scripts to run JPFJUnitTest on
  top of JPF.  / You are asked to fill in and modify 5 files:
BeanCounterLogicImpl.java, BeanImpl.java, BeanCounterLogicTest.java,
BeanTest.java, and JPFJUnitTest.java.  The first two files complete the bean
counter implementation.  The third and fourth files are JUnit tests for the two
implementation files.  The last file tests the implementation using the Java
Path Finder model checker.  Take care that you limit your modifications to
these three files as all the other files will be ignored in your submission.
Also, take care that you do not change the public interfaces of
BeanCounterLogic and Bean as GradeScope relies on them.

I expect you to employ test-driven development (TDD) for this project and fully
embrace it.  I can guarantee you that it will shorten development time.  You
are going to write the tests anyway.  Why not write them at the beginning when
they will be the most useful?  Besides, writing the tests will give you a much
better understanding of how test application is supposed to behave before
starting the implementation.  I will lay down the steps, roughly in the order
you should perform them.

### Task 1: Write Unit Tests for BeanTest to drive BeanImpl development

Start by completing the tests in
[BeanTest.java](src/test/java/edu/pitt/cs/BeanTest.java).  Please start from
the @Before setUp() method to create the beans for testing.  Please use the
Bean.createInstance method to create the beans (the GradeScope autograder
relies on you to do that).  You have the option to pass in InstanceType.IMPL,
InstanceType.SOLUTION, or InstanceType.BUGGY to Bean.createInstance to create a
bean from your BeanImpl class, a solution Bean class (from
BeanCounterSolution.jar) and a buggy Bean class (from BeanCounterBuggy.jar).
As previously, you can test against the solution and buggy classes to gain
confidence that your test is working properly before applying it to your
BeanImpl to drive your development (TDD).  All tests should pass the solution
and all tests should fail the buggy Bean, with the exception of testConstructor
and testReset.  On your final submission, your test should be testing
InstanceType.IMPL, since by then your implementation would be complete.

Now, when testing Beans, there is a complication: Beans in their natural state
tend to behave wildy and randomly bound left and right, which is not amenable
to reliable testing.  This randomness comes from a Random object used to
produce that nondeterminism.  Fortunately, the software architect had the
foresight to make the code testable by injecting the Random object into the
Bean.createInstance method.  This allows you to pass in a Random object that
you can control to make it deterministic.  We learned two ways to do that: 1)
mock the Random object or 2) seed the Random object.  In this test, we are
going to choose the former.  Please stub the nextInt and nextGaussian methods
for the mock Random as specified in the // TODO comments.

When writing the tests, it helps to understand the bean machine coordinate
system to understand why the given postconditions are the postconditions.  The
Javadoc comment on top of BeanCounterLogicImpl provides you with a visual
representation of the coordinate system for a machine with 4 slots.

You can perform Maven testing as before using the commandline:

```
mvn test
```

Or using the VSCode Testing extension (the flask icon).

After you are satisfied with your tests, use them to drive the development of
BeanImpl.  Please read the Javadoc comment on top of BeanImpl closely before
starting.
   
### Task 2: Write Integration Tests for BeanCounterLogicTest to drive BeanCounterLogic development

Next complete the tests in
[BeanCounterLogicTest.java](src/test/java/edu/pitt/cs/BeanCounterLogicTest.java).
Note that we are doing integration tests and not mocking the Bean objects,
which would be needed for unit testing BeanCounterLogic.  This is not out of
choice but necessity.  In essence, the Bean and BeanCounterLogic objects are
too tightly coupled with each other to allow unit testing.  Each Bean
experiences numerous state changes as its X and Y coordinates change while
streaming down the machine, and BeanCounterLogic relies on those state changes
to happen to display the Bean at its correct location.  This exposes the
limitations of Mockito as there is no way to emulate that kind of complex
behavior using stubbing (well, to be honest with you [there
is](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#13),
but the resulting testing infrastructure is often too complex and too fragile
to make it worth the effort, which is why we did not discuss it in this class).

If you ask Mockists (those who adhere to the central tenets of unit testing
through mocking), this type of integration testing is a code smell.  However,
if you ask Classicists (the opposing camp who do not like mocking), this type
of integration testing comes naturally.  The argument is that you already
verified Bean through BeanTest, so it should be safe to use real Beans for
purposes of testing BeanCounterLogic.  Of course, the counter argument is that
that is exactly where the problem lies: it prevents BeanCounterLogic to be
tested and developed ahead of Bean.  A thoughtful discussion about the merits
of the Mockist and Classicist perspectives is in [this Medium article by Adrian
Booth](
https://medium.com/@adrianbooth/test-driven-development-wars-detroit-vs-london-classicist-vs-mockist-9956c78ae95f).

Now, if we are going to use real Beans, then we have the same problem with
nondeterminism as in BeanTest.  This time, we are going to both mock and seed
the Random objects.  Please fill in the // TODO comments in the @Before setUp()
method to do one or the other as instructed in the comment.

Again, please use InstanceType.SOLUTION and InstanceType.BUGGY in
BeanCounterLogic.createInstance and Bean.createInstance to verify your testing
against the solution and buggy versions.  Also, inside testMain(), instead of
calling BeanCounterLogicImpl(String[]), please call
BeanCounterLogicSolution(String[]) or BeanCounterLogicBuggy(String[]), to test
the respective versions.  If you test against the buggy version, all tests
should fail with the exception of testReset().

After you are confident with your tests, please use them to drive the
development of BeanCounterLogicImpl using InstanceType.IMPL instances.  Again,
please close attention to the Javadoc comment on top of BeanCounterLogicImpl
before starting.

If you want to see the bugs in BeanCounterLogicBuggy for yourself, you can try the following:

```
java -cp BeanCounterBuggy.jar edu.pitt.cs.BeanCounterLogicBuggy 10 400 luck
```

Then you will get something like (with some random variations):

```
$ java -cp BeanCounterBuggy.jar edu.pitt.cs.BeanCounterLogicBuggy 10 400 luck
Slot bean counts:
   2   7  21  71 101  88  54  22   3   0
```

Note that now things look pretty normal, but if you look closely, you will
notice that if you add up all the beans, they don't sum up to 400.  Strange!
Now you might want to try this on a smaller scale with 10 beans:

```
$ java -cp BeanCounterBuggy.jar edu.pitt.cs.BeanCounterLogicBuggy 5 10 luck
Slot bean counts:
   0   4   2   3   1
```

Now, the beans add up to 10.  Where did the bug go?  As you can see, it is
going to be very hard to find these kind of bugs with just plain JUnit testing
unless you know exactly what you are looking for and even then you need a
certain amount of luck.  To find these kind of bugs reliably we need the
JavaPathFinder. 

### Task 3: Generate Paths for Different Machine Configurations in JPFJUnitTest.java

By now we are somewhat confident that we have a mostly working application.
But as noted earlier with the buggy implementation, there still might be bugs
lurking because we did not test all different combinations of machine
configurations and also did not test all the random variations that a machine
go through even for a single configuration.  For that we need model checking
using JPF.

Notice that I have intentionally separated out the logic part of the program
from the GUI.  This was done to make model checking easier.  Model checking a
GUI is tricky and so is a multi-threaded event-driven program like
BeanCounterGUI.  Yes, JPF can model check even multi-threaded programs by
exhaustively going through all the interleavings.  But it is complicated and it
takes a long time because it has to go through many more states.  So we will
just check the core logic (BeanCounterLogic), which is the important part
anyway.

Just like for Exercise 5, we are going to use the runTest.bat or runTest.sh
scripts to run our JUnit class on top of JPF.  Please cd into jpf-core/ before
running scripts:

```
cd jpf-core
```

Then, for Windows:

```
.\runTest.bat edu.pitt.cs.JPFJUnitTest
```

For Mac/Linux:

```
bash runTest.sh edu.pitt.cs.JPFJUnitTest
```

Initially, since all tests are empty, it should show:

```
...
(snipped for brevity)

......................................... execution of testsuite: edu.pitt.cs.JPFJUnitTest SUCCEEDED
.... [1] testAdvanceStepBeanCount: Ok
.... [2] testAdvanceStepCoordinates: Ok
.... [3] testAdvanceStepPostCondition: Ok
.... [4] testReset: Ok
.... [5] testLowerHalf: Ok
.... [6] testUpperHalf: Ok
.... [7] testRepeat: Ok
......................................... tests: 7, failures: 0, errors: 0

tested classes: 1, passed: 1
```

The first thing you should do in JPFJUnitTest.java is to modify the setUp()
method to insert Verify calls to enumerate all possible user input values.  The
three input values relevant here are: slot count, bean count, and the boolean
value isLuck ("luck" or "skill" mode).  Once you insert the Verify calls, JPF
will explore each combination of input values.  As described in the "// TODO"
comment in the setUp() method, verify 1-5 slot count, 0-3 bean count, and both
"luck" and "skill" modes.  We will not test slot count 0 because then it means
there are no slots to receive beans and the machine basically falls apart.
Although the range of values is not exhaustive, these are enough values to give
us confidence that our machine works, while ensuring that JPF terminates within
a few seconds.

The testReset() method contains a println statement inserted in order to
demonstrate to you all the combinations of input values JPF explores.  Let's
see what it prints out initially without Verify API calls.  Run the tests again
and you should obtain output like the following:

```
...
(snipped for brevity)

......................................... testing testReset()
  running jpf with args:
JavaPathfinder core system v8.0 (rev 1a704e1d6c3d92178504f8cdfe57b068b4e22d9c) - (C) 2005-2014 United States Government. All rights reserved.


====================================================== system under test
edu.pitt.cs.JPFJUnitTest.runTestMethod()

====================================================== search started: 3/28/24, 6:26 AM
[WARNING] orphan NativePeer method: jdk.internal.misc.Unsafe.getUnsafe()Lsun/misc/Unsafe;
Failure in (slotCount=1, beanCount=0, isLucky=false):
Failure in (slotCount=1, beanCount=0, isLucky=true):
Failure in (slotCount=1, beanCount=1, isLucky=false):
Failure in (slotCount=1, beanCount=1, isLucky=true):
Failure in (slotCount=1, beanCount=2, isLucky=false):
Failure in (slotCount=1, beanCount=2, isLucky=true):
Failure in (slotCount=1, beanCount=3, isLucky=false):
Failure in (slotCount=1, beanCount=3, isLucky=true):
Failure in (slotCount=2, beanCount=0, isLucky=false):
Failure in (slotCount=2, beanCount=0, isLucky=true):
Failure in (slotCount=2, beanCount=1, isLucky=false):
Failure in (slotCount=2, beanCount=1, isLucky=true):
Failure in (slotCount=2, beanCount=2, isLucky=false):
Failure in (slotCount=2, beanCount=2, isLucky=true):
Failure in (slotCount=2, beanCount=3, isLucky=false):
Failure in (slotCount=2, beanCount=3, isLucky=true):
Failure in (slotCount=3, beanCount=0, isLucky=false):
Failure in (slotCount=3, beanCount=0, isLucky=true):
Failure in (slotCount=3, beanCount=1, isLucky=false):
Failure in (slotCount=3, beanCount=1, isLucky=true):
Failure in (slotCount=3, beanCount=2, isLucky=false):
Failure in (slotCount=3, beanCount=2, isLucky=true):
Failure in (slotCount=3, beanCount=3, isLucky=false):
Failure in (slotCount=3, beanCount=3, isLucky=true):
Failure in (slotCount=4, beanCount=0, isLucky=false):
Failure in (slotCount=4, beanCount=0, isLucky=true):
Failure in (slotCount=4, beanCount=1, isLucky=false):
Failure in (slotCount=4, beanCount=1, isLucky=true):
Failure in (slotCount=4, beanCount=2, isLucky=false):
Failure in (slotCount=4, beanCount=2, isLucky=true):
Failure in (slotCount=4, beanCount=3, isLucky=false):
Failure in (slotCount=4, beanCount=3, isLucky=true):
Failure in (slotCount=5, beanCount=0, isLucky=false):
Failure in (slotCount=5, beanCount=0, isLucky=true):
Failure in (slotCount=5, beanCount=1, isLucky=false):
Failure in (slotCount=5, beanCount=1, isLucky=true):
Failure in (slotCount=5, beanCount=2, isLucky=false):
Failure in (slotCount=5, beanCount=2, isLucky=true):
Failure in (slotCount=5, beanCount=3, isLucky=false):
Failure in (slotCount=5, beanCount=3, isLucky=true):

====================================================== results
no errors detected

====================================================== search finished: 3/28/24, 6:26 AM
......................................... testReset: Ok

(snipped for brevity)
...
```

You can see how JPF exhaustively tries out all possible combinations of machine
configurations.  Since the println was just for demonstration purposes, please
remove it in your final submission and replace it with the actual test.

### Task 4: Write Property-based tests in JPFJUnitTest.java

Complete [JPFJUnitTest.java](src/test/java/pitt/edu/cs/JPFJUnitTest.java) by
replacing the // TODO comments.  Pay close attention to the invariants you are
asked to test described in the Javadoc comment above each @Test method.  I
recommend that you always insert the failString that I initialized for you as
the first argument of every JUnit assert call so that you get that as part of
your failure message.  The failString describes the machine configuration that
is being currently tested and it will tell you which configuration led to the
failure.

Again, you can pass InstanceType.SOLUTION and InstanceType.BUGGY to
BeanCounterLogic.createInstance and Bean.createInstance to verify your tests
against the solution and buggy implementations.  The expectation is that all
solution tests pass and all buggy tests fail with the exception of testReset().
After you verify your tests, try running them on InstanceType.IMPL to verify
your own implementation.

### Task 5: Add an Extra Property-based Test to JPFJUnitTest.java

_Add one more test case of your own that helps you verify some invariant
property_.  Add that test at the very end.  Make sure you test a new invariant
that has not yet been tested (as in all the previous tests can pass and the new
test still fail).  There are many many invariants yet to be tested: be
creative!  Remember, it has to be an invariant.  If you write a test applicable
to a specific input combination using if statements, it doesn't count.  Make
sure that the test case is well documented with a Javadoc comment just like
other methods.

### Task 6: Linting and Auditing

Run the CheckStyle linter and the SpotBugs tool regularly while and after
coding.  Use the Maven site generation phase to generate reports as in Exercise 5:

```
mvn site
```

### Task 7: Manual System Testing

Even after doing model checking, you still need to verify that the program
"looks" right end-to-end in the GUI.  This is hard to do using automated
testing so you will do manual testing for this.  Refer to the
[requirements.md](requirements.md) file for the features that need testing.  I
am not going to make you write a test plan for this, but you will be graded on
how closely the GUI follows the requirements as demonstrated in BeanCounterSolution.jar

# Grading

TBD.

# Submission

You will create a GitHub repository just for deliverable 5.  Make sure you keep
the repository *PRIVATE* so that nobody else can access your repository.  Once
you are done modifying code, don't forget to commit and push your changes to
the github repository.  Submit your GitHub repository to GradeScope at the
"Deliverable 5 GitHub" link.  Don't forget to add your partner, if you have one.
Once you submit, GradeScope will run the autograder to grade you and give feedback.
If you get deductions, fix your code based on the feedback and resubmit.  Repeat
until you don't get deductions.

# GradeScope Feedback

TBD.  GradeScoe autograder is still under construction.  Will be released soon!

# Resources

* Java Path Finder manual:  
https://github.com/javapathfinder/jpf-core/wiki/How-to-use-JPF
http://javapathfinder.sourceforge.net/

* Java Path Finder Verify API:  
https://github.com/javapathfinder/jpf-core/wiki/Verify-API-of-JPF

* CheckStyle reference:  
https://checkstyle.sourceforge.io/checks.html  
If you don't understand a CheckStyle warning, read the corresponding entry inside google\_checks\_modified.xml under the checkstyle-jars folder and the above reference.

* SpotBugs reference:  
https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html
