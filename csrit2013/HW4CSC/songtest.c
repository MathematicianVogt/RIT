// 
// File: songtest.c 
// 
// 
// @author csci243 
// 
// Version: 
// $Id$ 
// 
// Revisions: 
// $Log$ 
// 
// // // // // // // // // // // // // // // // // // // // // // // // 


#include <stdio.h>
#include <stdlib.h>

#include "song.h"
#include "mtime.h"

///
/// main function
/// @author csci243
///
/// @param argc  number of command line arguments, including program name
/// @param argv  supplied command line arguments, including program name
/// @returns errorCode  error Code; EXIT_SUCCESS if no error
///
int main( int argc, char **argv )
{
    mtime *atime = NULL ;

    song *song1 = NULL ;
    song *song2 = NULL ;
    song *song3 = NULL ;

    printf( "Creating a time...\n" ) ;
    atime = newMTime( 6, 30, "pm" ) ;

    printf( "Starting song tests...\n" ) ;

    song1 = newSong( "Summer Days", "Beach Boys" ) ;
    printf( "First song initialized...\n" ) ;

    char *artist = songGetArtist( song1 ) ;
    char *title = songGetTitle( song1 ) ;
    printf( "The current song1 song is: %s by %s.\n", title, artist ) ;
    free( artist ) ;
    free ( title ) ;

    char * sstr = songToString( song1 ) ;
    printf( "Otherwise... %s\n", sstr ) ;
	free(sstr);
    songPlay( song1, atime ) ;
    sstr = songToString( song1 ) ;
    printf( "After playing the song... %s\n", sstr ) ;
    free(sstr);
    mtime *glp = songGetLastPlayed( song1 ) ;
    char *sglp = mtimeToString(glp) ;
    artist = songGetArtist( song1 ) ;
    title = songGetTitle( song1 ) ;
    printf( "The same song referencing members is: %s by %s. Last played at: %s\n", 
             title, artist, sglp ) ;
    free( artist ) ;
    free( title ) ;
    mtimeDelete( glp ) ;
    free( sglp ) ;

    songDelete( song1 ) ;

    artist = malloc( sizeof( "Goo Goo Dolls" ) + 1 ) ;
    title = malloc( sizeof( "The Black Hole" ) + 1 ) ;
    strcpy( artist, "Goo Goo Dolls" ) ;
    strcpy( title, "The Black Hole" ) ;
    song2 = newSong( title, artist ) ;
    free( artist ) ;
    free( title ) ;
    sstr = songToString ( song2 ) ;
    printf( "The o song is: %s\n", sstr ) ;
    free(sstr);
    song1 = songCopy( song2 ) ;
    sstr = songToString( song1 ) ;
    free(song1);
    printf( "The copy of song2 song is: %s \n", sstr ) ;
    free(sstr);
    songDelete( song2 ) ;
    sstr = songToString( song1 ) ;
    printf( "After deleting the original, the song1 song is ... %s\n", sstr ) ;
free(sstr);

    char *pstr ;
    song3 = songCopy( song1 ) ;
    if ( songEquals( song1, song3 ) )
    {
        sstr = songToString( song1 ) ;
        pstr = songToString( song3 ) ;
        printf( "The song3 song %s\n    is the same as song1 song %s\n", pstr, sstr ) ;
        free( pstr ) ;
	free(sstr);
	songDelete(song3);
    }
    else
        printf( "ERROR: songCopy() failure!\n" ) ;

 
    song3 = newSong( "Its a Beatiful Morning", "Chicago" ) ;
    sstr = songToString( song3 ) ;
    printf( "The current song3 song is: %s\n", sstr ) ;
    free(sstr);
    songPlay( song3, newMTime(4, 42, "am") ) ;

    sstr = songToString( song3 ) ;
    printf( "The current song3 song is: %s\n", sstr ) ;
    free( sstr ) ;

    songDelete( song1 ) ;
    songDelete( song3 ) ;

    return EXIT_SUCCESS ;
}
