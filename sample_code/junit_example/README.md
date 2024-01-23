# JUnit Example

## Description

This example demonstrates using Unit Testing and also Integration Testing to
test a linked list implementation.  LinkedListUnitTest does unit testing of
LinkedList by mocking the Node class, and LinkedListIntegrationTest does
integration testing by integrating real Node objects into the tests.  You can
learn a few things through this example:

1. The difference between unit testing and integration testing.

2. In unit testing, what to mock and what not to mock.

3. In unit testing, how to set up preconditions using Mockito.when.

4. In unit testing, how to test postconditions by using both assertions and Mockito.verify.

## Running Tests Using VSCode

Simplest way to compile and run the code is to open this folder in VSCode by
doing File > Open Folder on the menu.  VSCode will open the Maven project
specified in the pom.xml file in this folder.  Then, simply use the Java Test
Runner (the flask icon on the left) to run the tests.

## Running Tests Using Maven Commandline

As usual, simply invoke the test goal in your Maven project by doing:

```
mvn test
```

