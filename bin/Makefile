# binary search program makefile
# Hussein Suleman
# 27 March 2017

JAVAC=/usr/bin/javac
JAVA=/user/bin/java
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin


$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) -sourcepath $(SRCDIR) $<

CLASSES=Edge.class Vertex.class Path.class GraphException.class Graph.class GraphExperiment.class 
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
	
run:
	$(JAVA) -cp bin GraphExperiment
