compile:
	find -name "*.java" > classes.txt
	javac -g -cp lib/AbsoluteLayout.jar:lib/junit-4.13-rc-1.jar:lib/hamcrest-core-1.3.jar:. @classes.txt -d bin
	rm classes.txt

run:
	java -cp lib/AbsoluteLayout.jar:bin:. src.Main > /dev/null 2>&1

runConsole:
	java -cp bin src.Console

runDriver:
	java -cp bin tests.Main

runJUnit:
	java -cp lib/junit-4.13-rc-1.jar:lib/hamcrest-core-1.3.jar:bin:. org.junit.runner.JUnitCore tests.JUnit.IntegerToByteTest

clean:
	rm -Rf bin/*
