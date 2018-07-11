# Readme

The simplest way to work with this project is to import it as a Maven
project into the IDE of your choice. It's known to work with both
IntelliJ and with Eclipse with standard plugins.

If you don't have an IDE available, or prefer to work with command
line tools, you can build and run the project with the following
commands. Because the tests are in a different source directory from
the primary files, you'll need to run `mvn test` to build the test
files, and you'll need to explicitly specify the classpath when
running the tests themselves.

    $ # compile the main files like this
    $ mvn compile
    $ # compile the main files and test files like this
    $ mvn test
    $ # then run the tests (after compiling the test files) like this
    $ mvn exec:java -Dexec.mainClass="com.company.already_passing.AlreadyPassingTests" -Dexec.classpathScope="test"
    $ # To run the first failing test, do:
    $ mvn exec:java -Dexec.mainClass="com.company.failing_tests.FailingTest1" -Dexec.classpathScope="test"
