//
// A simple symbol table module.
//
// To compile:	gcc -Wall -ggdb -std=c99 -o sym sym.c
//
#define _GNU_SOURCE

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>

//
// Constants controlling various program limits
//
// The number of characters in a symbol table entry's name field
//
#define	MAX_NAME	8

//
// The number of entries in the symbol table
//
#define	NUM_SYMBOLS	5

//
// The number of characters in our input buffer
//
#define	BUFFER_SIZE	32

//
// Our basic symbol table entry
//
struct symbol {
	char name[MAX_NAME];
	int value;
};

//
// The symbol table itself
//
struct symbol table[NUM_SYMBOLS];

//
// Index of the next available slot in the symbol table
//
int index = 0;

//
// add_to_table() - add a name to the symbol table
//
// parameters:
//	name	the name of the new symbol
//	value	the data associated with this symbol
//
// returns:
//	'true' if the symbol was successfully entered in the table
//	'false' if there was an error
//
bool add_to_table( char name[], int value ) {
	int i;

	//
	// Copy the data into the current slot in the table
	//
	table[index].value = value;
	i = 0;
	while( (table[index].name[i] = name[i]) ) {
		++i;
	}

	//
	// Move to the next available slot
	//
	++index;

	return( true );
}

int main( void ) {
	char namebuf[BUFFER_SIZE];
	char valbuf[BUFFER_SIZE];
	int i;
	bool prompt;

	//
	// Only print prompts if the standard input
	// is coming from a terminal
	//

	prompt = isatty( fileno(stdin) );

	//
	// Begin by reading name + value pairs and 
	// adding them to the table
	//

	while( true ) {

		//
		// Read the next name; if the user typed
		// control-D, we'll see that as EOF
		//

		if( prompt ) {
			printf( "Enter a name (control-D to stop): " );
		}
		if( fgets(namebuf,BUFFER_SIZE,stdin) == NULL ) {
			break;
		}

		//
		// Locate the newline character in the buffer,
		// and replace it with a NUL character
		//
		i = 0;
		while( namebuf[i] != '\0' && namebuf[i] != '\n' ) {
			++i;
		}

		if( namebuf[i] == '\n' ) {
			namebuf[i] = '\0';
		}

		//
		// Read the associated number; again, check
		// for EOF
		//
		if( prompt ) {
			printf( "Enter a number: " );
		}
		if( fgets(valbuf,BUFFER_SIZE,stdin) == NULL ) {
			break;
		}

		//
		// Add this name+value pair to the table
		//
		if( !add_to_table(namebuf,atoi(valbuf)) ) {
			printf( "Error - insertion failed!\n" );
			break;
		}
	}

	//
	// All done - let's dump the symbol table
	//

	printf( "\n\nContents of symbol table:\n" );

	for( i = 0; i < index; ++i ) {
		printf( "%3d:  '%s', %d\n", i, table[i].name, table[i].value );
	}

	return( 0 );
}
