# A simple makefile with variables for the cyoa game

JAVASRC = cyoa.java Adventure.java Option.java Room.java 
DEMO = demo.adventure
SOURCES = README makefile demo.adventure ${JAVASRC}
MAINCLASS = cyoa
CLASSES = cyoa.class Adventure.class Option.class Room.class
JARFILE = myCyoa
JARCLASSES = ${CLASSES}

all: ${JARFILE}

${JARFILE}: ${CLASSES}
	echo "Main-class: ${MAINCLASS}" > Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	rm Manifest
	chmod +x ${JARFILE}

${CLASSES}: ${JAVASRC}
	javac -Xlint ${JAVASRC}

test:
	myCyoa ${DEMO}

clean:
	rm ${CLASSES} ${JARFILE}

.PHONY: clean all