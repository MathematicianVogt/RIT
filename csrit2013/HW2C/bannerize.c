

//@author Ryan Vogt
//A simple program that will bannerize
//A phase bordered with a specific character
//gcc -std=c99 -Wall
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int makeBanner(char borderChar, char *myString[], int numberOfargc)
{
	
	int numberOfchar=3;
	printf("%s","\n");	
	for(int j=1; j<numberOfargc; j++)
	{

		numberOfchar=numberOfchar+strlen(myString[j])+1;

	}
	
	//top of banner
	for(int k=0; k<numberOfchar; k++)
	{

		printf("%c", borderChar);


	}
	printf("%s","\n" );

	printf("%c",borderChar );
	printf("%c", ' ' );
	//phase itself
	for(int i=1; i<numberOfargc; i++)
	{

		printf("%s",myString[i]);
		printf("%c", ' ');
	}
	printf("%c",borderChar );
	printf("%c", ' ' );
	printf("%s","\n" );
	//bottom of banner
	for(int k=0; k<numberOfchar; k++)
	{

		printf("%c", borderChar);


	}
	printf("%s","\n" );

	//banner succesfully made
	return EXIT_SUCCESS;

}

//@param argc the number of arguements passed
//@param argv, the array containing the arguments passed
int main(int argc, char *argv[])
 {
 	
 	//Check if atleast two arguments are passed
 	//If so prompt the user to give a character to make the banner
 	if(argc<2)
 	{
 	printf("%s", "usage: bannerize arguments are the text to bannerize.\n");
 	}
 	else
 	{
 		printf("%s","Enter the character for the border: ");
 		char myBorderChar = getchar();
 		while(isspace(myBorderChar))
 		{
 			if(myBorderChar=='\n')
 			{
 				exit(EXIT_FAILURE);
 				
 			}
 			myBorderChar = getchar();
 		}
 		if(myBorderChar==EOF)
 			{
 				//banner cannot be made, program has been quit with CTRL D
 				printf("%s", "");
 				exit(EXIT_FAILURE);

 			}
 		
 		makeBanner(myBorderChar,argv,argc);

 	}
 	//Banner was not made successfully
 	return EXIT_FAILURE;

 }
