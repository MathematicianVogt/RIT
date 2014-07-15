// 
// File: triangle.c 
// 
// A program to print out multiple triangles "right-up" triangles.
// of the form
//    *
//   **
//  ***
// ****
// 
// @author TODO_AUTHOR_ID_ : TODO_AUTHOR_FULL_NAME_
// 
// // // // // // // // // // // // // // // // // // // // // // // // 

// TODO_ADD_#INCLUDES_ HERE
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 *  Function: drawTriangle
 *
 *  Description: function to draw a triangle whose height/width is "size"
 * 
 *  @param size  the size of the triangle to draw
 */
// TODO_WRITE_DRAWTRIANGLE_FUNCTION HERE
 int drawTriangle(int size)
 {
 	char dot='*';
 	char space=' ';
	for(int i=0; i<size; i++)
	{

		for(int k=0; k<size; k++)
		{

			int blankspace=size-i-1;
			if(k<blankspace)
			{

				printf("%c",space);


			}
			if(k>=blankspace)
			{


				printf("%c",dot);
			}


		}
		printf("%s","\n");


	}

	return 0;
 	}
 

 	

 

/**
 *  Function: main 
 *
 *  Description: program to draw 3 triangles of size 2, 4, and 6.
 *
 *  @author Ryan Vogt
 * 
 *  @param argc  number of command line arguments, including program name
 *  @param argv  supplied command line arguments, including program name
 *  @returns errorCode  error Code; EXIT_SUCCESS if no error
 */
// TODO_WRITE_MAIN_FUNCTION HERE
 int main(int argc, char *argv[])
 {
 	drawTriangle(2);
 	printf("%s\n", "");
 	drawTriangle(4);
 	printf("%s\n", "");
 	drawTriangle(6);
 	return EXIT_SUCCESS;

 }

// // // // // // // // // // // // // // // // // // // // // // // // 
// Version: 
// $Id$ 
// 
// Revisions: 
// $Log$ 
