- [CS 1632 - Software Quality Assurance](#cs-1632---software-quality-assurance)
  * [Description](#description)
  * [Test Application: GoatGoatCar](#test-application-goatgoatcar)
  * [Creating a Test Plan](#creating-a-test-plan)
  * [Creating a Traceability Matrix](#creating-a-traceability-matrix)
  * [Reporting Defects](#reporting-defects)
    + [Components of a Defect Report](#components-of-a-defect-report)
    + [GitHub Bug Tracking System](#github-bug-tracking-system)
  * [Format](#format)
  * [Submission](#submission)
  * [Groupwork Plan](#groupwork-plan)
  * [Extra Credit](#extra-credit)

# CS 1632 - Software Quality Assurance
Spring Semester 2024 - Exercise 1

* DUE: January 15 (Sunday), 2024 11:59 PM

**GitHub Classroom Link:** https://classroom.github.com/a/ca4Vo1H6

This is going to be your first group assignment.  When you click on the GitHub
Classroom link, you will be asked to either create a new team or join an
existing team.  Please use the team name that you listed on the Partnership
Contract.  If your partner already created a team, join that team rather than
create a new one.

## Description

For this exercise, you and your partner will determine a **test plan** for the
simple simulator GoatGoatCar, based on the requirements listed.

* There should be at least **5 test cases** for the 5 requirements.
* Test cases should have unambiguous preconditions, execution steps, and
  postconditions.  Remember, a test case should be **reproducible** no matter
who is doing the testing and should be executable **independent** of one other.
* There should be at least one base case, one edge case, and one corner case in the mix of test cases.
* The test plan should include a **traceability matrix** showing the
  mapping of test cases to requirements.  The traceability matrix should
**cover all requirements** for good test coverage.

You will execute the above test plan to find **defects** in the software.  There
are several known defects (10 at last count); try to find and report on at
least **three defects**.

You may find it useful to initially do some **exploratory testing** to
determine how the system works before coming up with a test plan.  Exploring
the software will give you a good feel of which test cases to add and which
ones are likely fo uncover defects.  A good test plan has edge cases and corner
cases as well as base cases.

## Test Application: GoatGoatCar

GoatGoatCar is going to be our way of determining the correct answer to the
"Monty Hall Problem" (https://en.wikipedia.org/wiki/Monty_Hall_problem).  The
Monty Hall Problem can be summarized in pictures:

<img alt="Figure 1" src=img/monty-hall-pic-1.jpg width=350>
<img alt="Figure 2" src=img/monty-hall-pic-2.jpg width=350>
<img alt="Figure 3" src=img/monty-hall-pic-3.jpg width=350>
<img alt="Figure 4" src=img/monty-hall-pic-4.jpg width=350>
<img alt="Figure 5" src=img/monty-hall-pic-5.jpg width=350>

What do you think?  The answer lies in the below illustration:

<img alt="Figure 6" src=img/monty-hall-pic-6.svg width=350>

If you still don't get it, you can read the above [wikipedia entry](https://en.wikipedia.org/wiki/Monty_Hall_problem).

Another way to get at the answer is through Monte Carlo simulations, that is
simulate the game thousands of times to empirically answer the question one
way or another.  The program we will test today randomly generates games,
plays them one way another, and gives a summary at the end of what percentage
of the time switching would give you the "good item" and what percentage of
the time staying would have won you the "bad" item.

The program will accept four arguments, in this order:

* __"Good" option__ - This will be the item that the player actually wants (e.g., a car).

* __"Bad" option__ - This will be the item that the player does not want (e.g., a goat)

* __Num Times__ - This is the number of rounds that will be simulated.

* __Num Threads__ - This will be the number of threads that the simulation runs in

Example command to execute.  This will use "car" as the good choice, "goat" as
the bad choice, 10001 as the number of rounds to simulate, and it will do it on
four threads.

```
$ java -jar GoatGoatCar.jar car goat 10001 4
```

GoatGoatCar.jar is available in this directory.  

Create a reasonable test plan based on the [requirements](requirements.md).
Hint: Try to have a combination of explicit boundary values and implicit
boundary values as well as interior values in your test cases.  As we learned,
this is where most of the defects will reside!

## Creating a Test Plan

Write up a test plan in the report template provided [below](#format).

Remember, the template for test cases -

```
	IDENTIFIER: [A unique number or string (e.g. TEST-ARGS-NUMBER-FIVE-ARGS)]
	TEST CASE: [A description of the test case]
	PRECONDITIONS: [State of the system before performing execution steps]
	EXECUTION STEPS: [Step-by-step instructions on how to perform test]
	POSTCONDITIONS: [*EXPECTED* state of the system after having performed execution steps]
```

The IDENTIFIER is some value which will uniquely specify the test case.  We
learned it can be either a number, or a more descriptive label (e.g.
TEST-INVALID-TIMES, TEST-LOW-NUM-TIMES, etc.).  For this exercise, please use a
descriptive label.  Please refer to [Lecture 4: Test
Plans](../../lectures/CS1632_Lecture4_Test_Plans.pdf) Slides 11 - 12 for more
details and examples for each item.

PRECONDITIONS is the **state of the system before performing the test**.  If
the system is a website, it is things like: "user is logged into the website"
or "user is subscribed to the mailing list", etc.  The program we will be
testing today is a commandline Java program where the test is simply running
the program with a set of arguments.  There is no program state to speak of
before running the test.  Then, what things should you put as preconditions?
There are plenty!  For a Java program, it is always important which Java
Runtime Environment (JRE) the program runs in.  You could say something like:
When "java -version" is run, the system outputs "java version "11.0.21".
Companies would actually perform multiple tests on the same program using the
same arguments for multiple versions of Java that their clients use, and each
would be a separate test case!

EXECUTION STEPS should be **numbered step-by-step instructions** on what you expect
the tester to do.  It should be exact to the letter so that tests are
reproducible.  The tested program does not allow any interaction with the user
other than to run it with commandline arguments, so all steps will be
one-liners in this case, but typically there will be multiple steps.

POSTCONDITIONS should be the **expected state** of the program after the test.
It is **not** the observed state.  So where should you get expectations of your
program?  From the [requirements](requirements.md) of course!  But note that it
should not be a simple copy and paste of the requirement.  It should be the
state exactly according to the requirement given the preconditions and
execution steps.  It should not overspecify beyond the requirements or
underspecify short of the requirements.  Otherwise, you will end up with false
positive defects or false negative defects, respectively.  That means
screenshots or copies of "correct" program output are often not good
postconditions.  That is because screenshots typically contain extra
information beyond what is required for correctness (e.g. the font of the text,
coloring, decorative images or text that is not strictly required).

Please review the various pitfalls described in the [Lecture 4: Test
Plans](../../lectures/CS1632_Lecture4_Test_Plans.pdf) lecture to avoid falling
into them while doing the exercise.

## Creating a Traceability Matrix

Add a traceability matrix to the report template provided [below](#format).

A traceability matrix allows us to correlate test cases to requirements and
vice versa.  It allows us to check why a test case is being run (to check one
or more requirements).  It also allows us to check how much testing a
particular requirement is receiving.  See Chapter 6 section 6 (6.6) in the
textbook for examples and details on creating them.

Note that some test cases may test several requirements at once.  This is only
natural.  As we saw on the last example on the lecture slides, in a real-world
traceability matrix, you can have one test case mapped to multiple
requirements.  Vice versa, you can have several test cases for one requirement.

Also note that a good traceability matrix must cover all requirements to have
no gaps in test coverage.  Please make sure of this.

## Reporting Defects

Please find **at least three defects** and report them through the GitHub issues system.  

### Components of a Defect Report

This is the correct format for defects -

```
	 IDENTIFIER: [A unique number or string (e.g. BUG-ARGS-NUMBER-FIVE-ARGS)]
	 SUMMARY: [A one sentence description of defect]
	 DESCRIPTION: [A detailed description of everything the tester discovered]
	 REPRODUCTION STEPS: [Preconditions + Steps to reproduce (similar to test case execution steps)]
	 EXPECTED BEHAVIOR: [What you expected according to requirements]
	 OBSERVED BEHAVIOR: [What you *ACTUALLY* saw]
```

Please refer to [Lecture 5: Defects](../../lectures/CS1632_Lecture5_Defects.pdf) Slides 13 - 27
for more details and examples for each item.  Optional bug report items
such as SEVERITY or IMPACT were not included for this exercise.

REPRODUCTION STEPS should start with preconditions, if there is no separate
entry for preconditions as in this case.  You will not be able to reproduce the
bug even if you reproduce the steps if you start from a different precondition!  For example:

```
REPRODUCTION STEPS:
   Preconditions:
   - "java -version" shows java version "11.0.21".
   - GoatGoatCar.jar file is in the current directory.
   Steps:
   1. ...
   2. ...
```

EXPECTED BEHAVIOR is literally what it says.  If you discovered this defect
while running a test case, it would look very similar to the POSTCONDITIONS of
the test case.

OBSERVED BEHAVIOR is arguably the most important component of a defect report.
You should describe what you observed with as much detail as possible so that
developers can use that information to debug.  Screenshots of your application
output or webpage are highly encouraged since that is the best way to convey
what you see.  If your application crashed, don't just say it crashed --- that
is not going to be very helpful.  Include all the output the program displayed
while crashing, for example an exception stack trace if you are testing a Java
program.  A common misconception is: "I have included all the reproduction
steps so the developer can test for him/herself so I can be lazy about
describing observed behavior."  You cannot assume that the developer will
easily be able to reproduce the defect.  There are a myriad of things that
could go wrong: 1) You may have missed an important precondition in your
report, or 2) You may not have described the reproduction steps in enough
detail (note that sometimes even timing of input is important), or 3) The
program may be a nondeterministic program to begin with.

### GitHub Bug Tracking System

To get hands on experience in defect reporting, tracking, and resolution, you
are going to use the GitHub issue management system.

On your GitHub classroom repository, Click on the "Issues" tab on the top.
Initially you should have 0 open issues.  Click on the "New issue" button on
the top right.  Fill the comment box with the defect report properly formatted
with the 6 items shown above.  For the title, use the content of the SUMMARY
item.  When all is filled in, click on the "Submit new issue" button.  

After the issue is submitted, perform **triage** and tag the issue as a "bug"
and assign it to one of the group members, as shown below in the first red box:

<img alt="Issues list" src=img/open_issue.png>

Now if you click on the "Issues" tab, you should see the new open issue:

<img alt="Issues list" src=img/issues_list.png>

Now, it is time to resolve the issue.  Have the assignee for the issue open the
issue by clicking on it, and then have her click the "Create a branch" link
shown in the second red box above.  This will create a new **git branch** to work
on the issue.  Once you are done creating the branch, you should see it appear
on the issue page (see red box):

<img alt="create branch" src=img/create_branch.png>

Now click on the branch name (in this case,
"1-when-42-is-passed-as-the-number-of-times...").  That would take you to the
newly created branch as below:

<img alt="open branch" src=img/open_branch.png>

Make sure that the branch selector indicates the newly created issue branch,
not the main branch, as seen in the red box above.  Now, we were doing black
box testing, so we do not have access to the source code.  So, we are going to
resolve this issue by sneakily adding a new requirement to the requirements
specification, and then claim that this is a feature, not a bug.  Navigate to
the requirements.md file and add the new requirement as shown below in the red
box: 

<img alt="edit requirement" src=img/edit_requirement.png>

Go ahread and commit the change to the issue branch.  Now that we are done with
our fix, we are going to create a **pull request**, which is a request to pull
the changes in the issue branch into the main branch.  One or more people can
review that request and make sure everything is kosher before pulling
everything into the main branch, because updating the main branch comes with
risks.  Create the pull request by clicking on the "Pull requests" tab and
clicking on the "New pull request" button as showin in the red box below:

<img alt="create pull request" src=img/create_pull_request.png>

In the ensuing page, select the issue branch as the source branch as indicated
in the red box below:

<img alt="compare changes" src=img/compare_changes.png>

You can review the changes that will be pulled into the main branch as a result
of this pull request in green color.  If you are satisfied, go ahead and cluck
on the "Create pull request" button.  If you follow through, you will see the
new pull request created as seen below:

<img alt="merge pull request" src=img/merge_pull_request.png>

You can see that the issue branch has no merge conflicts with the main branch,
and that you have the option to add one or more reviewers before merging.  If
you had a CI (Continuous Integration) pipeline, then automated testing would
typically be performed on the issue branch at this point, but that will come
later in the semester. :)  for now, we are happy to merge the branch.  Click on
the "Merge pull request" button to do so.  Go ahead and confirm the merge when
prompted to do so.  You will see the pull request now successfully merged and
closed as you see below:

<img alt="merge pull request" src=img/merge_pull_request.png>

Delete the branch by clicking on the "Delete branch" button since you no longer
need the branch since it is already merged.  Closing the pull request will
automatically close the issue associated with that pull request, so that when
you click on the "Issues" tab, you will no longer see any open issues.  Now you
need to click on the "Closed" issues tab indcated by the red box below to see
the closed issue:

<img alt="closed issue" src=img/closed_issue.png>

Now, as you may have guessed, the above defect is a bogus defect.  Please find
three real defects, open issues for them, and close them using the process
above, by modifying the requirements to turn the bugs into features.  Of
course, in a real world scenario, you would most likely modify the source code,
not the requirements, but the process would be the same.

## Format

You will write a short report that includes the test plan that you created as
well as your defect report.
Please use the [ReportTemplate.docx](ReportTemplate.docx) file provided in this
directory to write your report.  If you don't have a .docx compatible word
processor, that's perfectly fine as long as you follow the same organization.
A PDF version of the file is at [ReportTemplate.pdf](ReportTemplate.pdf).

The report should start with a cover page with:
* The names of the people in the group

Write a short introduction that includes:
* IDENTIFIER of one base case and why you think it is a base case.
* IDENTIFIER of one edge case and why you think it is an edge case.
* IDENTIFIER of one corner case and why you think it is a corner case.

ON A NEW PAGE, a traceability matrix should be provided mapping the test cases
with their associated requirements.  Remember that all requirements should map
to AT LEAST ONE test case, and all test cases should map to AT LEAST ONE
requirement.  

ON A NEW PAGE, a list of the actual test cases should follow.  You may name
them any way you wish, but please be consistent.  Please write them out in this
format -

	IDENTIFIER:
	TEST CASE: 
	PRECONDITIONS:
	EXECUTION STEPS:
	POSTCONDITIONS:

ON A NEW PAGE, copy and paste the link to your GitHub repository issues page with the 3 closed issues.

## Submission

Submission will be reflected on your participation grade.  As long as the
submission shows any amount of reasonable work was done (that is, you don't get
a 0 on GradeScope), it will count.  On the other hand, the actual score you get
on GradeScope will not count towards your grade.  It's there only for feedback
purposes and help you do Deliverable 1 better.  This applies to all future
exercises.

Each pairwise group shall do a *shared* submission to GradeScope to the
**Exercise 1** link, by *one member* of the group.  The submitting member shall
press the **View or edit group** link at the top-right corner of the assignment
page before or after submission to add his/her partner.  That way, the feedback
will be accessible to both of you (and the credit).  You may submit as many
times as you wish before the deadline as long as you make sure of this. This
applies to all future exercises and deliverables.

When you submit, please make sure that the introduction, traceability matrix,
test cases, and defects are on seperate pages.  You will be submitting to
GradeScope in PDF format.  When you submit, you will be asked to assign pages
in the PDF file to each rubric item.

When your exercise is marked as graded, you should find feedback written on
your grade details.  Please use the feedback wisely when doing Deliverable 1!

## Groupwork Plan

Please create a shared online document for the report using Google Docs or
Microsoft OneDrive or your preferred cloud service.  Each partner shall come up
with three test cases and one defect each, for a sum of six test cases and
three defects for the group (or more, if you wish).  The first partner shall
work on the requirements FUN-ARGS-NUMBER and FUN-ARGS-INVALID.  The second
partner shall work on the requirements FUN-DISPLAY-RESULTS,
FUN-DISPLAY-ITERATIONS, and FUN-SMALL-NUM.

Once you are done, check each other's work to make sure that the test case
documentation and defect reporting were done properly.  If you see issues,
discuss.  Once you are both satisfied, you may submit.

## Extra Credit

* DUE: February 6 (Tuesday), 2024 before start of class

This submission is optional.  An extra credit of 1 point out of 100 points for
the entire course will be awarded to the group that finds the most number of
defects in the program.  You must have found at least 5 unique defects to
qualify for the competition.  There can be multiple winners too if there is a
tie!

Duplicate defects that are really the same defect that is triggered by two
different inputs will be counted only once.  How do you know if it is the same
defect?  If they display the same behavior (e.g. causes the same type of
exception at the same source code line).

Also, some behaviors that you may think are defects are expected behaviors.  At
below are some examples: 

1. Bash behavior

```
$ java -jar GoatGoatCar.jar car goat 1000 \
> 
```
... or ...
```
$ java -jar GoatGoatCar.jar car goat 1000 "
> 
```

This is just normal bash behavior that allows user to enter multi-line
commands.  In the first case, the newline was escaped (\) and in the second
case, a multi-line string was started using the quote(") charater.  Other
special characters recognized by bash is listed here:

https://www.tldp.org/LDP/abs/html/special-chars.html

2. Empty names or duplicate names

```
$ java -jar GoatGoatCar.jar "" "" 1000 4
Thread 0: 250 iterations.
Thread 1: 250 iterations.
Thread 2: 250 iterations.
Thread 3: 250 iterations.
Calculating..


Switch:
 : 68.500%
 : 31.500%
-----------------------------
Stay:
 : 31.500%
 : 68.500%
```

There is no requirement that the "good" and "bad" strings have to be
unique, or they cannot be empty strings for that matter.  This is still
behavior conformant with the requirements.

Please submit to the **Exercise 1 Extra Credit** link on GradeScope in the same
manner as for Exercise 1.  Please use the
[ReportTemplateExtraCredit.docx](ReportTemplateExtraCredit.docx) to write the
report.  In addition to the link to your GitHub issues, I am also asking you to
attach a screenshot of the list of issues so that I can confirm you did not add
new issues after the deadline.  Only defects that I can reproduce will be
counted.  That means they must be properly reported using the preconditions,
execution steps, and postconditions template described on the worksheet.
