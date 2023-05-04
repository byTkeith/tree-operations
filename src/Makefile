# binary search program makefile
# Hussein Suleman
# 27 March 2017

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin



$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=BinaryTreeNode.class BTQueueNode.class BTQueue.class BinaryTree.class BinarySearchTree.class BinarySearchTreeTest.class	Posts.class	Account.class TokTik.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
	
run:
	java -cp bin TokTik
