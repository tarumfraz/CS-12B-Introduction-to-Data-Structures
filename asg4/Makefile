# Build a class and do unit tests
JAVASRC = Calc.java CalcTest.java
SOURCES = Makefile.unit ${JAVASRC}
CLASSES = Calc.class CalcTest.class
TESTCLASS = CalcTest
CLASSPATH = "junit-4.12.jar:hamcrest-core-1.3.jar:."

all: ${CLASSES}

test: ${CLASSES}
	echo Main-class: CalcTest > Manifest
	jar cvfm test Manifest CalcTest.class
	rm Manifest
	chmod +x test
	java -cp ${CLASSPATH} org.junit.runner.JUnitCore ${TESTCLASS}

${CLASSES}: ${JAVASRC}
	javac -Xlint -cp ${CLASSPATH} ${JAVASRC}

clean:
	rm ${CLASSES} test


.PHONY: clean all test
