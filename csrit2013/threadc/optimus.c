#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>
#include <pthread.h>

//struct for a infoholder, will be passed to a thread to run code.
struct infoHolder
{
	int start;
	int end;
	int threadId;
	int* primeList;



};
//prints the array when given an array and its length
void printyo(int *list,int length)
{
	for(int i =0; i<length; i++)
	{

		printf("%d ",list[i] );


	}
	printf("\n");



}
//fill an array with an length of zeros
int * fillWithzeros(int* list, int length)
{

	for(int i=0; i<length; i++)
	{

		list[i]=0;


	}
	return list;



}
//tells if a number is prime. 
int isPrime(int number)
{

	for(int i=2; i<10; i++)
	{
		if(number==i)
		{



		}
		else if(number%i==0)
		{

			return 0;



		}

	}
	return 1;




}
//actual thead method, generates primes on an interval, only looks at odds though and puts them in a list saved
//in the structure.
void *primeGenerator(void *infoHold)
{	
	struct infoHolder *myinfo=  (struct infoHolder *) infoHold;
	int myStart=myinfo->start;
	int myEnd=myinfo->end;
	int *myPrimeList=myinfo->primeList;
	for(int i=myStart; i<myEnd; i++)
	{


		if(i%2!=0)
		{
		if(isPrime(i))
		{

			myPrimeList[i]=i;


		}
		}

	}
	myinfo->primeList=myPrimeList;
	pthread_exit(NULL);




}
//gets the invervals need to properly divide up the intervals for n threads.
int *generateIntervalArray(int start, int end, int numberOfThreads)
{
	
	int *array=malloc(sizeof(int)*(numberOfThreads+1));
	int myIntervals[numberOfThreads];
	int change=((end-start+numberOfThreads)/(numberOfThreads)) ;
	//printf("CHANGE%d\n",change );
	for(int i=0; i<numberOfThreads+1;i++)
	{
		if(i==numberOfThreads-1)
		{
			array[i]=start+(i*change);

		}
		else
		{

			array[i]=start+(i*change);
			//printf("LOOKIE %d\n",start+(i*change));
		}	
		//printf("LOOK %d\n", array[i] );

	}
	return array;

}
//takes a thread result saved on a structure.
//Then prints it. 
void printThreads(struct infoHolder one[],int length,int size)
{

	for(int i=0; i<length;i++)
	{

		struct infoHolder currentThread = one[i];
		int *currentPrimes = currentThread.primeList;
		int currentThreadId=currentThread.threadId;
		printf("Thread #%d results:  ",currentThreadId );
		for(int i=0; i< size; i++)
		{

			if(currentPrimes[i]!=0)
			{

				printf("%d ", currentPrimes[i] );


			}


		}
		printf("\n");


	}

}
//runs the threading program to find the primes in a given interval. 
void execute(int start, int end, int numberOfThreads)
{	int rc;
	void *retval;
	pthread_t threads[numberOfThreads];
	struct infoHolder myinfo[numberOfThreads];
	int *myInterval=generateIntervalArray(start,end,numberOfThreads);

	


	for(int t = 0; t < numberOfThreads; t++ ) 
	{
		struct infoHolder *currentBody=malloc(sizeof(struct infoHolder));
		int* theList = malloc(sizeof(int)*(end-start));
		theList=fillWithzeros(theList,(end-start));
		//printyo(theList,(end-start));
		currentBody->start=myInterval[t];
		currentBody->end=myInterval[t+1];
		//printf("LOOKIEHERE %d\n",myInterval[t]);
		//printf("LOOKIEHERE2%d\n",myInterval[t+1]);
		currentBody->threadId=t;
		currentBody->primeList=theList;
		myinfo[t]=*currentBody;
		rc = pthread_create( &threads[t], NULL, primeGenerator,(void*) currentBody);

		if (rc){
		   printf( "ERROR; pthread_create() returned %d\n", rc );
		   exit(-1);
		}
	

	}

	for( int t = 0; t < numberOfThreads; t++ ) {
		pthread_join( threads[t], &retval );
	}
	







	printThreads(myinfo,numberOfThreads,end-start);

	free(myInterval);
	//for(int i=0; i<numberOfThreads; i++)
	//{

		//free(myinfo[i]);

	//}


}


//main method to run program. 
int main(int argc, char *argv[])
{	int start;
	int end;
	int numberOfThreads;

	if(argc<2)
	{

		fprintf(stderr, "Usage: primes start end threads\n" );
		exit (EXIT_FAILURE);

	}
	else if(argc==2)
	{

		if((end=atoi(argv[1])))
		{
			start=2;
			numberOfThreads=2;


		}
		else
		{

			fprintf(stderr, "Unable to convert %s to number.\n",argv[1] );
			exit (EXIT_FAILURE);

		}


	}
	else if(argc==3)
	{


		if((start=atoi(argv[1])))
		{
			
			


		}
		else
		{

			fprintf(stderr, "Unable to convert %s to number.\n",argv[1] );
			exit (EXIT_FAILURE);

		}
		if((end=atoi(argv[2])))
		{
			


		}
		else
		{

			fprintf(stderr, "Unable to convert %s to number.\n",argv[2] );
			exit (EXIT_FAILURE);

		}
		numberOfThreads=2;		






	}
	else
	{

		if((start=atoi(argv[1])))
		{


		}
		else
		{

			fprintf(stderr, "Unable to convert %s to number.\n",argv[1] );
			exit (EXIT_FAILURE);

		}
		if((end=atoi(argv[2])))
		{
			


		}
		else
		{

			fprintf(stderr, "Unable to convert %s to number.\n",argv[2] );
			exit (EXIT_FAILURE);

		}
		if((numberOfThreads=atoi(argv[3])))
		{
			


		}
		else
		{

			fprintf(stderr, "Unable to convert %s to number.\n",argv[3] );
			exit (EXIT_FAILURE);

		}






	}
	printf("Command: primes %d %d %d\n",start,end,numberOfThreads);
	execute(start,end,numberOfThreads);


return 0;
}