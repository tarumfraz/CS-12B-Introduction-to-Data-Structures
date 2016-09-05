# Makefile for the list.c program
# makes executing and cleaning the build files much easier


CC = gcc
CFLAGS = -Wall -g -std=c99
OBJS = list.c
OUTPUT = list
HEADERS = valgrind

${OUTPUT}: ${OBJS}
	${CC} $< -o ${OUTPUT}

%.o: %.c ${HEADERS}
	${CC} -c ${CFLAGS} $<

clean:
	rm -f *.o

spotless: clean
	rm -f ${OUTPUT}

test: ${OUTPUT}
	${HEADERS} ./${OUTPUT}

.PHONY: clean spotless test