// $Id: queueTest1.c,v 1.1 2013/10/29 19:07:57 csci243 Exp $
//
// Description:  test driver for the QueueADT module
//
// This test program creates three queues (one an ascending-ordered
// queue, one a descending-ordered queue, and one a FIFO queue),
// inserts a number of integer items into it, and then removes and
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

int testData[] = { 42, 17, -12, 9982, 476, 2912, -22, 3291213, 7782 };
int numData = sizeof(testData) / sizeof(int);

//
// Comparison function for integers sorted in ascending order
//

int cmp_int_ascend( const void *a, const void *b ) {
	int na = (int) a;
	int nb = (int) b;

	return( na - nb );
}

//
// Comparison function for integers sorted in descending order
//

int cmp_int_descend( const void *a, const void *b ) {
	int na = (int) a;
	int nb = (int) b;

	return( nb - na );
}

//
// Process insertions/removals using a specific queue
//

void process( QueueADT queue ) {
	int i;

	fputs( "Inserting: ", stdout );
	for( i = 0; i < numData; ++i ) {
		printf( " %d", testData[i] );
		que_insert( queue, (void *) testData[i] );
	}

	fputs( "\nRemoving:  ", stdout );
	while( !que_empty(queue) ) {
		i = (int) que_remove( queue );
		printf( " %d", i );
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
//	$Log: queueTest1.c,v $
//	Revision 1.1  2013/10/29 19:07:57  csci243
//	Initial revision
//
