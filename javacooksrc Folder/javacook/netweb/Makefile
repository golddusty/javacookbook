# Makefile for directory that may have files that deliberately contain errors.
# (I've included the source for these files for pedagogical reasons, but 
# they will not compile cleanly!!). This makefile is a bit twisty but it
# automatically eliminates these classes.

# $Id: Makefile.exclude-errors,v 1.5 2001/03/15 19:54:56 ian Exp $

JAVAC?=	jikes +E

all:	classes

classes:
		@echo "===> Building in `pwd`"
		# This directory includes some classes that will not compile as is;
		# they are present for pedagogic reasons. To compile all but them:
		# The tr turns newlines into spaces. The sed gets rid of the last one.
		$(JAVAC) $$(ls *.java | egrep -v $$(grep -l "EXPECT COMPILE ERROR" *.java | tr '\012' '|' | sed -e 's/|$$//'))

clean:
		@rm -f *.class
