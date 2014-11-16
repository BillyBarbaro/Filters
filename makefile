#Billy Barbaro wxb107@case.edu

JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Combinations.java \
	Filter.java \
	ScalarFilter.java \
	IdentityFilter.java \
	NScalarFilter.java \
	MinFilterN.java \
	MaxFilterN.java \
	AverageFilterN.java \
	AverageFilter.java \
	ExtremeFilter.java \
	MaxFilter.java \
	MinFilter.java \
	ScalarLinearFilter.java \
	FIRFilter.java \
	GainFilter.java \
	BinomialFilter.java \

default: classes

classes: $(CLASSES:.java=.class)

test:
	#javac -cp junit-4.10.jar:. ChemistryTester.java
	#java -cp junit-4.10.jar:. org.junit.runner.JUnitCore ChemistryTester


clean:
	$(RM) *.class
	$(RM) *.java~