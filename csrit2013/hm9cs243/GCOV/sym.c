/// A symbol table module.
///
/// Basic compile:	gcc -Wall -ggdb -std=c99 -o sym sym.c
//
#define _GNU_SOURCE

#include <assert.h>
#include <ctype.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>

/// The number of characters in the input buffer
#define	BUFFER_SIZE	32

#define	CHUNK_SIZE	100

#define	MAX_NAME	20

/// a basic symbol table entry
//
typedef
struct Symbol_S {
	int value;
	char name[MAX_NAME];
} SymTab_T;

/// The symbol table itself
//
static SymTab_T * table = NULL;

/// the next available slot in the symbol table
//
static int next = 0;

/// size of the amount of allocated memory
//
static int size = CHUNK_SIZE;

/// resize the amount of allocated memory
/// side effect: size doubles
//
static SymTab_T * resize( SymTab_T * ptr, int oldsize ) {
	size = size << 1;
	SymTab_T * newtab = calloc( size , sizeof( SymTab_T ) );
	assert( newtab );
	// copy every entry of old table to newtab
	for ( int j = 0; j < oldsize ; ++j ) {
		newtab[j] = table[j];
	}
	free( table);
	table = NULL;
	return newtab;
}

/// add_to_table() - add a name to the symbol table
///
/// parameters:
///	name	the name of the new symbol
///	value	the data associated with this symbol
///
/// returns:
///	'true' if the symbol was successfully entered in the table
///	'false' if there was an error
//
bool add_to_table( char name[], int value ) {
	int i;

	if ( name[0] == '\0' ) {
		return false;
	}
	if ( next >= size ) {
		table = resize( table, size ); // side effect: size doubles
		assert( table );
	}
	//
	// Copy the data into the current slot in the table
	//
	table[next].value = value;
	i = 0;
	while( (table[next].name[i] = name[i]) ) {
		++i;
	}
	//
	// Move to the next available slot
	//
	++next;

	return( true );
}

int main( void ) {
	char namebuf[BUFFER_SIZE];
	char valbuf[BUFFER_SIZE];
	int i;
	bool prompt;

	// allocate and clear memory
	table = calloc( size , sizeof( SymTab_T ) );
	assert( table );

	// prompt is true only if the standard input
	// is coming from a terminal

	prompt = isatty( fileno(stdin) );

	// Begin by reading name + value pairs and 
	// adding them to the table

	int count = 0;
	while( true ) {

		// Read the next name; if the user typed
		// control-D, we'll see that as EOF

		if( prompt ) {
			printf( "Enter a name (control-D to stop): " );
		}
		if( fgets(namebuf,BUFFER_SIZE,stdin) == NULL ) {
			break;
		}

		// Locate the newline character in the buffer,
		// and replace it with a NUL character

		i = 0;
		while( namebuf[i] != '\0' && namebuf[i] != '\n' ) {
			++i;
		}

		if( namebuf[i] == '\n'  ) {
			namebuf[i] = '\0';
		}

		// Read the associated number and check for EOF

		if( prompt ) {
			printf( "Enter a number: " );
		}
		if( fgets(valbuf,BUFFER_SIZE,stdin) == NULL ) {
			break;
		}

		if ( strlen( namebuf ) >= MAX_NAME ) {
			continue;                  // skip overlong words
		}

		// Add this name+value pair to the table

		if( ! add_to_table(namebuf,atoi(valbuf)) ) {
			continue;
		}
		++count;
	}

	// All done - dump the symbol table

	printf( "\n\nNumber of entries in symbol table: %d\n", count );
	printf( "Contents of symbol table:\n" );

	for( i = 0; i < next; ++i ) {
		printf( "%3d:  '%s', %d\n", i, table[i].name, table[i].value );
	}

	free( table );
	return( 0 );
}
