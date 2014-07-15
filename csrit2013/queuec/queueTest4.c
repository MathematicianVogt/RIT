// $Id: queueTest4.c,v 1.1 2013/10/29 19:07:57 csci243 Exp $
//
// Description:  test driver for the QueueADT module
//
// This test program creates three queues (one an ascending-ordered
// queue, one a descending-ordered queue, and one a FIFO queue),
// inserts a number of integer items into it, and then removes and
// prints them.
//
// Author:  wrc

#define _GNU_SOURCE

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

#include "queueADT.h"

//
// Default number of values to generate and use for testing
//

#define	DEFAULT_N_TESTS	1024

//
// Sequence of test values to be inserted and removed
//

int *testData;
int numData = DEFAULT_N_TESTS;

//
// Comparison functions for integers sorted in ascending order
//

int cmp_int_ascend( const void *a, const void *b ) {
	int na = (int) a;
	int nb = (int) b;

	return( na - nb );
}

int cmp_int_ptr_ascend( const void *a, const void *b ) {
	int na = *(int *) a;
	int nb = *(int *) b;

	return( na - nb );
}

//
// Process insertions/removals using a specific queue
//

void process( QueueADT queue ) {
	int i, j;

	// first, insert the values 

	puts( "Inserting data" );
	for( i = 0; i < numData; ++i ) {
		que_insert( queue, (void *) testData[i] );
	}

	// next, reorder the original list

	qsort( testData, numData, sizeof(int), cmp_int_ptr_ascend );

	puts( "Removing and testing results" );
	j = 0;
	while( !que_empty(queue) ) {
		i = (int) que_remove( queue );
		if( i != testData[j] ) {
			printf( "  mismatch: testData[%3d] %d :: %d\n",
				j, testData[j], i );
		}
		++j;
	}

}


int main( int argc, char *argv[] ) {
	QueueADT up;
	int n;

	// if we were given a command-line argument, use it
	// as the number of tests to generate

	if( argc > 1 ) {
		n = sscanf( argv[1], "%d", &numData );
		if( n != 1 ) {
			fprintf( stderr, "Error - bad test count %s\n",
				 argv[1] );
			exit( EXIT_FAILURE );
		}
	}

	// allocate space for the test data

	testData = (int *) malloc( numData * sizeof(int) );
	if( testData == 0 ) {
		fprintf( stderr, "Error - cannot malloc() data space\n" );
		exit( EXIT_FAILURE);
	}

	// generate numData random numbers

	srand48( (long) time(NULL) );

	for( int i = 0; i < numData; ++i ) {
		testData[i] = lrand48();
	}

	up = que_create( cmp_int_ascend );
	if( up == NULL ) {
		fputs( "Cannot create ascending queue\n", stderr );
		exit( EXIT_FAILURE );
	}

	puts( "Testing the ascending queue" );
	process( up );

	que_destroy( up );

	free( testData );

	return( 0 );
}

// Revisions:
//	$Log: queueTest4.c,v $
//	Revision 1.1  2013/10/29 19:07:57  csci243
//	Initial revision
//
