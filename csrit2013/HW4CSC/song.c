// File:         song.c
// Author:       Joe Geigel
// Description:  Representation of a song
// Revisions:
//              $Log$
//
//

#include <stdio.h>
#include <string.h>

#include "song.h"

/*
 * Initialize a song structure with the given Title and Artist. Set
 * the time last played to NULL.
 */
song *newSong(char *t, char *a)
{
    song *s = NULL ;
 
    s = malloc(sizeof(song)) ;

    s->artist = malloc(strlen(a) + 1) ;
    strcpy(s->artist, a) ;

    s->title = malloc(strlen(t) + 1) ;
    strcpy(s->title, t) ;

    s->lastPlayed = NULL ;

    return s ;
}

//
// Name:        (destructor)
//
void songDelete(song *s)
{
    //artist
    free(s->artist) ;
    //title
    free(s->title) ;
    //time
    if(NULL != s->lastPlayed)
        mtimeDelete(s->lastPlayed) ;
    //song
	free(s);

}

char *songGetArtist(const song *s) 
{
    char *tmp = NULL ;
    tmp = malloc(strlen(s->artist)+1) ;
    strcpy(tmp, s->artist) ;
    return tmp ;
}

char *songGetTitle(const song *s)
{
    char *tmp = NULL ;
    tmp = malloc(strlen(s->title)+1) ;
    strcpy(tmp, s->title) ;
    return tmp ;
}

mtime *songGetLastPlayed(const song *s)
{
    mtime *tmp = NULL ;
    tmp = mtimeCopy(s->lastPlayed) ;
    return tmp ;
}

void songPlay(song *s, mtime *t)
{
    if (NULL != s->lastPlayed)
        free(s->lastPlayed) ;
    s->lastPlayed = t ;
}

bool songEquals(const song *s, const song *o)
{
    // Two songs are equal if their titles and artists are equal
    if((NULL != s) && (NULL != o))
    {
        if ((0 == strcmp(s->artist, o->artist)) &&
            (0 == strcmp(s->title, o->title)))
        {
            return true ;
        }
    }
    return false ;
}

song *songCopy(const song *s)
{
    song *d = NULL ;
    mtime *tmp = NULL ;

    d = malloc(sizeof(song)) ;

    d->artist = malloc(sizeof(s->artist) + 1) ;
    strcpy(d->artist, s->artist) ;

    d->title = malloc(sizeof(s->title) + 1) ;
    strcpy(d->title, s->title) ;

    if (NULL != s->lastPlayed)
    {
        // copy the last played
        tmp = mtimeCopy(s->lastPlayed) ;
        d->lastPlayed = tmp ;
    }
    else
    {
        // set lastPlayed to NULL
        d->lastPlayed = NULL ;
    }

    return d ;
}

char *songToString(const song *s)
{
    char *sstr = NULL ;
    char *st = NULL ;
    char *sa = NULL ;
    char *tt = NULL ;
    int len = 0 ;

    st = songGetTitle(s) ;
    sa = songGetArtist(s) ;

    // calculate the total string length needed.
    len = strlen("Title: ") + strlen(st) +
                  strlen("  Artist: ") + strlen(sa) + 1 ;

    if (NULL != s->lastPlayed)
    {
        tt = mtimeToString(s->lastPlayed) ;
        len += strlen(" at ") + strlen(tt) ;
    }

    // allocate enough space for the song
    sstr = malloc( len ) ;

    sprintf(sstr, "Title: %s  Artist: %s", st, sa) ;

    if (NULL != s->lastPlayed)
    {
        sstr = strcat(sstr, " at ") ;
        sstr = strcat(sstr, tt) ;
    }

    free(sa) ;
    free(st) ;
    free(tt) ;
    return sstr ;
}
