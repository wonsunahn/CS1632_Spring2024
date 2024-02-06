- [CS 1632 - Software Quality Assurance](#cs-1632---software-quality-assurance)
  * [Description](#description)
  * [Prerequisites](#prerequisites)
  * [Task 1: Write test cases](#task-1-write-test-cases)
    + [Tips for Writing assertions for each Test](#tips-for-writing-assertions-for-each-test)
    + [Other Tips when using Selenium](#other-tips-when-using-selenium)
  * [Task 2: Add test cases to test suite and save project](#task-2-add-test-cases-to-test-suite-and-save-project)
  * [Task 3: Export test suite to JUnit class](#task-3-export-test-suite-to-junit-class)
    + [Why export to a JUnit class?](#why-export-to-a-junit-class-)
    + [How to export to JUnit for Selenium IDE](#how-to-export-to-junit-for-selenium-ide)
    + [Running the JUnit class](#running-the-junit-class)
  * [Tips for JUnit + Selenium problem solving](#tips-for-junit--selenium-problem-solving)
    + [How to deal with race conditions](#how-to-deal-with-race-conditions)
    + [How to enforce uniform window sizes](#how-to-enforce-uniform-window-sizes)
- [Submission](#submission)
- [GradeScope Feedback](#gradescope-feedback)
- [Groupwork Plan](#groupwork-plan)
- [Resources](#resources)
- [Extra Credit](#extra-credit)
  * [Description](#description-1)
  * [Submission](#submission-1)

# CS 1632 - Software Quality Assurance

* DUE: February 11 (Sunday), 2024 11:59 PM

**GitHub Classroom Link:** https://classroom.github.com/a/SqWpRzVd

## Description

For this assignment, you and a partner will write a systems-level, automated
black-box tests for the Pitt website using the Selenium IDE.  Specifically,
we are going to test the URL:

https://www.pitt.edu/

## Prerequisites

1. Please install the [Chrome web browser](https://www.google.com/chrome/)
   or the [Firefox web browser](https://www.mozilla.org/en-US/firefox/new/).

1. Please install the web driver for the browser of your choice.  The
   Selenium people just recently announced a new tool called Selenium
Manager that can automatically download and install a web driver that
matches your current browser.  You can invoke Selenium Manager as
follows, assuming you are using Chrome.  If you want to use Firefox, you can
just replace "chrome" with "firefox" in the instructions.

   On Windows:
   
   ```
   selenium-manager\windows\selenium-manager.exe --browser chrome
   ```

   On MacOS:

   ```
   selenium-manager/macos/selenium-manager --browser chrome
   ```

   On Linux:

   ```
   selenium-manager/linux/selenium-manager --browser chrome
   ```

   On running this tool, you should see something similar to the below:

   ```
   > selenium-manager\windows\selenium-manager.exe --browser chrome
   INFO    Driver path: C:\Users\mrabb\.cache\selenium\chromedriver\win64\116.0.5845.96\chromedriver.exe
   INFO    Browser path: C:\Program Files\Google\Chrome\Application\chrome.exe
   ```

1. Please adding the Selenium IDE browser extension for your web browser by
   selecting "Chrome Download" or "Firefox Download" on the below website:

   https://www.selenium.dev/selenium-ide/

   Then, open Selenium IDE by clicking on the newly created browser extension with
the "Se" symbol.  You should see a pop up window that looks very similar to the
one shown on the lecture slides.

## Task 1: Write test cases

Implement the 6 test cases listed in the [testplan.md](testplan.md) document
using Selenium IDE, for the requirements listed in
[requirements.md](requirements.md).  Remember, each test must end with an
assertion to check the postconditions!

The list of available assertions and other commands are available at:

https://www.selenium.dev/selenium-ide/docs/en/api/commands

```
IDENTIFIER: TEST-TITLE
TEST CASE: Tests that the title
PRECONDITIONS: [State of the system before performing execution steps]
EXECUTION STEPS: [Step-by-step instructions on how to perform test]
POSTCONDITIONS: [*EXPECTED* state of the system after having performed execution steps]
```
### Tips for Writing assertions for each Test

TEST-1-TITLE = This should be pretty straightforward.  Just make sure that when
you do **assert title**, the expected title string is in the "Target" box and
not the "Value" box.  If you read the Reference tab for **assert title**, the
title string is the first argument, and the first argument always goes into the
"Target" box, and the second argument goes into the "Value" box.  Sometimes
confusing, but that is how it always is.

TEST-2-LOGO-EXISTS - The important thing is when you use the **assert element
present** assertion, you use a locator string that indicates that the element
contains the alt text "University of Pittsburgh".  Otherwise, if you use any
random xpath locator indicating a position in the HTML page, you will be
checking that there exists an element in that position, but not that an element
with the alt text exists.  By the way, the logo with the alt text is the large
University of Pittsburgh logo at the top left.

TEST-3-LOGO-IMAGE - You will need to use **store attribute** followed by
**assert** for the postcondition check.  You will be storing the **src**
attribute value of the **img** element to a Selenium variable of your choosing
and asserting on the value of that variable.  Now the target argument for
**store attribute** does not directly take a locator string.  If you see the
Reference tab for the command, you will see that it takes \<locator
string\>@\<attribute name\> instead, where the attribute name in this case is
**src**.  Since the target argument is not a locator string, the target
selector button is disabled.  If you want to still use the target selector to
at least get the locator string part, you will have to do a workaround and
enter a command such as **assert text** or **click** which allows you to use
the target selector, fill in the locator string using it, and then revert back
to **store attribute**.

TEST-4-SCHOOLS-SCI - You will **assert text** to check that the 3rd school in
the list is "Computing & Information".  Now, for the locator passed to **assert
text**, you cannot use a locator for an element that contains the "Computing &
Information" text, unlike what we did for TEST-3-LOGO-EXISTS.  Using such a
locator string would give us no indication that the element is the 3rd item in
the list.  For that, you need an xpath locator that includes **li[3]**, or the
3rd **li** item in a list.

TEST-5-SCHOOLS-COUNT - The logic is that if **assert element present**
passes for the **li[16]** item and **assert element not present** passes for the
**li[17]** item, there must be exactly 16 lis and 16 schools.  Now, for
the **assert element not present** command, there is no such element so
obviously you would not be able to use the target selector tool to generate the
locator string.  You will have to copy the locator string from the **assert
element present** command and edit the locator to change li[16] to li[17].

TEST-6-SEARCH-CSC - Just like for TEST-4-SCHOOLS-SCI, you should use an xpath
locator string containing **div[3]**, since it is important to check the 3rd
item in the search list.

### Other Tips when using Selenium

Sometimes your test case will not work as expected.  Here are a few hints on how to debug a problem:

1. Check the **Log window** at the bottom of the Selenium IDE.  It will tell you
   which step failed for what reason (in red).

1. Select the test step that failed in the main test case window, and then
   select the **Reference tab** at the bottom pane of the IDE.  It will display
usage instructions for that command.  Remember always, the first argument goes
to the Target field and the second argument goes to the Value field, regardless
of command.

1. Sometimes the target component of a test step is the problem.  The selector
button tries to generate a **locator string** as best it can using xpath, css
selector, or id tag.  But it is not fool proof.  The problem is, the web page
may change ever so slightly on the next page load (e.g. due to a new post, or a
new comment) and then the locator will stop working.  You will notice that
there is a small down arrow at the end of the target text box.  If you click on
that arrow, you will see alternative locator strings to the current string.
These are locator strings that can uniquely identify the element that are being
proposed by Selenium IDE.  Mind you, there are hundreds of potential locator
strings that can uniquely identify an element and Selenium IDE is proposing a
few that it feels is good.

   Here is a list of all the different types of locators available in Selenium:

   https://www.selenium.dev/documentation/webdriver/elements/locators/   

   Here is an in-depth discussion on what are "good" locator strings:

   https://www.selenium.dev/documentation/test_practices/encouraged/locators/

   In summary, good locator string: 1) can locate an element uniquely according
to the test scenario (e.g. at a particular location, containing a particular
text, having a certain attribute, etc.) and 2) can withstand the test of time.
By the test of time, I mean that the locator string should not break even when
the website goes through future revisions and some parts of the page layout
changes.  For this, in general, the more concise the locator string is, the
less chance it has of some page modification changing the string in the middle.
That is why using the ID attribute is recommended by the Selenium
documentation, if it exists.

## Task 2: Add test cases to test suite and save project

1. Choose "Test Suites" from the left panel drop down menu.

1. There will already be a "Default Suite" there with possibly one or more tests.

1. Right click on "Default Suite", or click on the vertical-3-dot context menu button, and select "Rename" and rename to "PittEdu".

1. Right click on "PittEdu", or click on the vertical-3-dot context menu button,
and select "Add tests".  Make sure all tests are checked the press on the
"Select" button.

1. Click on the "Save project" button on the top right corner that looks like a
   floppy disk.  Save to file name "PittEdu.side" in the exercise root folder.

## Task 3: Export test suite to JUnit class

### Why export to a JUnit class?

There are many reasons why you would want to export to JUnit.

1. You may have a pre-existing testing framework in JUnit (or Python Pytest, or
   JavaScript Mocha, etc).  And you may want to merge the Selenium IDE testing
script to the language and framework of your choice.

1. Exporting to JUnit really gives you a good sense of what's happening under
   the covers (in terms of the actual calls to the web driver).  Also, if there
is a test case that is particularly hard to nail down just by using Selenium
IDE, you can touch it up in the form of exported Java code.  

1. Selenium IDE also gives the option to export your JUnit test directly to a
   Selenium Grid which can run the test cases in parallel.  This can allow you
to utilize a server farm to finish your testing very quickly, although we will
not explore this option today.

In the end, once you get familiar with the Selenium API and how locator strings
work, you will prefer coding in Java directly to the APIs.  At that stage,
Selenium IDE will feel more like extra baggage rather than a helping hand.  The
Selenium IDE scripting language is Turing complete, but it is primitive
compared to something like Java and the GUI coding interface, while helpful
initially, will start to feel clunky.  Moreover, the full range of Selenium
APIs are not represented in the scripting language (not to mention a few
defects in the subset that it does implement).

This part of the exercise will help you get more familiar with the Selenium API
and get you started on your journey to be a full Selenium programmer.

### How to export to JUnit for Selenium IDE

Once you are done writing your Selenium test suite, let's try exporting the test
suite in Selenium IDE to a Java JUnit test class.  

Follow these instructions:

1. Right click on "PittEdu", or click on the vertical-3-dot context menu
   button, and select "Export".

1. Select "Java JUnit" in the list of language options and optionally check
   "Include step descriptions as a separate comment" to generate more detailed
comments.  Leave other boxes unchecked.

1. Save the resulting file "PittEduTest.java" to the
   src/test/java/edu/pitt/cs test source directory.

1. Add the following line to the top of "PittEduTest.java":
   ```
   package edu.pitt.cs;
   ```

1. Also add the following line in the list of imports:
   ```
   import static org.hamcrest.MatcherAssert.assertThat;
   ```

   This gets rid of the annoying crossouts on assertThat calls in VSCode.

### Running the JUnit class

Before starting, let me warn you that your PittEduTest.java JUnit test class is
**may not work**, even after having made the above changes.  That is because
making Selenium work on websites sometimes requires some
[massaging](#tips-for-junit--selenium-problem-solving) and the Selenium IDE
code generation just takes you halfway there.  Moreover, Selenium IDE will
sometimes generate code which is flat out incorrect (one glaring example is
switching the locations of the expected and observed values in assertEquals
assertions).  Which is just fine because this is going to be a learning
opportunity for you to solve these problems and gain a deeper understanding of
Selenium Web Driver API.

With full expectation with things will fail, let's invoke the Maven test phase:
```
mvn test
```

Alternatively, you may invoke the Test Runner extension for JUnit on VSCode.

If things go properly, you will see the browser pop up repeatedly for each test
case, perform the actions, and close.  In the command line, you should see:

```
...
[INFO] Results:
[INFO]
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  44.361 s
[INFO] Finished at: 2024-01-24T18:51:20-04:00
[INFO] ------------------------------------------------------------------------
```

If you do see test failures, please read the failure message and the stack
trace for each failure to figure out what went wrong.  Then, modify each test
case that fails using tips listed in the next section.

## Tips for JUnit + Selenium problem solving

Here are a few tips for dealing with Selenium test failures.

### How to deal with race conditions

One big headache with Selenium is that there is an inherent **race
condition** in the way it works.  There are three components to this
distributed system: the web browser that renders the web page and runs
JavaScript, the web server that sends web data to the web browser
intermittently, and the web driver that sends commands to the web browser to
control its actions.  These three components will not synchronize with each
other unless you tell them to and events (such as page loads from web
server, DOM element rendering by the web browser, and commands from the web
driver) can happen in arbitrary order.  For example, your web browser may
not have finished rendering a button before your web driver sends a command
to click on it.  This leads to nondeterminism and unreproducible testing.

Fortunately, Selenium does provide you with a long list of synchronization
APIs that allow you to wait for a particular event to happen.  Details about
the different types of wait APIs available on Selenium are described in:

https://www.selenium.dev/documentation/webdriver/waits/
   
Most of the time, setting an **implicit wait** at the beginning is enough to
solve most race conditions.  It ensures that the web driver implicitly waits
for the given amount of time for a target element to be rendered when
sending any command, before flagging a failure. It is flexible in that it
will only wait the given amount of time if the element does not load
quickly, and will proceed immediately if it does.  Insert the following line
in the @Before setUp() method:

```
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
```

In order to use that line, you will need to also import this library:

```
import java.time.Duration;
```

Selenium IDE internally uses an implicit wait time of 30 seconds
when running a script, but when it exports the script to the JUnit test, it
fails to insert that implicit wait in the @Before setUp() method.  So if you
want one, you need to insert it yourself.

Sometimes, you may have to synchronize on events other than an element
getting rendered.  For that, you will have to do an **explicit wait** on that
event.  Here is an exhaustive list of events that you can wait on:

https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html

In some very rare cases, the event that you want to wait on is not in the
list of events that Selenium supports.  In that case, you have no choice but
to insert an arbitrary wait such as "Thread.sleep(3000)" (wait for 3
seconds).  It is best to avoid this because this is not true synchronization
and it only reduces the likelihood of race conditions without removing them
entirely.  And the catch is, we cannot wait for too long either because it
will slow down testing.

Luckily, most of our race conditions in PittEdu can be solved by simply setting
the implicit wait time at the beginning as explained above.  We just need to
wait until elements appear on screen before performing actions.  There is one
test TEST-4-SCHOOLS-SCI where you may need an explicit wait though.  Otherwise,
you may get an empty string on the 3rd element in the school list when
comparing with "Computing & Information".  In that case, you will need to
insert an explicit wait using the **wait for element visible** command on
Selenium IDE to wait for the "Colleges & Schools" list header to appear, right
before performing that assertion, to make sure that the list has correctly
rendered.  When exported to JUnit code, the Selenium IDE command will get translated to the following snippet of code:

```
    // 4 | waitForElementVisible | id=block-collegesschools-menu | 30000
    {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-collegesschools-menu")));
    }
```

### How to enforce uniform window sizes

Another common problem is that depending on the browser window size, certain
elements may disappear.  For example, the Reddit site would hide the "rules"
bar on the right hand side if the windows is too narrow.  One way to solve
this is to uniformly set the window size at the @Before setUp() method so
that all your test cases are tested on the same dimensions (and remove all
calls to setSize in your test cases):

   ```
   driver.manage().window().setSize(new Dimension(1200, 800));
   ```

# Submission

Each pairwise group will do a group submission to GradeScope as usual.  The
submitting member must use the "View or edit group" link at the top-right
corner of the assignment page after submission to add his/her partner.  

Submit the repository created by GitHub Classroom for your team to GradeScope
at the **Exercise 3 GitHub** link.  Make sure the files "PittEdu.side" and
"PittEduTest.java" are in your submission.  Once you submit, GradeScope will
run the autograder to grade you and give feedback.  If you get deductions, fix
your code based on the feedback and resubmit.  Repeat until you don't get
deductions.

# GradeScope Feedback

The GradeScope autograder simply runs your 6 tests against www.pitt.edu and
deducts 5 points per test failure.

You may be curious how I was able to run the tests on the GradeScope cloud
runners when they most likely don't have displays to render the Chrome browser.
The Chrome web driver, as well as other web drivers, can be run in "headless"
mode.  That is, the tests can be performed inside the web engine without having
to actually display the page.  This is common practice since in a work setting,
testers will be running tests on server machines or even on the cloud in Docker
images like I did.  If you need to do this in the future, you can achieve this
by passing additional options when creating the Chrome web driver:

```
options.addArguments("--headless");			// Enable running without a display
options.addArguments("--disable-dev-shm-usage");	// Disable /dev/shm which is limited to 64MB in Docker and use /tmp/ instead to store shared memory files
```

I add the above options by replacing the setUp() method in PittEduTest.java
with my own version that looks like the following:

```
@Before
public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");			// Enable running without a display
    options.addArguments("--disable-notifications");
    options.addArguments("--disable-dev-shm-usage");	// Disable /dev/shm which is limited to 64MB in Docker and use /tmp/ instead to store shared memory files
    options.addArguments("--no-sandbox");		// A quick and dirty way to run Selenium as root, bypassing the OS security model
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().setSize(new Dimension(1200, 800));
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
}
```

# Groupwork Plan

I want each member in the group to work on this individually and that is why I
created individual repositories in GitHub Classroom.  Practically, there is
only one single file that you will be modifying the Selenium IDE (.side)
project file, or the PittEduTest.java file.  And it would be difficult for
both of you to work on that single file.  Parallel modifications would result
in frequent merge conflicts.  When both of you are done, compare your work and
submit one finalized version to GradeScope.

# Resources

These links are the same ones posted at the end of the slides:

* Selenium IDE Getting Started:
https://www.selenium.dev/selenium-ide/docs/en/introduction/getting-started

* Selenium IDE Command Reference:  
https://www.selenium.dev/selenium-ide/docs/en/api/commands

* Selenium WebDriver Tutorial:
https://www.selenium.dev/documentation/webdriver/

* Official W3C XPath specification:
https://www.w3.org/TR/xpath/

* Unofficial XPath tutorial:
https://www.w3schools.com/xml/xpath_intro.asp.

* Official W3C CSS selector specification:
https://www.w3.org/TR/selectors/

* Unofficial CSS selector tutorial:
https://www.w3schools.com/cssref/css_selectors.asp

# Extra Credit

DUE: February 27 (Tuesday), 2024 before start of class

## Description

This extra credit is going to be 0.2 points out of 100 points for the entire
course, for anyone who is able to do this.

Previously, the suggested method for testing TEST-5-SCHOOLS-COUNT was to use
"assert element present" for the 16th item, followed by a "assert element not
present" for the 17th item.  

Admittedly, this is clunky.  It would be much cleaner if we could count the
number elements directly and verify that it is 16.

The Selenium IDE command "store xpath count" allows you to count the number of
elements that matches an xpath and store it inside a Selenium variable.  You
can later verify the value of the variable using the "assert" command.  Now,
you will not be able to acquire that xpath using the target selector button in
the IDE.  You will have to inspect the element on your web browser and come up
with a pattern than can match all 16 items in that list.  On both the Chrome
and Firefox browsers, when you right click on an HTML element to bring up the
context menu, there is an "Inspect" menu.  Clicking on the "Inspect" menu
brings up Inspector view.  When you right click on the highlighted element
again, there is a "Copy" menu on the context menu.  This allows you to copy the
XPath of the given element.

You will have to do a little bit of your own research on xpaths to figure out
what actual XPath to pass to the "store xpath count" command.  You can read the
[official W3C specification](https://www.w3.org/TR/xpath/).

But just like most specifications, it is focused on exact specification rather
than readability.  You will find this unofficial tutorial using examples more
digestible: https://www.w3schools.com/xml/xpath_intro.asp.


## Submission

Please do a group submission, like the exercise.  Submit the same repository
that you submitted for the exercise at the **Exercise 3 Extra Credit** link.
You should get a full score on the autograder and have used "store xpath count"
to get credit.  Make sure the files "PittEdu.side" and
"PittEduTest.java" are in your submission.  
