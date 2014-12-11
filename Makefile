#Billy Barbaro

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
	MinFilter.java \
	MaxFilter.java \
	FilterCascade.java \
	ScalarLinearFilter.java \
	FIRFilter.java \
	GainFilter.java \
	BinomialFilter.java \

TESTS = \
	IdentityFilterTest.java \
	MinFilterNTest.java \
	MaxFilterNTest.java \
	AverageFilterNTest.java \
	AverageFilterTest.java \
	MinFilterTest.java \
	MaxFilterTest.java \
	FilterCascadeTest.java \
	ScalarLinearFilterTest.java \
	FIRFilterTest.java \
	GainFilterTest.java \
	BinomialFilterTest.java \

default: classes

classes: $(CLASSES:.java=.class)

test:
	javac -cp junit-4.10.jar:. $(TESTS)
	java -cp junit-4.10.jar:. org.junit.runner.JUnitCore $(TESTS:.java=)

doc:
	javadoc -d ./javadoc -cp junit-4.10.jar:. $(CLASSES)

clean:
	$(RM) *.class
	$(RM) *.java~
	$(RM) -r ./javadoc