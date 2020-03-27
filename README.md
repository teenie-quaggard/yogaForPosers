# Yoga For Posers 

A command line program built to help yogis keep track of their favourite yoga
 poses.
 
!["Screenshot of App start"](https://imgur.com/jxG12k0.jpg)

 ## Getting started
 These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

 ### Prerequisites
 To run this project, you'll need [JDK13](https://openjdk.java.net/projects/jdk/13/) 
 and [JUnit5](https://junit.org/junit5/) installed on your computer. It may
  also be helpful to use an Integrated Development Environment (IDE) that works
   well with Java such as [Eclipse](https://www.eclipse.org/downloads/packages/) 
  or, as I have used to write this project, [IntelliJ](https://www.jetbrains.com/idea/).
  
   This project was built using [Gradle Build Tool](https://gradle.org/). If you
    would also like to use Gradle to build this project, you'll first need to
     download it [here](https://gradle.org/install/). 
  
 ### Installing
 First open your Terminal and run 
 
 ```git clone https://github.com/teenie-quaggard/yogaForPosers.git```
 
 in the location where you'd like to save the project locally. 
 
 The easiest way to quickly build and run this project is by using an IDE that
  can compile and run Java files for you. For instance, if you are using
   IntelliJ, you will be able to run this project by opening the project in your IDE
  , opening up the ```App``` class located at: 
  
   ```yogaForPosers.src.main.java.space.jaggard.yogaforposers.App```
   
   and running the ```main``` method by clicking the green arrow that appears
    on the left-hand side of line 10 in the screenshot below.  
    !["Screen shot of App class"](https://imgur.com/Fx60bUY.jpg).

   If you're using Gradle, you should be able to build this project by
    running: ```./gradlew build``` in your terminal while in the root folder
     of this project. You should also then be able to run ```./gradlew run
     ``` to run the program from the command line.
     
   It is also possible to compile and run this program manually using javac
      which comes installed with JDK. For a tutorial on how to do that, have a 
   [look here](https://users.soe.ucsc.edu/~eaugusti/archive/102-winter16/misc/howToCompileAndRunFromCommandLine.html).
 
 ## Running tests
If using Gradle, use the command ```./gradlew test``` while within the root
 folder of his project in your terminal to run the tests.
 
 Otherwise, you should be able to run the tests using the IDE of your choice
. Different IDEs may require different types of configuration. If you are
  using IntelliJ, you can look [here to find out how to do so](https://www.jetbrains.com/help/idea/creating-run-debug-configuration-for-tests.html).

## Bugs noted and potential nice to add features
- Project lacks testing for if program closes appropriately
- Project lacks testing for randomized UUID (used as Entry ID) - this can
 potentially be side-stepped by refactoring and instead using a static Counter
  class to create unique IDs which are more human readable than UUID
- Program class should be refactored as at the moment it is holding basically
 all of the logic - command and their dependent methods should be refactored
  out into their own classes and should include unit tests
- Better validation should be implemented throughout the program
- Small UI improvements throughout

 ## Things I learned
 - How to implement the [Maven Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
 - [Package naming conventions in Java](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html)
 - The pattern of creating a TestConsole to substitute for user input and
  standard output of the console
- How to use Enums to nicely separate out constants from the code (and using
 them also in tests, so that tests don't fail when you change something
  simple within the copy.)
- That when storing lists of data, it is often smarter to store the
 items in their original data type. For instance, I had initially converted
  individual Entries into strings before storing them all in an array list
  . This meant that the individual Entries were much more difficult to deal
   with in the long run, even though it initially seemed better for testing
    to store them as strings.
- The refactor tool in IntelliJ is indispensable! 
- The difference between private and public methods
- That you should delete all your unit tests after creating integration tests
 
 ## Things I struggled with
- Implementing a design that stays within the scope of the stories, but is
 thoughtful of the future
- Implementing more VIM commands into my repertoire
- Taking advantage of IntelliJ's built in commands
- Remembering to take breaks while working from home (stretch hands!)
- Learning how to test for multiple inputs in Java (in this case, by creating
 a fake console)
- Remembering to ensure that I am following a TDD structure and that I see a
  test fail before continuing on through the TDD cycle
- Moving too quickly through the stories without testing for edge-cases
- Branching and committing at appropriate points
 

=======
[![Build Status](https://travis-ci.org/teenie-quaggard/yogaForPosers.svg?branch=master)](https://travis-ci.org/teenie-quaggard/yogaForPosers)

An app to help yogis keep track of their favourite yoga poses
>>>>>>> master
