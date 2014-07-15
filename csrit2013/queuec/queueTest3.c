// $Id: queueTest3.c,v 1.1 2013/10/29 19:07:57 csci243 Exp $
//
// Description:  test driver for the QueueADT module
//
// This test program creates three queues (one an ascending-ordered
// queue, one a descending-ordered queue, and one a FIFO queue),
// inserts a number of pointers into it, and then removes and
// prints them.
//
// Author:  wrc

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "queueADT.h"

//
// Sequence of test values to be inserted and removed
//

char unused[5];
char *testData[] = { 
	unused + 1, unused + 0, unused + 2, unused + 4, unused + 3
};

int numData = sizeof(testData) / sizeof(char *);

//
// Comparison function for pointers sorted in ascending order
//

int cmp_int_ascend( const void *a, const void *b ) {
	char *cpa = (char *) a;
	char *cpb = (char *) b;

	if( cpa < cpb )
		return( -1 );
	else if( cpa == cpb )
		return( 0 );
	else
		return( 1 );
}

//
// Comparison function for pointers sorted in descending order
//

int cmp_int_descend( const void *a, const void *b ) {
	char *cpa = (char *) a;
	char *cpb = (char *) b;

	if( cpb < cpa )
		return( -1 );
	else if( cpb == cpa )
		return( 0 );
	else
		return( 1 );
}

//
// Process insertions/removals using a specific queue
//

void process( QueueADT queue ) {
	int i;
	void *tmp;

	fputs( "Inserting: ", stdout );
	for( i = 0; i < numData; ++i ) {
		printf( " %#010x", (unsigned int) testData[i] );
		que_insert( queue, (void *) testData[i] );
	}

	fputs( "\nRemoving:  ", stdout );
	while( !que_empty(queue) ) {
		tmp = (char *) que_remove( queue );
		printf( " %#010x", (unsigned int) tmp );
	}

	putchar( '\n' );
}


int main( void ) {
	QueueADT up, down, fifo;

	up = que_create( cmp_int_ascend );
	if( up == NULL ) {
		fputs( "Cannot create ascending queue\n", stderr );
		exit( EXIT_FAILURE );
	}

	down = que_create( cmp_int_descend );
	if( down == NULL ) {
		fputs( "Cannot create descending queue\n", stderr );
		exit( EXIT_FAILURE );
	}

	fifo = que_create( NULL );
	if( fifo == NULL ) {
		fputs( "Cannot create FIFO queue\n", stderr );
		exit( EXIT_FAILURE );
	}

	puts( "Testing the ascending queue" );
	process( up );

	puts( "\nTesting the descending queue" );
	process( down );

	puts( "\nTesting the FIFO queue" );
	process( fifo );

	que_destroy( up );
	que_destroy( down );
	que_destroy( fifo );

	return( 0 );
}

// Revisions:
//	$Log: queueTest3.c,v $
//	Revision 1.1  2013/10/29 19:07:57  csci243
//	Initial revision
//
