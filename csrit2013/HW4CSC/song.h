// File:         $Id: Song.h,v 1.2 2011/10/19 18:25:04 cs4 Exp $
// Author        Joe Geigel
// Description:  Class that represents a song
// Revision History:
//      $Log: Song.h,v $
//      Revision 1.2  2011/10/19 18:25:04  cs4
//      fixed field declaration order.
//
//      Revision 1.1  2005/10/16 17:42:36  cs4
//      Initial revision
//
//

#ifndef SONG_H_DEFINED
#define SONG_H_DEFINED

#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "mtime.h"
 
/***************************************************************
 *
 * This structure represents a Song that is played by a radio 
 * station.
 * 
 * @author       Joe Geigel
 * @author       Rob Duncan
 **************************************************************/
typedef struct song_
{
     char  *artist ;
     char  *title ;
     mtime *lastPlayed ;
} song ;
	
song *newSong(char *t, char *a) ;

void songDelete(song *s) ;

// returns non-zero if artist and title are the same
bool songEquals(const song *s, const song *o) ;

// returns a copy of the source song
song *songCopy(const song *src) ;

// getArtist - Returns the artist
char *songGetArtist(const song *s) ;

// getTitle - Returns the title
char *songGetTitle(const song *s) ;

// record the last time song played (NULL if never)
void songPlay(song *s, mtime *t) ;

// get the last time the song was played
mtime *songGetLastPlayed(const song *) ;

// return a string representation of the song
char *songToString(const song *) ;
#endif
