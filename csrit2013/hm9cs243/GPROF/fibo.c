// 
// file: fibo.c
// description: a variety of fibonacci implementations.
// author: ben k steele bks@cs.rit.edu
// language: c
// standard compile flags: -std=c99 -ggdb
// 

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <assert.h>

/// fiboRec : Natural -> Natural
/// given the 0-based index, nth,
/// return the nth value in the Fibonacci series.
//
int fiboRec( int nth ) {
    if ( nth == 0 ) {
        return 0;
    }
    if ( nth == 1 ) {
        return 1;
    }
    return fiboRec( nth - 1 ) + fiboRec( nth - 2 ); 
}

// test_fiboRec performs basic tests of fiboR.
void test_fiboRec( void ) {
    printf( "fiboRec( 0 ) is %i\n", fiboRec( 0 ) );
    printf( "fiboRec( 1 ) is %i\n", fiboRec( 1 ) );
    printf( "fiboRec( 2 ) is %i\n", fiboRec( 2 ) );
    printf( "fiboRec( 3 ) is %i\n", fiboRec( 3 ) );
    printf( "fiboRec( 4 ) is %i\n", fiboRec( 4 ) );
    printf( "fiboRec( 6 ) is %i\n", fiboRec( 6 ) );
}

/// fiboAccum : Natural Natural Natural -> Natural
/// given the 0-based index, nth, the first and last (most recent) values,
/// return the nth value in the Fibonacci series.
/// fiboAccum is the helper function for fiboR.
//
static int fiboAccum( int nth, int first, int last ) {
    if ( nth == 0 ) {
        return first;
    }
    if ( nth == 1 ) {
        return last;
    }
    return fiboAccum( nth - 1, last, first + last );
}

/// fiboR : Natural -> Natural
/// given the 0-based index, nth,
/// return the nth value in the Fibonacci series.
//
int fiboR( int nth ) {
    return fiboAccum( nth, 0, 1 );
}

// test_fiboR performs basic tests of fiboR.
void test_fiboR( void ) {
    printf( "fiboR( 0 ) is %i\n", fiboR( 0 ) );
    printf( "fiboR( 1 ) is %i\n", fiboR( 1 ) );
    printf( "fiboR( 2 ) is %i\n", fiboR( 2 ) );
    printf( "fiboR( 3 ) is %i\n", fiboR( 3 ) );
    printf( "fiboR( 4 ) is %i\n", fiboR( 4 ) );
    printf( "fiboR( 6 ) is %i\n", fiboR( 6 ) );
}

/// fiboIter : Natural -> Natural
/// given the 0-based index, nth,
/// return the nth value in the Fibonacci series.
//
int fiboIter( int nth ) {
    int first = 0;
    int last = 1;
    while ( nth > 1 ) {
        int next = first + last;
        first = last;
        last = next;
        nth = nth - 1;
    }
    if ( nth == 1 ) {
        return last;
    }
    return first;
}

// test_fiboIter performs basic tests of fiboIter.
void test_fiboIter( void ) {
    printf( "fiboIter( 0 ) is %i\n", fiboIter( 0 ) );
    printf( "fiboIter( 1 ) is %i\n", fiboIter( 1 ) );
    printf( "fiboIter( 2 ) is %i\n", fiboIter( 2 ) );
    printf( "fiboIter( 3 ) is %i\n", fiboIter( 3 ) );
    printf( "fiboIter( 4 ) is %i\n", fiboIter( 4 ) );
    printf( "fiboIter( 6 ) is %i\n", fiboIter( 6 ) );
}

/// using applet of invert and add calculator method found on
/// http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/phi.html
/// cited 12/2013
#define PHI 1.618033988749895

/// fiboPhi : Natural -> Natural
/// given the 0-based index, nth,
/// return the nth value in the Fibonacci series.
/// fiboPhi uses the formula: round( ( PHI ^ nth ) / sqrt( 5 ) ).
//
int fiboPhi( int nth ) {

    assert( nth > -1 );
    double numer = rint( pow( PHI, nth + 0.0 ) );
    return (int)( numer / sqrt( 5.0 ) );
}

// test_fiboPhi performs basic tests of fiboPhi.
void test_fiboPhi( void ) {
    printf( "fiboPhi( 0 ) is %i\n", fiboPhi( 0 ) );
    printf( "fiboPhi( 1 ) is %i\n", fiboPhi( 1 ) );
    printf( "fiboPhi( 2 ) is %i\n", fiboPhi( 2 ) );
    printf( "fiboPhi( 3 ) is %i\n", fiboPhi( 3 ) );
    printf( "fiboPhi( 4 ) is %i\n", fiboPhi( 4 ) );
    printf( "fiboPhi( 6 ) is %i\n", fiboPhi( 6 ) );
}

/// main runs stress tests on the fibonacci implementations.
//
int main( int argc, char * argv[] ) {

    int choice = 0xF;   // default run all
    if ( argc == 1 ) {
        fprintf( stderr, "usage: fibo [1|2|4|8]\n" );
        return 1;
    } else {
        choice = atoi( argv[1] );
        if ( choice == 0 ) {
            fprintf( stderr, "usage: fibo [1|2|4|8]\n" );
            return 2;
        }
    }
    const int MAXFIB = 40;  // int storage overflows after 47.
    int fib;
    int j = 1;
    for ( j = 1; j < MAXFIB; ++j ) {
        if ( choice & 1 ) {
            fib = fiboRec( j );
            printf( "fiboRec( %i ) is %i\n", j, fib );
        }
        if ( choice & 2 ) {
            fib = fiboR( j );
            printf( "fiboR( %i ) is %i\n", j, fib );
        }
        if ( choice & 4 ) {
            fib = fiboIter( j );
            printf( "fiboIter( %i ) is %i\n", j, fib );
        }
        if ( choice & 8 ) {
            fib = fiboPhi( j );
            printf( "fiboPhi( %i ) is %i\n", j, fib );
        }
    }
    return 0;
}

